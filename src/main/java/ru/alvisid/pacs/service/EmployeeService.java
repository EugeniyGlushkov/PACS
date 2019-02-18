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
    Employee create(Employee employee, int deptId, int positionId);

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
    void update(Employee employee, int deptId, int positionId) throws NotFoundException;

    /**
     * Returns the list with all employees by department's id.
     *
     * @param deptId the department's id.
     * @return the list with all employees by department's id.
     */
    List <Employee> getAllByDeptId(int deptId);

    /**
     * Returns the list with all employees by position's id.
     *
     * @param posId the position's id.
     * @return the list with all employees by position's id.
     */
    List <Employee> getAllByPositionId(int posId);

    /**
     * Returns an employee by given email.
     *
     * @param email the specified email.
     * @return the employee by given email.
     * @throws NotFoundException if the employee with the specified email isn't found.
     */
    Employee getByEmail(String email) throws NotFoundException;

    /**
     * Returns an employee with filled fields: {@code department} and {@code position}
     * by specified id.
     *
     * @param id the specified employee's id.
     * @return the employee with filled fields: {@code department} and {@code position}.
     * @throws NotFoundException if the entity with the specified id isn't found
     */
    Employee getWithDeptAndPosition(int id) throws NotFoundException;
}
