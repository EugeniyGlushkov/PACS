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
     * Returns a department employee by given employee id.
     *
     * @param empId the employee id.
     * @return the employee schedule by given employee id.
     * null if there are no employee schedule with the employee id.
     */
    EmpSchedule getByEmpId(int empId);
}
