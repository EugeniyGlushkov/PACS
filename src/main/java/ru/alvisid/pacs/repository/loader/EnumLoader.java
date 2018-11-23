package ru.alvisid.pacs.repository.loader;

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
            if (!AbstractDictionary.class.isAssignableFrom((Class <AbstractDictionary>) obj)) {
                continue;
            }

            if (!((Class) obj).isAnnotationPresent(MappedEnum.class)) {
                continue;
            }

            MappedEnum mappedEnum = ((Class <MappedEnum>) obj).getAnnotation(MappedEnum.class);
            updateEnumIdentifiers(mappedEnum.enumClass(), (Class <AbstractDictionary>) obj);
            updateEnumValues(mappedEnum.enumClass());
        }
    }

    private void updateEnumIdentifiers(Class <? extends Enum> enumClass,
                                       Class <? extends AbstractDictionary> dictClass) {
        String sql = "FROM " + dictClass.getSimpleName();
        List <AbstractDictionary> valueList = em.createQuery(sql).getResultList();

        for (AbstractDictionary dict : valueList) {
            setEnumOrdinal(Enum.valueOf(enumClass, dict.getCode()), dict.getId());
        }
    }

    private void updateEnumValues(Class <? extends Enum> enumClass) {
        Enum[] enumConstants = enumClass.getEnumConstants();

        Object valuesArray = Array.newInstance(enumClass, enumConstants.length + 1);

        for (Enum value : enumConstants) {
            Array.set(valuesArray, value.ordinal(), value);
        }

        Field field;
        Field modifiersField;

        try {
            field = enumClass.getDeclaredField("$VALUES");
            field.setAccessible(true);

            modifiersField = Field.class.getDeclaredField("modifiers");
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
