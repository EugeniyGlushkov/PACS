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
     * @param empId the employee's id.
     * @return the employee's schedule by employee {@code id}.
     */
    EmpSchedule getByEmpId(int empId);
}
