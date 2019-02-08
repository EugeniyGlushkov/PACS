package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Absence;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

/**
 * The specific functional for the absence's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface AbsenceService extends TypicalService<Absence> {
    /**
     * Creates and saves a given absence in the data base
     * with inserted employee and absence reason.
     *
     * @param absence         the absence to create.
     * @param empId           the employee's id to insert.
     * @param absenceReasonId absence reason's id to insert.
     * @return the created object.
     */
    Absence create(Absence absence, int empId, int absenceReasonId);

    /**
     * Updates an existing in the data base absence
     * with inserted employee and absence reason.
     *
     * @param absence         the absence to update.
     * @param empId           the employee's id to insert.
     * @param absenceReasonId absence reason's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(Absence absence, int empId, int absenceReasonId) throws NotFoundException;

    /**
     * Returns the list with all absences by employee's id.
     *
     * @param id the employee's id.
     * @return the list with all absences by employee's id.
     * @see AbsenceService#getAllByEmplDeptId(int)
     */
    List<Absence> getAllByEmplId(int id);

    /**
     * Returns the list with all absences by id of the employee's department.
     *
     * @param id the department's id.
     * @return the list with all absences by id of the employee's department.
     * @see AbsenceService#getAllByEmplId(int)
     */
    List<Absence> getAllByEmplDeptId(int id);
}
