package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.model.ActionType;
import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.PointActionTestData;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static testdata.ControlPointTestData.CONTROL_POINT_3;
import static util.TestUtil.assertMatch;
import static testdata.PointActionTestData.*;

/**
 * PointAction's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class PointActionServiceTest extends AbstractServiceTest <PointAction, PointActionService> {
    /**
     * Constructs new <em>PointActionServiceTest</em> object.
     */
    public PointActionServiceTest() {
        super(new PointActionTestData());
    }

    /**
     * The point action's service realization.
     */
    private PointPermitService pointPermitService;

    /**
     * The point action's service realization.
     */
    private ActionService actionService;

    /**
     * Sets the {@code PointActionService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(PointActionService service) {
        this.service = service;
    }

    /**
     * Sets the {@code PointPermitService} and {@code ActionService}.
     *
     * @param pointPermitService the specified PointPermitService.
     * @param actionService      the specified ActionService.
     */
    @Autowired
    public void setSupportingServices(PointPermitService pointPermitService,
                                      ActionService actionService) {
        this.pointPermitService = pointPermitService;
        this.actionService = actionService;
    }

    /**
     * Cleans point permits and actions tables in the data base and execute test method in the superclass.
     */
    @Override
    public void delete() {
        List <PointPermit> pointPermitsForDel = pointPermitService.getAll();
        List <Action> actionsForDel = actionService.getAll();

        for (PointPermit pp : pointPermitsForDel) {
            pointPermitService.delete(pp.getId());
        }

        for (Action act : actionsForDel) {
            actionService.delete(act.getId());
        }

        super.delete();
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code control point} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithCtrlPointId() {
        PointAction expectedPointAction = testData.getNew();
        int expectedCtrlPointId = expectedPointAction.getControlPoint().getId();
        expectedPointAction.setControlPoint(null);
        PointAction actualPointAction = service.create(expectedPointAction, expectedCtrlPointId);
        expectedPointAction = testData.getNew();
        expectedPointAction.setId(actualPointAction.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedPointAction));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code control point} in the DB;
     * checks matching the actual value to the expected value from {@code testData}.
     */
    @Test
    public void updateWithCtrlPointId() {
        PointAction expectedPointAction = testData.getUpdated();
        int expectedCtrlPointId = expectedPointAction.getControlPoint().getId();
        expectedPointAction.setControlPoint(null);
        service.update(expectedPointAction, expectedCtrlPointId);
        PointAction actualPointAction = service.get(expectedPointAction.getId());
        expectedPointAction = testData.getUpdated();
        assertMatch(testData.IGNORING_FIELDS, actualPointAction, expectedPointAction);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted control point) in the DB.
     */
    @Test
    public void updateWithCtrlPoinIdNotFound() {
        PointAction updatedPointAction = testData.getUpdated();
        updatedPointAction.setId(testData.NOT_FOUND_ID);
        int ctrlPointId = updatedPointAction.getControlPoint().getId();
        updatedPointAction.setControlPoint(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(updatedPointAction, ctrlPointId);
    }

    /**
     * Checks the actual gotten by control point's id list of the all objects from DB to
     * the expected gotten by control point's id list of the all objects from {@code testData}.
     */
    @Test
    public void getAllByControlPointId() {
        int expectedCtrlPointId = POINT_ACTION_3.getControlPoint().getId();
        assertMatch(testData.IGNORING_FIELDS, service.getAllByControlPointId(expectedCtrlPointId), POINT_ACTION_3, POINT_ACTION_4);
    }

    /**
     * Checks matching the actual gotten value from DB to the expected gotten value from {@code testData}
     * without ignoring fields.
     */
    @Test
    public void getWithCtrlPoint() {
        PointAction expectedPointAction = testData.getGotten();
        PointAction actualPointAction = service.getWithCtrlPoint(expectedPointAction.getId());
        assertMatch(actualPointAction, expectedPointAction);
    }

    /**
     * Checks the {@code NotFoundException} when there are no object with the specified id in the DB.
     */
    @Test
    public void getWithCtrlPointNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.getWithCtrlPoint(testData.NOT_FOUND_ID);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new PointAction(null, ActionType.LOCK)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new PointAction(CONTROL_POINT_3, null)),
                ConstraintViolationException.class);
    }
}
