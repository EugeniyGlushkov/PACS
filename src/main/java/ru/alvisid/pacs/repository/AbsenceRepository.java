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
     * Saves or updates a given object with inserted parameters.
     *
     * @param absence         the object to save or update.
     * @param empId           the employee's id, the employee will be inserted to the
     *                        saved object's {@code employee} field.
     * @param absenceReasonId absence reason's id, the absence reason will be
     *                        inserted to the saved object's {@code absenceReason} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    Absence save(Absence absence, int empId, int absenceReasonId);


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
