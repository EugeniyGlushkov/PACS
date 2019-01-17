package ru.alvisid.pacs.service.impl;

import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Visitor;
import ru.alvisid.pacs.repository.impl.DataJpaVisitorRepositoryImpl;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.DepartmentService;
import ru.alvisid.pacs.service.VisitorService;

/**
 * Implementation of the {@code DepartmentService} interface.
 * Extends <b>AbstractService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see VisitorService
 * @see AbstractService
 */
@Service
public class VisitorServiceImpl
        extends AbstractService <DataJpaVisitorRepositoryImpl, Visitor> implements VisitorService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "visitors";

}
