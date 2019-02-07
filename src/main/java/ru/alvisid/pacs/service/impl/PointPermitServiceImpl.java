package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.repository.PointPermitRepository;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.PointPermitService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code PointPermitService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see PointPermitService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class PointPermitServiceImpl
        extends AbstractCachedService <PointPermitRepository, PointPermit> implements PointPermitService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "pointPermits";

    /**
     * Creates and saves a given point permit in the data base
     * with inserted employee and point action.
     *
     * @param pointPermit   the point permit to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @return the created object.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public PointPermit create(PointPermit pointPermit, int empId, int pointActionId) {
        Assert.notNull(pointPermit, pointPermit.getClass().getSimpleName() + " must not be null");
        return repository.save(pointPermit, empId, pointActionId);
    }

    /**
     * Updates an existing in the data base point permit
     * with inserted employee and point action.
     *
     * @param pointPermit   the point permit to update.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(PointPermit pointPermit, int empId, int pointActionId) throws NotFoundException {
        Assert.notNull(pointPermit, pointPermit.getClass().getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(pointPermit, empId, pointActionId), pointPermit.getId());
    }

    /**
     * Returns the list with all point permits by employee's id.
     *
     * @param empId the specified employee's id.
     * @return the list with all point permits by employee's id.
     * @see PointPermitServiceImpl#getAllByControlPointId(int)
     */
    @Override
    @Cacheable(cacheResolver = "cacheResolver")
    public List <PointPermit> getAllByEmpId(int empId) {
        return repository.getAllByEmpId(empId);
    }

    /**
     * Returns the list with all point permits by control point's id.
     *
     * @param ctrlPointId the specified control point's id.
     * @return the list with all point permits by control point's id.
     * @see PointPermitServiceImpl#getAllByEmpId(int)
     */
    @Override
    @Cacheable(cacheResolver = "cacheResolver")
    public List <PointPermit> getAllByControlPointId(int ctrlPointId) {
        return repository.getAllByControlPointId(ctrlPointId);
    }

    /**
     * Returns the list of point permits by employee's id and control point's id.
     *
     * @param empId       the employee's id.
     * @param ctrlPointId the control point's id.
     * @return the list of point permits by employee's id and control point's id.
     */
    @Override
    public List <PointPermit> getAllByEmpIdAndCtrlPointId(int empId, int ctrlPointId) {
        return repository.getAllByEmpIdAndCtrlPointId(empId, ctrlPointId);
    }

    /**
     * Constructs new {@code PointPermitServiceImpl} and set a specified point permit's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified point permit's repository implementation.
     */
    @Autowired
    public PointPermitServiceImpl(PointPermitRepository repository) {
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
