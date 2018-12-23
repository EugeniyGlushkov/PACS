package ru.alvisid.pacs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.abstractions.AbstractId;
import ru.alvisid.pacs.model.abstractions.HasId;
import ru.alvisid.pacs.repository.TypicalRepository;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * The realization of the functionality which is common to all services.
 *
 * @param <T> the type of the repository which corresponds to the specified service's object: type {@code <S>}.
 * @param <S> the type of the service's objects.
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public abstract class AbstractService<T extends TypicalRepository <S>, S extends HasId>
        implements TypicalService <S> {
    /**
     * The specific repository implementation
     * which corresponds to service's object: type {@code S}.
     */
    protected final T repository;

    /**
     * Creates and saves a given object in the data base.
     *
     * @param obj the object to create.
     * @return the created object.
     */
    @Override
    public S create(S obj) {
        Assert.notNull(obj, obj.getClass().getSimpleName() + " must not be null");
        return repository.save(obj);
    }

    /**
     * Updates an existing in the data base object.
     *
     * @param obj the object to update.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    public void update(S obj) throws NotFoundException {
        Assert.notNull(obj, obj.getClass().getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(obj), obj.getId());
    }

    /**
     * Deletes the object by specified id.
     *
     * @param id the specified id of a deleted object.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    /**
     * Returns the object by the specified id.
     *
     * @param id the specified id of the object to get.
     * @return the object with the specified id.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    @Override
    public S get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    /**
     * Returns the list with all objects.
     *
     * @return the list with all objects.
     */
    @Override
    public List <S> getAll() {
        return repository.getAll();
    }

    /**
     * Constructs new servise with the specified repository implementation.
     *
     * @param repository the specific repository implementation
     * which corresponds to service's object: type {@code S}.
     */
    public AbstractService(T repository) {
        this.repository = repository;
    }
}
