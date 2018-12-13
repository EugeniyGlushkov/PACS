package ru.alvisid.pacs.service;

import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

/**
 * The functionality that all services have.
 *
 * @param <T> the type of the service's objects.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface TypicalService<T> {
    /**
     * Creates and saves a given object in the data base.
     *
     * @param obj the object to create.
     * @return the created object.
     */
    T create(T obj);

    /**
     * Updates an existing in the data base object.
     *
     * @param obj the object to update.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(T obj) throws NotFoundException;

    /**
     * Deletes the object by specified id.
     *
     * @param id the specified id of a deleted object.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    void delete(int id) throws NotFoundException;

    /**
     * Returns the object by the specified id.
     *
     * @param id the specified id of the object to get.
     * @return the object with the specified id.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    T get(int id) throws NotFoundException;

    /**
     * Returns the list with all objects.
     *
     * @return the list with all objects.
     */
    List<T> getAll();
}
