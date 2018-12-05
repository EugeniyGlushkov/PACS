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
     * Returns the employee's schedule by employee {@code id}.
     *
     * @param emplId the employee's id.
     * @return the employee's schedule by employee {@code id}.
     */
    EmpSchedule getByEmplId(int emplId);
}
