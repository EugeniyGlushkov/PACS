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
     * Saves or updates a given object with inserted parameters.
     *
     * @param employee   the object to save or update.
     * @param deptId     department's id, the department will be inserted to the object's
     *                   {@code department} field.
     * @param positionId position's id, the position will be inserted to the object's
     *                   {@code position} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    Employee save(Employee employee, int deptId, int positionId);

    /**
     * Returns the list with all employees by department's id.
     *
     * @param deptId the department's id.
     * @return the list with all employees by department's id.
     */
    List<Employee> getAllByDeptId(int deptId);

    /**
     * Returns the list with all employees by position's id.
     *
     * @param posId the position's id.
     * @return the list with all employees by position's id.
     */
    List<Employee> getAllByPositionId(int posId);

    /**
     * Returns an employee by given email.
     *
     * @param email the specified email.
     * @return the employee by given email.
     */
    Employee getByEmail(String email);

    /**
     * Returns an employee with filled fields: {@code department}, {@code position} and {@code chief}
     * by specified id.
     *
     * @param id the specified employee's id.
     * @return the employee with filled fields: {@code department}, {@code position} and {@code chief}.
     */
    Employee getWithDeptPositionAndChief(int id);
}
