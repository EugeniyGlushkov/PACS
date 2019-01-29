package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.ControlPoint;
import ru.alvisid.pacs.repository.ControlPointRepository;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.ControlPointService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFound;

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
@Service
public class ControlPointServiceImpl
        extends AbstractCachedService <ControlPointRepository, ControlPoint> implements ControlPointService {
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
    public ControlPointServiceImpl(ControlPointRepository repository) {
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

    /**
     * Returns a control point by the serial code.
     *
     * @param serialCode the specified serial code.
     * @return the control point by the given serial code.
     * @throws NotFoundException if the control point with the specified serial code isn't found.
     */
    @Override
    public ControlPoint getBySerialCode(String serialCode) throws NotFoundException {
        Assert.notNull(serialCode, "serial code must not be null");
        return checkNotFound(repository.getBySerialCode(serialCode), "serial code=" + serialCode);
    }
}
