package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Position;
import ru.alvisid.pacs.repository.PositionRepository;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.PositionService;

/**
 * Implementation of the {@code PositionService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see PositionService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class PositionServiceImpl
        extends AbstractCachedService <PositionRepository, Position> implements PositionService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "positions";

    /**
     * Constructs new {@code PositionServiceImpl} and set a specified position's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified position's repository implementation.
     */
    @Autowired
    public PositionServiceImpl(PositionRepository repository) {
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
