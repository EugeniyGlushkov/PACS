package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.repository.ActionRepository;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.ActionService;
import ru.alvisid.pacs.service.PointPermitService;
import ru.alvisid.pacs.util.exceptions.IllegalActionException;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
@Service
public class ActionServiceImpl
        extends AbstractService<ActionRepository, Action> implements ActionService {
    /**
     * Current {@code PointPermitService} implementation.
     */
    private PointPermitService pointPermitService;

    /**
     * Creates and saves a specified action in the data base.
     *
     * @param action the specified action.
     * @return the created object.
     * @throws IllegalActionException if an employee has no permit for this action at the control point.
     */
    @Override
    public Action create(Action action) throws IllegalActionException {
        checkPermit(action);
        return super.create(action);
    }

    /**
     * Updates an existing in the data base action.
     *
     * @param action the action to create.
     * @throws NotFoundException      if there aren't updated object in the data base.
     * @throws IllegalActionException if an employee has no permit for this action at the control point.
     */
    @Override
    public void update(Action action) throws NotFoundException, IllegalActionException {
        checkPermit(action);
        super.update(action);
    }

    /**
     * Creates and saves a given action in the data base
     * with inserted employee and point action.
     *
     * @param action        the point action to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @return the created object.
     * @throws IllegalActionException if an employee has no permit for this action at the control point.
     */
    @Override
    public Action create(Action action, int empId, int pointActionId) throws IllegalActionException {
        Assert.notNull(action, action.getClass().getSimpleName() + " must not be null");
        checkPermit(action);
        return repository.save(action, empId, pointActionId);
    }

    /**
     * Updates an existing in the data base action
     * with inserted employee and point action.
     *
     * @param action        the point action to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @throws NotFoundException      if there aren't updated object in the data base.
     * @throws IllegalActionException if an employee has no permit for this action at the control point.
     */
    @Override
    public void update(Action action, int empId, int pointActionId) throws NotFoundException, IllegalActionException {
        Assert.notNull(action, action.getClass().getSimpleName() + " must not be null");
        checkPermit(action);
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
     * Checks employee which does the specified action has permit for this action type at the control point.
     *
     * @param action the specified action.
     * @throws IllegalActionException if an employee has no permit for this action at the control point.
     */
    private void checkPermit(Action action) throws IllegalActionException {
        PointPermit currentPointPermit = pointPermitService.getByEmpIdCtrlPointIdAndActType(action.getEmployee().getId(),
                action.getPointAction().getControlPoint().getId(),
                action.getPointAction().getActionType());

        if (!Objects.isNull(currentPointPermit)) {
            throw new IllegalActionException("Employee [" + action.getEmployee() + "] " +
                    "has no permit for action type [" + action.getPointAction().getActionType() + "] " +
                    "at control point [" + action.getPointAction().getControlPoint() + "].");
        }
    }

    /**
     * Constructs new {@code ActionServiceImpl} and set a specified action's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified action's repository implementation.
     */
    @Autowired
    public ActionServiceImpl(ActionRepository repository, PointPermitService pointPermitService) {
        super(repository);
        this.pointPermitService = pointPermitService;
    }
}
