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
