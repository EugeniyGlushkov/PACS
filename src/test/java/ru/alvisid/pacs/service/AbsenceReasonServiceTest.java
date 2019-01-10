package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.AbsenceReason;
import testdata.AbsenceReasonTestData;
import testdata.AbstractTestData;

import javax.validation.ConstraintViolationException;

/**
 * AbsenceReason's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class AbsenceReasonServiceTest extends AbstractServiceTest<AbsenceReason, AbsenceReasonService> {
    /**
     * Constructs new <em>PositionServiceTest</em> object.
     */
    public AbsenceReasonServiceTest() {
        super(new AbsenceReasonTestData());
    }

    /**
     * Sets the {@code AbsenceReasonService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(AbsenceReasonService service) {
        this.service = service;
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new AbsenceReason(null, "Test validate, reason is null")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason("", "Test validate, reason is empty")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason("    ", "Test validate, reason is blank")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason("A", "Test validate, reason's size < 2")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason(
                        "Test validate, reason's size > 255" +
                                "Test validate, reason's size > 255" +
                                "Test validate, reason's size > 255" +
                                "Test validate, reason's size > 255" +
                                "Test validate, reason's size > 255" +
                                "Test validate, reason's size > 255" +
                                "Test validate, reason's size > 255" +
                                "Test validate, reason's size > 255",
                        "Test validate, reason's size > 255")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason("Test validate, description is null", null)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason("Test validate, description is empty", "")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason("Test validate, description is blank", "    ")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new AbsenceReason("Test validate, description's size < 2", "A")),
                ConstraintViolationException.class);
    }
}
