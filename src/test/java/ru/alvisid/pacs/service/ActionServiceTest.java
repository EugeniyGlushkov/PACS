package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.*;
import ru.alvisid.pacs.service.impl.ActionServiceImpl;
import ru.alvisid.pacs.util.exceptions.IllegalActionException;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.ActionTestData;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import static util.TestUtil.assertMatch;

import static testdata.PointActionTestData.*;
import static testdata.EmployeeTestData.*;
import static testdata.ActionTestData.*;

/**
 * Action's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class ActionServiceTest extends AbstractServiceTest <Action, ActionService> {
    /**
     * Constructs new <em>ActionServiceTest</em> object.
     */
    public ActionServiceTest() {
        super(new ActionTestData());
    }

    /**
     * Sets the {@code ActionService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(ActionService service) {
        this.service = service;
    }

    /**
     * Checks the {@code IllegalActionException} when created action has no permit for this action type at the control point.
     */
    @Test
    public void createIllegalAction() {
        Action createdAction = testData.getNew();
        createdAction.setPointAction(POINT_ACTION_5);
        thrown.expect(IllegalActionException.class);
        thrown.expectMessage(String.format(ActionServiceImpl.MESSAGE_FORMAT,
                createdAction.getEmployee().getId(),
                createdAction.getEmployee().getLastName(),
                createdAction.getEmployee().getCardNum(),
                createdAction.getPointAction().getActionType(),
                createdAction.getPointAction().getControlPoint()));
        service.create(createdAction);
    }

    /**
     * Checks the {@code IllegalActionException} when updated action has no permit for this action type at the control point.
     */
    @Test
    public void updateIllegalAction() {
        Action updatedAction = testData.getUpdated();
        updatedAction.setEmployee(EMPLOYEE_2);
        updatedAction.setPointAction(POINT_ACTION_5);
        thrown.expect(IllegalActionException.class);
        thrown.expectMessage(String.format(ActionServiceImpl.MESSAGE_FORMAT,
                updatedAction.getEmployee().getId(),
                updatedAction.getEmployee().getLastName(),
                updatedAction.getEmployee().getCardNum(),
                updatedAction.getPointAction().getActionType(),
                updatedAction.getPointAction().getControlPoint()));
        service.update(updatedAction);
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code employee} and {@code point action} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithEmpIdAndPointActId() {
        Action expectedAction = testData.getNew();
        int expectedEmpId = expectedAction.getEmployee().getId();
        int expectedPointActId = expectedAction.getPointAction().getId();
        expectedAction.setEmployee(null);
        expectedAction.setPointAction(null);
        Action actualAction = service.create(expectedAction, expectedEmpId, expectedPointActId);
        expectedAction = testData.getNew();
        expectedAction.setId(actualAction.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedAction));
    }

    /**
     * Checks the {@code IllegalActionException} when created action with inserted employee and point action
     * has no permit for this action type at the control point.
     */
    @Test
    public void createWithEmpIdAndPointActIdIllegalAction() {
        Action createdAction = testData.getNew();
        Employee insertedEmployee = createdAction.getEmployee();
        PointAction insertedPointAction = POINT_ACTION_5;
        createdAction.setEmployee(null);
        createdAction.setPointAction(null);
        thrown.expect(IllegalActionException.class);
        thrown.expectMessage(String.format(ActionServiceImpl.MESSAGE_FORMAT,
                insertedEmployee.getId(),
                insertedEmployee.getLastName(),
                insertedEmployee.getCardNum(),
                insertedPointAction.getActionType(),
                insertedPointAction.getControlPoint()));
        service.update(createdAction, insertedEmployee.getId(), insertedPointAction.getId());
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code employee} and {@code point action} in the DB;
     * checks matching the actual value to the expected value from {@code testData}.
     */
    @Test
    public void updateWithEmpIdAndPointActId() {
        Action expectedAction = testData.getUpdated();
        int expectedEmpId = expectedAction.getEmployee().getId();
        int expectedPointActId = expectedAction.getPointAction().getId();
        expectedAction.setEmployee(null);
        expectedAction.setPointAction(null);
        service.update(expectedAction, expectedEmpId, expectedPointActId);
        Action actualAction = service.get(expectedAction.getId());
        expectedAction = testData.getUpdated();
        assertMatch(testData.IGNORING_FIELDS, actualAction, expectedAction);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted employee and point action) in the DB.
     */
    @Test
    public void updateWithEmpIdAndPointActIdNotFound() {
        Action action = testData.getUpdated();
        int empId = action.getEmployee().getId();
        int pointActId = action.getPointAction().getId();
        action.setId(testData.NOT_FOUND_ID);
        action.setEmployee(null);
        action.setPointAction(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(action, empId, pointActId);
    }

    /**
     * Checks the {@code IllegalActionException} when updated action with inserted employee and point action
     * has no permit for this action type at the control point.
     */
    @Test
    public void updateWithEmpIdAndPointActIdIllegalAction() {
        Action updatedAction = testData.getUpdated();
        Employee insertedEmployee = EMPLOYEE_2;
        PointAction insertedPointAction = POINT_ACTION_5;
        updatedAction.setEmployee(null);
        updatedAction.setPointAction(null);
        thrown.expect(IllegalActionException.class);
        thrown.expectMessage(String.format(ActionServiceImpl.MESSAGE_FORMAT,
                insertedEmployee.getId(),
                insertedEmployee.getLastName(),
                insertedEmployee.getCardNum(),
                insertedPointAction.getActionType(),
                insertedPointAction.getControlPoint()));
        service.update(updatedAction, insertedEmployee.getId(), insertedPointAction.getId());
    }

    /**
     * Checks the actual gotten list of the all objects gotten by employee's id from DB to
     * the expected gotten values of the objects from {@code testData}.
     */
    @Test
    public void getAllByEmplId() {
        List <Action> actualActionsByEmpId = service.getAllByEmplId(EMPLOYEE_4.getId());
        assertMatch(testData.IGNORING_FIELDS, actualActionsByEmpId, ACTION_4, ACTION_5, ACTION_7);
    }

    /**
     * Checks the actual gotten list of the all objects gotten between start time and end time from DB to
     * the expected gotten values of the objects from {@code testData}.
     */
    @Test
    public void getAllBetween() {
        List <Action> actualActionsBetween = service.getAllBetween(
                LocalDateTime.of(2018, 7, 4, 7, 45, 32),
                LocalDateTime.of(2018, 7, 4, 12, 56, 44));
        assertMatch(actualActionsBetween, ACTION_1, ACTION_2, ACTION_6, ACTION_5, ACTION_3);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Action(null, POINT_ACTION_2,
                        LocalDateTime.of(2018, 7, 4, 17, 30, 16))),
                IllegalArgumentException.class);
        validateRootCause(() -> service.create(new Action(EMPLOYEE_2, null,
                        LocalDateTime.of(2018, 7, 4, 17, 30, 16))),
                IllegalArgumentException.class);
        validateRootCause(() -> service.create(new Action(EMPLOYEE_2, POINT_ACTION_2,
                        null)),
                ConstraintViolationException.class);
    }
}
