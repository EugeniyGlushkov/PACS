package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.repository.DepartmentRepository;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.DepartmentService;
import ru.alvisid.pacs.service.EmployeeService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code DepartmentService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see DepartmentService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class DepartmentServiceImpl
        extends AbstractCachedService<DepartmentRepository, Department> implements DepartmentService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "departments";

    /**
     * Employee service's realization.
     */
    private EmployeeService employeeService;

    @Override
    public void delete(int id) throws NotFoundException {
        if (!employeeService.getAllByDeptId(id).isEmpty()) {
            throw new RuntimeException("Can not delete department with id=" + id + ", it must be empty.");
        }

        super.delete(id);
    }

    /**
     * Returns a department with filled fields: {@code weekEnds} and {@code deptSchedule}
     * by specified id.
     *
     * @param id the specified department's id.
     * @return the department with filled fields: {@code weekEnds} and {@code deptSchedule}.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    @Override
    public Department getWithWeekEndsAndSched(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithWeekEndsAndSched(id), id);
    }

    /**
     * Constructs new {@code DepartmentServiceImpl} and set a specified department's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified department's repository implementation.
     */
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository, EmployeeService employeeService) {
        super(repository);
        this.employeeService = employeeService;
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
