package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The specific functional for the action's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface ActionService extends TypicalService<Action> {
    /**
     * Creates and saves a given action in the data base
     * with inserted employee and point action.
     *
     * @param action        the point action to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @return the created object.
     */
    Action create(Action action, int empId, int pointActionId);

    /**
     * Updates an existing in the data base action
     * with inserted employee and point action.
     *
     * @param action        the point action to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(Action action, int empId, int pointActionId) throws NotFoundException;

    /**
     * Returns the list with all absences by employee's id.
     *
     * @param id the employee's id.
     * @return all actions which are done.
     */
    List<Action> getAllByEmplId(int id);

    /**
     * Returns all actions in the specified time interval.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @return all actions in the specified time interval sorted with specified sort.
     */
    List<Action> getAllBetween(LocalDateTime start, LocalDateTime end);
}
