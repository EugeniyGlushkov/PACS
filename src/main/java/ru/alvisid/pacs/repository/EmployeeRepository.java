package ru.alvisid.pacs.repository;


import ru.alvisid.pacs.model.Employee;

import java.util.List;

/**
 * The generalized functional for employee's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface EmployeeRepository extends TypicalRepository<Employee> {
    /**
     * Returns the list with all employees by deparmetnt's id.
     *
     * @param deptId the department's id.
     * @return the list with all employees by deparmetnt's id.
     */
    List<Employee> getAllByDeptId(int deptId);

    /**
     * Returns an employee by given email.
     *
     * @param email the specified email.
     * @return the employee by given email.
     */
    Employee getByEmail(String email);
}
