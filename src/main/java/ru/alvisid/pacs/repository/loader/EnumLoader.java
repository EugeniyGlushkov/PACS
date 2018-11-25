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
 * Dictionaries classes have to be heir of the {@code AbstractDictionary}
 * and be annotated with @MappedEnum which contains enum Class
 * to mark the dictionary class that is represented by the enum.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Component
public class EnumLoader {
    /**
     * An instance is associated with a persistence context.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Initializes a synchronization enums which represent dictionaries
     * with corresponding dictionaries in the data base.
     */
    @PostConstruct
    private void init() {
        //Get all mapped entity types.
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();

        //Get all mapped classes.
        List<?> entityClasses = entities.stream()
                .map(EntityType::getJavaType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (Object obj : entityClasses) {
            if (!AbstractDictionary.class.isAssignableFrom((Class<AbstractDictionary>) obj)) {
                continue;
            }

            if (!((Class) obj).isAnnotationPresent(MappedEnum.class)) {
                continue;
            }

            //Get MappedEnum object of the carrent entity.
            MappedEnum mappedEnum = ((Class<?>) obj).getAnnotation(MappedEnum.class);
            updateEnumIdentifiers(mappedEnum.enumClass(), (Class<AbstractDictionary>) obj);
            updateEnumValues(mappedEnum.enumClass());
        }
    }

    /**
     * Syncs fields <b>ordinal</b> of the specified enum's constants
     * with the corresponding dictionary in the data base mapped by <b>AbstractDictionary</b> class.
     * A constants name corresponds to the id attribute in the dictionary table.
     * A constants field ordinal corresponds to the code attribute in the
     *
     * @param enumClass the identified enum class represents dictionary from data base.
     * @param dictClass the dictionary mapped class.
     */
    private void updateEnumIdentifiers(Class<? extends Enum> enumClass,
                                       Class<? extends AbstractDictionary> dictClass) {
        //Create SQL query and get list of the dictionary values.
        String sql = "FROM " + dictClass.getSimpleName();
        List<AbstractDictionary> valueList = em.createQuery(sql).getResultList();

        ValidationUtil.checkDictionary(enumClass, valueList);

        valueList.forEach(dict -> setEnumOrdinal(Enum.valueOf(enumClass, dict.getCode()), dict.getId()));
    }

    /**
     * Updates array field {@code $VALUES} of the constants in the specified enum class
     * so that array index of the enum constant equals appropriate id
     * from the dictionary table.
     *
     * @param enumClass the enum class to apdate array field {@code $VALUES}.
     */
    private void updateEnumValues(Class<? extends Enum> enumClass) {
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

    /**
     * Changes ordinal field of the specified enum object
     * to the specified value.
     *
     * @param object     the enum object to change ordinal field.
     * @param newOrdinal the new ordinal value.
     */
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
