package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.repository.impl.DataJpaEmployeeRepositoryImpl;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.EmployeeService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFound;
import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code EmployeeService} interface.
 * Extends <b>AbstractService</b>'s and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see EmployeeService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class EmployeeServiceImpl
        extends AbstractCachedService <DataJpaEmployeeRepositoryImpl, Employee> implements EmployeeService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "employees";

    /**
     * Creates and saves a given employee in the data base
     * with inserted department and position.
     *
     * @param employee   the object to save or update.
     * @param deptId     a department's id, the department will be inserted to the object's
     *                   {@code department} field.
     * @param positionId a position's id, the position will be inserted to the object's
     *                   {@code position} field.
     * @return the created object.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public Employee create(Employee employee, int deptId, int positionId) {
        Assert.notNull(employee, currentClass.getSimpleName() + " must not be null");
        return repository.save(employee, deptId, positionId);
    }

    /**
     * Updates an existing in the data base employee
     * with inserted department and position.
     *
     * @param employee   the object to save or update.
     * @param deptId     a department's id, the department will be inserted to the object's
     *                   {@code department} field.
     * @param positionId a position's id, the position will be inserted to the object's
     *                   {@code position} field.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(Employee employee, int deptId, int positionId) throws NotFoundException {
        Assert.notNull(employee, currentClass.getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(employee, deptId, positionId), employee.getId());
    }

    /**
     * Returns all employees by department id .
     *
     * @param deptId the department's id.
     * @return list of all employees by department id.
     */
    @Override
    @Cacheable(cacheResolver = "cacheResolver")
    public List <Employee> getAllByDeptId(int deptId) {
        return repository.getAllByDeptId(deptId);
    }

    /**
     * Returns all employees by position id .
     *
     * @param posId the position's id.
     * @return list of all employees by position id.
     */
    @Override
    @Cacheable(cacheResolver = "cacheResolver")
    public List <Employee> getAllByPositionId(int posId) {
        return repository.getAllByPositionId(posId);
    }

    /**
     * Returns an employee by given email.
     *
     * @param email the specified email.
     * @return the employee by given email.
     * @throws NotFoundException if an employee by the specified email is not found.
     */
    @Override
    public Employee getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    /**
     * Returns an employee with filled fields: {@code department} and {@code position}
     * by specified id.
     *
     * @param id the specified employee's id.
     * @return the employee with filled fields: {@code department} and {@code position}.
     * @throws NotFoundException if the entity with the specified id isn't found
     */
    @Override
    public Employee getWithDeptAndPosition(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithDeptAndPosition(id), id);
    }

    /**
     * Constructs new {@code EmployeeServiceImpl} and set a specified employee's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified employee's repository implementation.
     */
    @Autowired
    public EmployeeServiceImpl(DataJpaEmployeeRepositoryImpl repository) {
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
