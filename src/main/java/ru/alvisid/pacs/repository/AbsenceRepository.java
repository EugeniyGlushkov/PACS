package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Absence;

import java.util.List;

/**
 * The generalized functional for absence's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface AbsenceRepository extends TypicalRepository<Absence> {
    /**
     * Returns the list with all absences by employee's id.
     *
     * @param id the employee's id.
     * @return the list with all absences by employee's id.
     */
    List<Absence> getAllByEmplId(int id);

    /**
     * Returns the list with all absences by id of the employee's department.
     *
     * @param id the department's id.
     * @return the list with all absences by id of the employee's department.
     */
    List<Absence> getAllByEmplDeptId(int id);
}
