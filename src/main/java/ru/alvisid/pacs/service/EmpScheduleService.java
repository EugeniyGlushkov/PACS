package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.EmpSchedule;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

/**
 * The specific functional for the employee schedule's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface EmpScheduleService extends TypicalService<EmpSchedule> {
    /**
     * Creates and saves a given employee schedule in the data base
     * with inserted employee.
     *
     * @param empSchedule the employee schedule to create.
     * @param empId the employee's id to insert.
     * @return the created object.
     */
    EmpSchedule create(EmpSchedule empSchedule, int empId);

    /**
     * Updates an existing in the data base employee schedule
     * with inserted employee.
     *
     * @param empSchedule the employee schedule to update.
     * @param empId the employee's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(EmpSchedule empSchedule, int empId) throws NotFoundException;

    /**
     * Returns an employee's schedule by given employee id.
     * If there are no employee's schedule by the specified employee id in a data base
     * then returns new employee's schedule which the employee by specified id based.
     * If there no department' schedule in the data base
     * then returns new employee's schedule: {@c employee}-field is initialized by specified empId,
     * start work time, end work time, start lunch time and lunch time are nulls.
     *
     * @param empId the employee id.
     * @return the employee schedule by given employee id.
     * null if there are no employee schedule with the employee id.
     * @throws NotFoundException if there are no employee by specified id in the data base.
     */
    EmpSchedule getByEmpId(int empId) throws NotFoundException;
}
