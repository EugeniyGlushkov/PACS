package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.util.exceptions.IllegalActionException;
import testdata.ActionTestData;
import testdata.EmployeeTestData;
import static util.TestUtil.assertMatch;

import static testdata.PointActionTestData.*;

/**
 * Action's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class ActionServiceTest extends AbstractServiceTest<Action, ActionService> {
    /**
     * Constructs new <em>ActionServiceTest</em> object.
     */
    public ActionServiceTest() {
        super(new ActionTestData());
    }

    /**
     * Sets the {@code ActionService} to the superclass.
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
        thrown.expectMessage("Employee [id=" + createdAction.getEmployee().getId() +
                ", lastName=' " + createdAction.getEmployee().getLastName() + '\'' +
                ", cardNum=" + createdAction.getEmployee().getCardNum() +"] " +
                "has no permit for action type [" + createdAction.getPointAction().getActionType() + "] " +
                "at control point [" + createdAction.getPointAction().getControlPoint() + "].");
        service.create(createdAction);
    }

    /**
     * Checks the {@code IllegalActionException} when updated action has no permit for this action type at the control point.
     */
    @Test
    public void updateIllegalAction() {
        Action updatedAction = testData.getUpdated();
        updatedAction.setEmployee(EmployeeTestData.EMPLOYEE_2);
        updatedAction.setPointAction(POINT_ACTION_5);
        thrown.expect(IllegalActionException.class);
        thrown.expectMessage("Employee [id=" + updatedAction.getEmployee().getId() +
                ", lastName=' " + updatedAction.getEmployee().getLastName() + '\'' +
                ", cardNum=" + updatedAction.getEmployee().getCardNum() +"] " +
                "has no permit for action type [" + updatedAction.getPointAction().getActionType() + "] " +
                "at control point [" + updatedAction.getPointAction().getControlPoint() + "].");
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
}
