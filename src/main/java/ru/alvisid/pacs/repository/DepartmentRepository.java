package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Department;

import java.util.List;

/**
 * The generalized functional for department's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DepartmentRepository extends TypicalRepository<Department> {
    /**
     * Returns a department with filled fields: {@code weekEnds} and {@code deptSchedule}
     * by specified id.
     *
     * @param id the specified department's id.
     * @return the department with filled fields: {@code weekEnds} and {@code deptSchedule}.
     */
    Department getWithWeekEndsAndSched(int id);
}
