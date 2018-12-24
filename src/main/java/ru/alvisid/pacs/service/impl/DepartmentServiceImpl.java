package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.repository.impl.DataJpaDepartmentRepositoryImpl;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.DepartmentService;
import ru.alvisid.pacs.util.cache.Cacheable;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import ru.alvisid.pacs.util.profileResolver.Profiles;

import java.util.List;

/**
 * Implementation of the {@code DepartmentService} interface.
 * Extends <b>AbstractService</b>'s functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see DepartmentService
 * @see AbstractService
 */
@Service
public class DepartmentServiceImpl
        extends AbstractService <DataJpaDepartmentRepositoryImpl, Department> implements DepartmentService, Cacheable {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "departments";

    /**
     * Constructs new {@code DepartmentServiceImpl} and set a specified department's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified department's repository implementation.
     */
    @Autowired
    public DepartmentServiceImpl(DataJpaDepartmentRepositoryImpl repository) {
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
