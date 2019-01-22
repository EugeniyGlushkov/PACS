package ru.alvisid.pacs.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.repository.impl.DataJpaControlPointRepositoryImpl;
import ru.alvisid.pacs.repository.impl.DataJpaPointActionRepositoryImpl;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.PointActionService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

public class PointActionServiceImpl
        extends AbstractCachedService<DataJpaPointActionRepositoryImpl, PointAction> implements PointActionService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "pointActions";

    /**
     * Creates and saves a given point action in the data base
     * with inserted control point.
     * Evicts a cache that is got by cache resolver.
     *
     * @param pointAction    the point action to create.
     * @param controlPointId the control point's id to insert.
     * @return the created object.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public PointAction create(PointAction pointAction, int controlPointId) {
        Assert.notNull(pointAction, pointAction.getClass().getSimpleName() + " must not be null")
        return repository.save(pointAction, controlPointId);
    }

    /**
     *
     *
     * @param pointAction    the point action to update.
     * @param controlPointId the control point's id to insert.
     * @throws NotFoundException
     */
    @Override
    public void update(PointAction pointAction, int controlPointId) throws NotFoundException {

    }
}
