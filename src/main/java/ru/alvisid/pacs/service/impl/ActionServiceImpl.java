package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.*;
import ru.alvisid.pacs.repository.ActionRepository;
import ru.alvisid.pacs.service.*;
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
        extends AbstractService <ActionRepository, Action> implements ActionService {
    /**
     * Formatted string for {@code IllegalActionException}'s message which shows:
     * employee's id, employee's last name, employee's card number, action type and control point.
     */
    public static final String MESSAGE_FORMAT = "Employee [id=%d, lastName='%s', cardNum=%d] " +
            "has no permit for action type [%s] " +
            "at control point [%s].";

    /**
     * Current {@code PointPermitService} implementation.
     */
    private PointPermitService pointPermitService;

    /**
     * Current {@code EmployeeService} implementation.
     */
    private EmployeeService employeeService;

    /**
     * Current {@code PointActionService} implementation.
     */
    private PointActionService pointActionService;

    /**
     * Creates and saves a specified action in the data base.
     *
     * @param action the specified action.
     * @return the created object.
     * @throws IllegalActionException if an employee has no permit for this action at the control point.
     */
    @Override
    public Action create(Action action) throws IllegalActionException {
        Assert.notNull(action, currentClass.getSimpleName() + " must not be null");
        Assert.notNull(action.getEmployee(), "Employee of the created" + action + " must not be null");
        Assert.notNull(action.getPointAction(), "Point action of the created" + action + " must not be null");
        checkPermit(action.getEmployee().getId(), action.getPointAction().getId());
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
        Assert.notNull(action, currentClass.getSimpleName() + " must not be null");
        Assert.notNull(action.getEmployee(), "Employee of the created" + action + " must not be null");
        Assert.notNull(action.getPointAction(), "Point action of the created" + action + " must not be null");
        checkPermit(action.getEmployee().getId(), action.getPointAction().getId());
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
        Assert.notNull(action, currentClass.getSimpleName() + " must not be null");
        checkPermit(empId, pointActionId);
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
        Assert.notNull(action, currentClass.getSimpleName() + " must not be null");
        checkPermit(empId, pointActionId);
        checkNotFoundWithId(repository.save(action, empId, pointActionId), action.getId());
    }

    /**
     * Returns the list with all absences by employee's id.
     *
     * @param id the employee's id.
     * @return all actions which are done.
     */
    @Override
    public List <Action> getAllByEmplId(int id) {
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
    public List <Action> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return repository.getAllBetween(start, end);
    }

    /**
     * Checks employee which does the specified action has permit for this action type at the control point.
     *
     * @param empId      the specified employee's id.
     * @param pointActId the specified point action's id.
     * @throws IllegalActionException if an employee has no permit for this action at the control point.
     */
    private void checkPermit(int empId, int pointActId) throws IllegalActionException {
        PointAction pointAction = pointActionService.getWithCtrlPoint(pointActId);
        PointPermit currentPointPermit =
                pointPermitService.getByEmpIdCtrlPointIdAndActType(empId,
                        pointAction.getControlPoint().getId(),
                        pointAction.getActionType());

        if (Objects.isNull(currentPointPermit)) {
            Employee employee = employeeService.getWithDeptAndPosition(empId);
            throw new IllegalActionException(String.format(MESSAGE_FORMAT,
                    empId, employee.getLastName(), employee.getCardNum(), pointAction.getActionType(), pointAction.getControlPoint()));
        }
    }

    /**
     * Constructs new {@code ActionServiceImpl} and sets a specified action's repository implementation
     * to the superclass's repository field, sets pointPermitService, employeeService and controlPointService fields.
     *
     * @param repository         the specified action's repository implementation.
     * @param pointPermitService the specified PointPermitService's repository implementation.
     * @param employeeService    the specified EmployeeService's repository implementation.
     * @param pointActionService the specified PointActionService's repository implementation.
     */
    @Autowired
    public ActionServiceImpl(ActionRepository repository,
                             PointPermitService pointPermitService,
                             EmployeeService employeeService,
                             PointActionService pointActionService) {
        super(repository);
        this.pointPermitService = pointPermitService;
        this.employeeService = employeeService;
        this.pointActionService = pointActionService;
    }
}
