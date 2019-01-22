package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

/**
 * The specific functional for the department's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DepartmentService extends TypicalService <Department> {
    /**
     * Returns a department with filled fields: {@code weekEnds} and {@code deptSchedule}
     * by specified id.
     *
     * @param id the specified department's id.
     * @return the department with filled fields: {@code weekEnds} and {@code deptSchedule}.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    Department getWithWeekEndsAndSched(int id) throws NotFoundException;
}
