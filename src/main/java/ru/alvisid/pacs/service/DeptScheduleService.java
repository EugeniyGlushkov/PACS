package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

/**
 * The specific functional for the department schedule's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DeptScheduleService extends TypicalService<DeptSchedule> {
    /**
     * Creates and saves a given department schedule in the data base
     * with inserted department.
     *
     * @param deptSchedule the department schedule to create.
     * @param deptId the department's id to insert.
     * @return the created object.
     */
    DeptSchedule create(DeptSchedule deptSchedule, int deptId);

    /**
     * Updates an existing in the data base department schedule
     * with inserted department.
     *
     * @param deptSchedule the department schedule to update.
     * @param deptId the department's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(DeptSchedule deptSchedule, int deptId) throws NotFoundException;

    /**
     * Returns a department schedule by given department id.
     *
     * @param deptId the department id.
     * @return the department schedule by given department id.
     * null if there are no department schedule with the deptId.
     */
    DeptSchedule getByDeptId(int deptId);
}
