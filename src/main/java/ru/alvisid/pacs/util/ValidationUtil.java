package ru.alvisid.pacs.util;

import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.Objects;

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
     * @param <T> the type of the checked object.
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
     * @param <T> the type of the checked object.
     * @return the checked object.
     * @throws NotFoundException if the checked object is null.
     * @see ValidationUtil#checkNotFoundWithId(boolean, int)
     */
    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "Not found entity with id=" + id);
    }
}
