package ru.alvisid.pacs.repository;


import ru.alvisid.pacs.model.Employee;

import java.util.List;

/**
 * The interface with a generalized functional for employee's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface EmployeeRepository {
    /**
     * Saves or updates a given employee.
     *
     * @param employee employee to save or update.
     * @return a saved or update employee,
     * null - if there aren't updated employee in the repository.
     */
    Employee save(Employee employee);

    /**
     * Deletes the employee with specifiec id.
     *
     * @param id the specifiec id of a deleted employee.
     * @return true - if a employee with the specifiec id are deleted,
     * false - if there aren't the employee with the cpecifiec id in the DB.
     */
    boolean delete(int id);

    /**
     * Returns an employee with the cpecifiec id.
     *
     * @param id the specifiec id of employee to get.
     * @return a employee with the cpecifiec id,
     * null - if there aren't employee with cpecifiec id  in the DB.
     */
    Employee get(int id);

    /**
     * Returns list with all employees.
     *
     * @return list with all employees.
     */
    List<Employee> getAll();

    /**
     * Returns list with all employees by deparmetnt's id.
     *
     * @param deptId the department's id.
     * @return list with all employees by deparmetnt's id.
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
