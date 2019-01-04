package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Action;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The generalized functional for action's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface ActionRepository extends TypicalRepository<Action> {
    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param action        the object to save or update.
     * @param empId         the employee's id, the employee will be inserted to the
     *                      saved object's {@code employee} field.
     * @param pointActionId the point action's id, the point action will be inserted to the
     *                      saved object's {@code pointAction} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    Action save(Action action, int empId, int pointActionId);

    /**
     * Returns the list with all actions by employee's id.
     *
     * @param id the employee's id.
     * @return the list with all actions by employee's id.
     */
    List<Action> getAllByEmplId(int id);

    /**
     * Returns the list with all actions in the specified time interval.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @return the list with all actions in the specified time interval.
     */
    List<Action> getAllBetween(LocalDateTime start, LocalDateTime end);
}
