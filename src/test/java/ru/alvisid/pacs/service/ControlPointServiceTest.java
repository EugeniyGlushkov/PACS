package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.ControlPoint;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.ControlPointTestData;

import javax.validation.ConstraintViolationException;

import static util.TestUtil.assertMatch;

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
     * Sets the {@code ControlPointService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(ControlPointService service) {
        this.service = service;
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
