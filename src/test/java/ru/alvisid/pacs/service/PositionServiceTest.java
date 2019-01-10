package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Position;
import testdata.PositionTestData;

import javax.validation.ConstraintViolationException;

/**
 * Position's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class PositionServiceTest extends AbstractServiceTest <Position, PositionService> {
    /**
     * Constructs new <em>PositionServiceTest</em> object.
     */
    public PositionServiceTest() {
        super(new PositionTestData());
    }

    /**
     * Sets the {@code PositionService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(PositionService service) {
        super.service = service;
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Position(null, "Test validate, position is null")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("", "Test validate, position is empty")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("    ", "Test validate, position is blank")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("A", "Test validate, position's size < 2")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position(
                        "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255",
                        "Test validate, position's size > 255")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description is null", null)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description is empty", "")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description is blank", "    ")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description's size < 2", "A")),
                ConstraintViolationException.class);
    }
}
