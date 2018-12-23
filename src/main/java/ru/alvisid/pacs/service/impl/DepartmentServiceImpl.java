package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.repository.impl.DataJpaDepartmentRepositoryImpl;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.DepartmentService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

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
        extends AbstractService<DataJpaDepartmentRepositoryImpl, Department> implements DepartmentService {

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

    @Override
    public String getCacheAlias() {
        return CACHE_ALIAS;
    }

    @CacheEvict(value = CACHE_ALIAS, allEntries = true)
    @Override
    public Department create(Department obj) {
        return super.create(obj);
    }

    @CacheEvict(value = CACHE_ALIAS, allEntries = true)
    @Override
    public void update(Department obj) throws NotFoundException {
        super.update(obj);
    }

    @CacheEvict(value = CACHE_ALIAS, allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        super.delete(id);
    }

    @Cacheable(CACHE_ALIAS)
    @Override
    public List<Department> getAll() {
        return super.getAll();
    }
}
