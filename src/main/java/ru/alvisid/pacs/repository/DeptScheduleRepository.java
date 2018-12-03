package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.DeptSchedule;

import java.util.List;

/**
 * The generalized functional for schedule's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DeptScheduleRepository extends TypicalRepository<DeptSchedule> {
    /**
     * Returns the department's schedule by department {@code id}.
     *
     * @param deptId the department's id.
     * @return the department's schedule by department {@code id}.
     */
    DeptSchedule getByDeptId(int deptId);
}
