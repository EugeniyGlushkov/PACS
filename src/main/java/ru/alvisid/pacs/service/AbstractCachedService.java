package ru.alvisid.pacs.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ru.alvisid.pacs.model.abstractions.HasId;
import ru.alvisid.pacs.repository.TypicalRepository;
import ru.alvisid.pacs.util.cache.Cached;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

public abstract class AbstractCachedService<T extends TypicalRepository<S>, S extends HasId>
        extends AbstractService<T, S> implements Cached {

    /**
     * Constructs new service with the specified repository implementation.
     *
     * @param repository the specific repository implementation
     *                   which corresponds to service's object: type {@code S}.
     */
    public AbstractCachedService(T repository) {
        super(repository);
    }

    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public S create(S obj) {
        return super.create(obj);
    }

    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(S obj) throws NotFoundException {
        super.update(obj);
    }

    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void delete(int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    @Cacheable(cacheResolver = "cacheResolver")
    public List<S> getAll() {
        return super.getAll();
    }
}
