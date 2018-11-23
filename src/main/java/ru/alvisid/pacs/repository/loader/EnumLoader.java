package ru.alvisid.pacs.repository.loader;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.alvisid.pacs.model.enumActivate.AbstractDictionary;
import ru.alvisid.pacs.model.enumActivate.MappedEnum;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnumLoader {
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        Set <EntityType <?>> entities = em.getMetamodel().getEntities();

        List <?> entityClasses = entities.stream()
                .map(EntityType::getJavaType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (Object obj : entityClasses) {
            if (!AbstractDictionary.class.isAssignableFrom((Class<AbstractDictionary>)obj)) {
                continue;
            }

            if (!((Class)obj).isAnnotationPresent(MappedEnum.class)){
                continue;
            }

            MappedEnum mappedEnum = ((Class<MappedEnum>)obj).getAnnotation(MappedEnum.class);
            Session session = ((Session)em.getDelegate()).getSessionFactory().openSession();
            updateEnumIdentifiers(session, mappedEnum.enumClass(), (Class<AbstractDictionary>)obj);
        }
    }

    private void updateEnumIdentifiers(Session session, Class <? extends Enum> enumClass,
                                       Class <? extends AbstractDictionary> dictClass) {
        /*String sql = "FROM " + dictClass.getSimpleName();
        List <AbstractDictionary> valueList = session.createQuery(sql).list();*/
        String sql = "FROM " + dictClass.getSimpleName();
        List <AbstractDictionary> valueList = em.createQuery(sql).getResultList();
        Enum[] constants = enumClass.getEnumConstants();
        int maxId = 0;
        int valueId = 0;

        for (AbstractDictionary dict : valueList) {
            valueId = dict.getId();
            setEnumOrdinal(Enum.valueOf(enumClass, dict.getCode()), valueId);

            if (valueId > maxId) {
                maxId = valueId;
            }
        }

        Object valuesArray = Array.newInstance(enumClass, maxId + 1);

        for (Enum value : constants) {
            Array.set(valuesArray, value.ordinal(), value);
        }

        Field field;

        try {
            field = enumClass.getDeclaredField("$VALUES");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, valuesArray);
        } catch (Exception exc) {
            throw new RuntimeException("Can't update values array: ", exc);
        }
    }

    private void setEnumOrdinal(Enum object, int newOrdinal) {
        Field field;

        try {
            field = object.getClass().getSuperclass().getDeclaredField("ordinal");
            field.setAccessible(true);
            field.set(object, newOrdinal);
        } catch (Exception exc) {
            throw new RuntimeException("Can't update enum ordinal: ", exc);
        }
    }
}
