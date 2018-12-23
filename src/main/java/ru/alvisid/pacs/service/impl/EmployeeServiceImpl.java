package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.repository.impl.DataJpaEmployeeRepositoryImpl;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.EmployeeService;
import ru.alvisid.pacs.service.TypicalService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;
import static ru.alvisid.pacs.util.ValidationUtil.checkNotFound;

/**
 * Implementation of the {@code EmployeeService} interface.
 * Extends <b>AbstractService</b>'s functionality.
 *
 * @see EmployeeService
 * @see AbstractService
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Service
public class EmployeeServiceImpl
        extends AbstractService<DataJpaEmployeeRepositoryImpl, Employee> implements EmployeeService{
    private static final String CACHE_ALIAS = "employees";

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

    @Override
    public String getCacheAlias() {
        return CACHE_ALIAS;
    }

    /**
     * Returns all employees by department id .
     *
     * @param deptId the department's id.
     * @return list of all employees by department id.
     */
    @Override
    public List<Employee> getAllByDeptId(int deptId) {
        return repository.getAllByDeptId(deptId);
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
        return checkNotFound(repository.getByEmail(email), "Not found entity with email=" + email);
    }
}
