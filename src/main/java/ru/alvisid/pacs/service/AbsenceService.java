package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Absence;

/**
 * The specific functional for the absence's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface AbsenceService extends TypicalService <Absence> {
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
}
