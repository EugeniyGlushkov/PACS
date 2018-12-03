package ru.alvisid.pacs.repository;

import java.util.List;

/**
 * The generalized functional of a typical repository.
 * @param <T> the type of the repository's objects.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface TypicalRepository<T> {
    /**
     * Saves or updates a given object.
     *
     * @param obj the object to save or update.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    T save(T obj);

    /**
     * Deletes the object by specified id.
     *
     * @param id the specified id of a deleted object.
     * @return {@code true} - the entity is deleted, {@code false} - the entity isn't found.
     */
    boolean delete(int id);

    /**
     * Returns the object with the cpecifiec id.
     *
     * @param id the specifiec id of the object to get.
     * @return an object with the cpecifiec id,
     * null - if there aren't the object with the cpecifiec id in the DB.
     */
    T get(int id);

    /**
     * Returns the list with all objects.
     *
     * @return the list with all objects.
     */
    List<T> getAll();
}
