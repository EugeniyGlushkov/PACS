package ru.alvisid.pacs.util;

import ru.alvisid.pacs.model.abstractions.HasId;
import ru.alvisid.pacs.model.enumActivate.AbstractDictionary;
import ru.alvisid.pacs.util.exceptions.EnumLoaderException;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class consists exclusively of static methods wich helps
 * with checking the passed values.
 */
public class ValidationUtil {
    private ValidationUtil() {

    }

    /**
     * Throws the NotFoundException if a found is false.
     *
     * @param found   shows availability the entity in the DB.
     * @param message message for thrown NotFoundException.
     * @throws NotFoundException the exception is thrown when entity is not found.
     * @see ValidationUtil#checkNotFound(Object, String)
     */
    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException(message);
        }
    }

    /**
     * Returns a checked object, throws the NotFoundException if the checked object is null.
     *
     * @param object  the checked object.
     * @param message message for thrown NotFoundException.
     * @param <T>     the type of the checked object.
     * @return the checked object.
     * @throws NotFoundException if the checked object is null.
     * @see ValidationUtil#checkNotFound(boolean, String)
     */
    public static <T> T checkNotFound(T object, String message) {
        checkNotFound(!Objects.isNull(object), message);
        return object;
    }

    /**
     * Throws the NotFoundException if a found is false.
     *
     * @param found shows availability the entity in the DB.
     * @param id    specific id of the entity.
     * @throws NotFoundException the exception is thrown when entity is not found.
     * @see ValidationUtil#checkNotFound(Object, String)
     */
    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "Not found entity with id=" + id);
    }

    /**
     * Returns a checked object, throws the NotFoundException if the checked object is null.
     *
     * @param object the checked object.
     * @param id     specific id of the entity.
     * @param <T>    the type of the checked object.
     * @return the checked object.
     * @throws NotFoundException if the checked object is null.
     * @see ValidationUtil#checkNotFoundWithId(boolean, int)
     */
    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "Not found entity with id=" + id);
    }

    /**
     * Checks that enum dictionary matches to dictionary in the data base.
     * Number enum constants is equal to number members of the table dictionary.
     * Every enum constant has appropriate member in the table dictionary.
     *
     * @param enumDict enum class represents dictionary.
     * @param dict     list of the dictionary members.
     */
    public static void checkDictionary(Class<? extends Enum> enumDict, List<? extends AbstractDictionary> dict) {
        List<Enum> constants = Arrays.asList(enumDict.getEnumConstants());
        List<String> dictStrs = dict.stream()
                .map(AbstractDictionary::getCode)
                .collect(Collectors.toList());

        if (constants.size() != dictStrs.size()) {
            throw new EnumLoaderException(String.format("Enum %s constants amount=%d is not equal"
                            + " dictionary members amount=%d in the data base!",
                    enumDict.getName(), constants.size(), dictStrs.size()));
        }

        constants.forEach(e -> {
            if (!dictStrs.contains(e.name())) {
                throw new EnumLoaderException(String.format("Can not synchronize %s with dictionary, "
                                + "because there are not code = '%s' in the data base dictionary!",
                        e.getClass().getName(), e.name()));
            }
        });
    }

    public static <T extends HasId> boolean equalIdChek (T objOne, T objTwo) {
        return objOne.getId().equals(objTwo.getId());
    }
}
