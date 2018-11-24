package ru.alvisid.pacs.repository.loader;

import org.springframework.stereotype.Component;
import ru.alvisid.pacs.model.enumActivate.AbstractDictionary;
import ru.alvisid.pacs.model.enumActivate.MappedEnum;
import ru.alvisid.pacs.util.ValidationUtil;

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

/**
 * Contains methods to synchronize the enum classes with dictionaries classes
 * which represents dictionary tables from data base.
 * We don't need keep enums like entity and can save enums by ordinal values.
 * Dictionaries classes have to be heir of the AbstractDictionary
 * and be annotated with @MappedEnum which contains enum Class
 * to mark the dictionary class that is represented by the enum. *
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Component
public class EnumLoader {
    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @PostConstruct
    private void init() {
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

        ValidationUtil.checkDictionary(enumClass, valueList);

        for (AbstractDictionary dict : valueList) {
            String code = dict.getCode();

            if (!ValidationUtil.checkDictCode(code, enumClass)) {
                continue;
            }

            setEnumOrdinal(Enum.valueOf(enumClass, code), dict.getId());
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
