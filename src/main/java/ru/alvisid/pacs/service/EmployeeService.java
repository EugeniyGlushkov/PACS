package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

/**
 * The specific functional for the employee's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface EmployeeService extends TypicalService <Employee> {
    /**
     * Returns the list with all employees by department's id.
     *
     * @param deptId the department's id.
     * @return the list with all employees by department's id.
     */
    List <Employee> getAllByDeptId(int deptId);

    /**
     * Returns an employee by given email.
     *
     * @param email the specified email.
     * @return the employee by given email.
     * @throws NotFoundException if the employee with the specified email isn't found.
     */
    Employee getByEmail(String email) throws NotFoundException;
}
