package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.ControlPoint;
import ru.alvisid.pacs.repository.impl.DataJpaControlPointRepositoryImpl;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.ControlPointService;

/**
 * Implementation of the {@code ControlPointService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see ControlPointService
 * @see AbstractCachedService
 * @see AbstractService
 */
public class ControlPointServiceImpl
        extends AbstractCachedService <DataJpaControlPointRepositoryImpl, ControlPoint> implements ControlPointService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "controlPoints";

    /**
     * Constructs new {@code ControlPointServiceImpl} and set a specified control point's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified position's repository implementation.
     */
    @Autowired
    public ControlPointServiceImpl(DataJpaControlPointRepositoryImpl repository) {
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
