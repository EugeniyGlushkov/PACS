package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.repository.PointActionRepository;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.PointActionService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code PointActionService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see PointActionService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class PointActionServiceImpl
        extends AbstractCachedService <PointActionRepository, PointAction> implements PointActionService {
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
        Assert.notNull(pointAction, pointAction.getClass().getSimpleName() + " must not be null");
        return repository.save(pointAction, controlPointId);
    }

    /**
     * Updates an existing in the data base point action
     * with inserted control point.
     * Evicts a cache that is got by cache resolver.
     *
     * @param pointAction    the point action to update.
     * @param controlPointId the control point's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(PointAction pointAction, int controlPointId) throws NotFoundException {
        Assert.notNull(pointAction, pointAction.getClass().getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(pointAction, controlPointId), pointAction.getId());
    }

    /**
     * Returns the list with all point actions by control point's id.
     *
     * @param ctrlPointId the specified control point's id.
     * @return the list with all point actions by control point's id.
     */
    @Override
    @Cacheable(cacheResolver = "cacheResolver")
    public List <PointAction> getAllByControlPointId(int ctrlPointId) {
        return repository.getAllByControlPointId(ctrlPointId);
    }

    /**
     * Returns a point action with filled field: {@code controlPoint}
     * by specified id.
     *
     * @param id the specified point action's id.
     * @return the point action with filled field: {@code controlPoint}.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    @Override
    public PointAction getWithCtrlPoint(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithCtrlPoint(id), id);
    }

    /**
     * Constructs new {@code PointActionServiceImpl} and set a specified point action's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified point action's repository implementation.
     */
    @Autowired
    public PointActionServiceImpl(PointActionRepository repository) {
        super(repository);
    }

    /**
     * Returns a cache alias for this service.
     *
     * @return the cache alias for this service.
     */
    @Override
    public String getCacheAlias() {
        return CACHE_ALIAS;
    }
}
