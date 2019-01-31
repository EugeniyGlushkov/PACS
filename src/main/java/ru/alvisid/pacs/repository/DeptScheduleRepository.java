package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.DeptSchedule;

import java.util.List;

/**
 * The generalized functional for department schedule's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DeptScheduleRepository extends TypicalRepository<DeptSchedule> {
    /**
     * Saves or updates a given object with inserted parameter.
     *
     * @param schedule the object to save or update.
     * @param deptId   department's id, the department will be inserted to the object's
     *                 {@code department} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    DeptSchedule save(DeptSchedule schedule, int deptId);

    /**
     * Returns the department's schedule by department {@code id}.
     *
     * @param deptId the department's id.
     * @return the department's schedule by department {@code id},
     * null - if there aren't department schedule with specified department's id in the DB.
     */
    DeptSchedule getByDeptId(int deptId);
}
