package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Edit;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The generalized functional for edit's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface EditRepository extends TypicalRepository<Edit> {
    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param edit     the object to save or update.
     * @param empId    the employee's id, the employee will be inserted to the
     *                 saved object's {@code employee} field.
     * @param editType the edit type's string, the edit type will be inserted to the
     *                 saved object's {@code editType} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    Edit save(Edit edit, int empId, String editType);

    /**
     * Returns the list with all edits by employee's id.
     *
     * @param id the employee's id.
     * @return the list with all edits by employee's id.
     */
    List<Edit> getAllByEmpId(int id);

    /**
     * Returns the list with all edits in the specified time interval.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @return the list with all edits in the specified time interval.
     */
    List<Edit> getAllBetween(LocalDateTime start, LocalDateTime end);
}
