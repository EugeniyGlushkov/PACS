package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.repository.ActionRepository;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.ActionService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code ActionService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see ActionService
 * @see AbstractService
 */
public class ActionServiceImpl
        extends AbstractService<ActionRepository, Action> implements ActionService {
    /**
     * Creates and saves a given action in the data base
     * with inserted employee and point action.
     *
     * @param action        the point action to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @return the created object.
     */
    @Override
    public Action create(Action action, int empId, int pointActionId) {
        Assert.notNull(action, action.getClass().getSimpleName() + " must not be null");
        return repository.save(action, empId, pointActionId);
    }

    /**
     * Updates an existing in the data base action
     * with inserted employee and point action.
     *
     * @param action        the point action to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    public void update(Action action, int empId, int pointActionId) throws NotFoundException {
        Assert.notNull(action, action.getClass().getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(action, empId, pointActionId), action.getId());
    }

    /**
     * Returns the list with all absences by employee's id.
     *
     * @param id the employee's id.
     * @return all actions which are done.
     */
    @Override
    public List<Action> getAllByEmplId(int id) {
        return repository.getAllByEmplId(id);
    }

    /**
     * Returns all actions in the specified time interval.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @return all actions in the specified time interval sorted with specified sort.
     */
    @Override
    public List<Action> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return repository.getAllBetween(start, end);
    }

    /**
     * Constructs new {@code ActionServiceImpl} and set a specified action's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified action's repository implementation.
     */
    @Autowired
    public ActionServiceImpl(ActionRepository repository) {
        super(repository);
    }
}
