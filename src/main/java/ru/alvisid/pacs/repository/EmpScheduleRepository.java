package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.EmpSchedule;

/**
 * The generalized functional for employee schedule's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface EmpScheduleRepository extends TypicalRepository<EmpSchedule> {
    /**
     * Saves or updates a given object with inserted parameter.
     *
     * @param empSchedule the object to save or update.
     * @param empId       the employee's id, the employee will be inserted to the
     *                    saved object's {@code employee} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    EmpSchedule save(EmpSchedule empSchedule, int empId);

    /**
     * Returns the employee's schedule by employee {@code id}.
     *
     * @param empId the employee's id.
     * @return the employee's schedule by employee {@code id}.
     */
    EmpSchedule getByEmpId(int empId);
}
