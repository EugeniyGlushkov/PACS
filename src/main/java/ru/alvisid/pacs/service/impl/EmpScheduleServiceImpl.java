package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.model.EmpSchedule;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.repository.EmpScheduleRepository;
import ru.alvisid.pacs.service.*;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.Objects;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code EmpScheduleService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see EmpScheduleService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class EmpScheduleServiceImpl
        extends AbstractCachedService<EmpScheduleRepository, EmpSchedule> implements EmpScheduleService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "empSchedules";

    /**
     * A {@code DeptScheduleService} implementation.
     */
    private DeptScheduleService deptScheduleService;

    /**
     * A {@code EmployeeService} implementation.
     */
    private EmployeeService employeeService;

    /**
     * Creates and saves a given employee schedule in the data base
     * with inserted employee.
     *
     * @param empSchedule the employee schedule to create.
     * @param empId       the employee's id to insert.
     * @return the created object.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public EmpSchedule create(EmpSchedule empSchedule, int empId) {
        Assert.notNull(empSchedule, currentClass.getSimpleName() + " must not be null");
        return repository.save(empSchedule, empId);
    }

    /**
     * Updates an existing in the data base employee schedule
     * with inserted employee.
     *
     * @param empSchedule the employee schedule to update.
     * @param empId       the employee's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(EmpSchedule empSchedule, int empId) throws NotFoundException {
        Assert.notNull(empSchedule, currentClass.getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(empSchedule, empId), empSchedule.getId());
    }

    /**
     * Returns an employee's schedule by given employee id.
     * If there are no employee's schedule by the specified employee id in a data base
     * then returns new employee's schedule which the employee by specified id based.
     * If there no department' schedule in the data base
     * then returns new employee's schedule: {@code employee}-field is initialized by specified empId,
     * start work time, end work time, start lunch time and lunch time are nulls.
     *
     * @param empId the employee id.
     * @return the employee schedule by given employee id.
     * null if there are no employee schedule with the employee id.
     * @throws NotFoundException if there are no employee by specified id in the data base.
     */
    @Override
    public EmpSchedule getByEmpId(int empId) throws NotFoundException {
        EmpSchedule empSchedule = repository.getByEmpId(empId);

        if (Objects.nonNull(empSchedule)) {
            return empSchedule;
        }

        Employee employee = employeeService.get(empId);
        DeptSchedule deptSchedule = deptScheduleService.getByDeptId(employee.getDepartment().getId());

        if (Objects.nonNull(deptSchedule)) {
            return EmpSchedule.valueOf(employee, deptSchedule);
        }

        return new EmpSchedule(employee, null, null, null, null);
    }

    /**
     * Constructs new {@code EmpScheduleServiceImpl}, set a specified employee schedule's repository implementation
     * to the superclass's repository field and department schedule's service.
     *
     * @param repository          the specified employee schedule's repository implementation.
     * @param deptScheduleService the department schedule's service implementation.
     * @param employeeService     the employee's service implementation.
     */
    @Autowired
    public EmpScheduleServiceImpl(EmpScheduleRepository repository,
                                  DeptScheduleService deptScheduleService, EmployeeService employeeService) {
        super(repository);
        this.deptScheduleService = deptScheduleService;
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
