package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.model.ControlPoint;
import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.ControlPointTestData;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static util.TestUtil.assertMatch;
import static testdata.ControlPointTestData.*;

/**
 * ControlPoint's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class ControlPointServiceTest extends AbstractServiceTest <ControlPoint, ControlPointService> {
    /**
     * Constructs new <em>PositionServiceTest</em> object.
     */
    public ControlPointServiceTest() {
        super(new ControlPointTestData());
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
     * The point action's service realization.
     */
    private PointActionService pointActionService;

    /**
     * Sets the {@code ControlPointService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(ControlPointService service) {
        this.service = service;
    }

    /**
     * Sets the {@code PointPermitService}, {@code ActionService} and {@code PointActionService}.
     *
     * @param pointPermitService the specified PointPermitService.
     * @param actionService the specified ActionService.
     * @param pointActionService the specified PointActionService.
     */
    @Autowired
    public void setSupportingServices(PointPermitService pointPermitService,
                                      ActionService actionService,
                                      PointActionService pointActionService) {
        this.pointPermitService = pointPermitService;
        this.actionService = actionService;
        this.pointActionService = pointActionService;
    }

    /**
     * Cleans point permits, actions and point actions tables in the data base and execute test method in the superclass.
     */
    @Override
    public void delete() {
        List <PointPermit> pointPermitsForDel = pointPermitService.getAll();
        List <Action> actionsForDel = actionService.getAll();
        List <PointAction> pointActionsForDel = pointActionService.getAll();

        for (PointPermit pp : pointPermitsForDel) {
            pointPermitService.delete(pp.getId());
        }

        for (Action act : actionsForDel) {
            actionService.delete(act.getId());
        }

        for (PointAction pointAction : pointActionsForDel) {
            pointActionService.delete(pointAction.getId());
        }

        super.delete();
    }

    /**
     * Checks the {@code DataAccessException} when control point with duplicate serial code create.
     */
    @Test
    public void createDuplicateSerialCode() {
        ControlPoint serialCodeDuplicateNew = testData.getNew();
        serialCodeDuplicateNew.setSerialCode(testData.getGotten().getSerialCode());
        thrown.expect(DataAccessException.class);
        service.create(serialCodeDuplicateNew);
    }

    /**
     * Checks the {@code DataAccessException} when control point with duplicate serial code update.
     */
    @Test
    public void updateDuplicateSerialCode() {
        ControlPoint serialCodeDuplicateUpd = testData.getUpdated();
        serialCodeDuplicateUpd.setSerialCode(CONTROL_POINT_3.getSerialCode());
        thrown.expect(DataAccessException.class);
        service.update(serialCodeDuplicateUpd);
    }

    /**
     * Checks matching the actual gotten by serial code value from DB to the expected gotten value from {@code testData}.
     */
    @Test
    public void getBySerialCode() {
        ControlPoint expectedControlPoint = testData.getGotten();
        ControlPoint actualControlPoint = service.getBySerialCode(expectedControlPoint.getSerialCode());
        assertMatch(testData.IGNORING_FIELDS, actualControlPoint, expectedControlPoint);
    }

    /**
     * Checks the {@code NotFoundException} when there are no object with specified serial code to getting in the DB.
     */
    @Test
    public void getBySerialCodeNotFound() {
        String notFoundSerialCode = "notFound";
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with serial code=" + notFoundSerialCode);
        service.getBySerialCode(notFoundSerialCode);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new ControlPoint(null, "Test validate, serial code is null")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint("", "Test validate, serial code is empty")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint("    ", "Test validate, serial code is blank")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint("A", "Test validate, serial code's size < 2")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint(
                        "Test validate, serial code's size > 50" +
                                "Test validate, serial code's size > 50",
                        "Test validate, serial code's size > 50")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint("Test validate, description is null", null)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint("Test validate, description is empty", "")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint("Test validate, description is blank", "    ")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ControlPoint("Test validate, description's size < 2", "A")),
                ConstraintViolationException.class);
    }
}
