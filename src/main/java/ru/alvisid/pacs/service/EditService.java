package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Edit;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The specific functional for the edit's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface EditService extends TypicalService <Edit> {
    /**
     * Creates and saves a given edit in the data base
     * with inserted employee.
     *
     * @param edit  the edit to create.
     * @param empId the employee's id to insert.
     * @return the created object.
     */
    Edit create(Edit edit, int empId);

    /**
     * Updates an existing in the data base edit
     * with inserted employee.
     *
     * @param edit  the edit to create.
     * @param empId the employee's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(Edit edit, int empId) throws NotFoundException;

    /**
     * Returns all edits which are done by specific employee.
     * List is sorted by edit's time and edit's type.
     *
     * @param id the employee's id.
     * @return all edits which are done by specific employee.
     */
    List <Edit> getAllByEmpId(int id);

    /**
     * Returns all edits in the specified time interval.
     * List is sorted by edit's time, edit's type, employee's last name, first name and second name.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @return the list of the all edits between start time and end time.
     */
    List <Edit> getAllBetween(LocalDateTime start, LocalDateTime end);
}
