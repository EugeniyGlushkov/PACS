package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.ActionType;
import ru.alvisid.pacs.repository.impl.DataJpaActionTypeRepositoryImpl;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.ActionTypeService;

/**
 * Implementation of the {@code ActionTypeService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see ActionTypeService
 * @see AbstractCachedService
 * @see AbstractService
 */
public class ActionTypeServiceImpl extends AbstractCachedService<DataJpaActionTypeRepositoryImpl, ActionType> {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "actionTypes";

    /**
     * Constructs new {@code ActionTypeServiceImpl} and set a specified action type's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified position's repository implementation.
     */
    @Autowired
    public ActionTypeServiceImpl(DataJpaActionTypeRepositoryImpl repository) {
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
