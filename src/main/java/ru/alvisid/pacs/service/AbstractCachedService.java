package ru.alvisid.pacs.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ru.alvisid.pacs.model.abstractions.HasId;
import ru.alvisid.pacs.repository.TypicalRepository;
import ru.alvisid.pacs.util.cache.Cached;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

/**
 * The abstract service realisation which uses cache.
 *
 * @param <T> the type of the repository which corresponds to the specified service's object: type {@code <S>}.
 * @param <S> the type of the service's objects.
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public abstract class AbstractCachedService<T extends TypicalRepository <S>, S extends HasId>
        extends AbstractService <T, S> implements Cached {

    /**
     * Constructs new service with the specified repository implementation.
     *
     * @param repository the specific repository implementation
     *                   which corresponds to service's object: type {@code S}.
     */
    public AbstractCachedService(T repository) {
        super(repository);
    }

    /**
     * Creates and saves a given object in the data base.
     * Delegates a functionality to the superclass.
     * Evicts a cache that is got by cache resolver.
     *
     * @param obj the object to create.
     * @return the created object.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public S create(S obj) {
        return super.create(obj);
    }

    /**
     * Updates an existing in the data base object.
     * Delegates a functionality to the superclass.
     * Evicts a cache that is got by cache resolver.
     *
     * @param obj the object to update.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(S obj) throws NotFoundException {
        super.update(obj);
    }

    /**
     * Deletes the object by specified id.
     * Delegates a functionality to the superclass.
     * Evicts a cache that is got by cache resolver.
     *
     * @param id the specified id of a deleted object.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void delete(int id) throws NotFoundException {
        super.delete(id);
    }

    /**
     * Returns the list with all objects.
     * Delegates a functionality to the superclass.
     * Uses a cache that is got by cache resolver.
     *
     * @return the list with all objects.
     */
    @Override
    @Cacheable(cacheResolver = "cacheResolver")
    public List <S> getAll() {
        return super.getAll();
    }
}
