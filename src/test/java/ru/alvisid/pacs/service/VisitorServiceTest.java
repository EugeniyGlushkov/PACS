package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.alvisid.pacs.model.Visitor;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.VisitorTestData;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static util.TestUtil.assertMatch;
import static testdata.VisitorTestData.*;

/**
 * Visitor's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class VisitorServiceTest extends AbstractServiceTest <Visitor, VisitorService> {
    /**
     * Constructs new <em>VisitorServiceTest</em> object.
     */
    public VisitorServiceTest() {
        super(new VisitorTestData());
    }

    /**
     * Sets the {@code VisitorService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(VisitorService service) {
        this.service = service;
    }

    /**
     * Checks matching the actual gotten by temporary number value from DB to the expected gotten value from {@code testData}.
     */
    @Test
    public void getByTempNum() {
        Visitor expectedVisitor = testData.getGotten();
        Visitor actualVisitor = service.getByTempNum(expectedVisitor.getTempNum());
        assertMatch(testData.IGNORING_FIELDS, actualVisitor, expectedVisitor);
    }

    /**
     * Checks the {@code NotFoundException} when there are no object with specified temporary number to getting in the DB.
     */
    @Test
    public void getByTempNumNotFound() {
        String notFoundTempNum = "notFound";
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with temporary number=" + notFoundTempNum);
        service.getByTempNum(notFoundTempNum);
    }

    /**
     * Checks the actual gotten list of the all objects by enter time interval from DB to
     * the expected gotten list of the all objects by enter time interval from {@code testData}.
     */
    @Test
    public void getAllByEnterTimeBetween() {
        LocalDateTime startTime = LocalDateTime.of(2019, 1, 5, 22, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 1, 20, 5, 11);
        assertMatch(testData.IGNORING_FIELDS,
                service.getAllByEnterTimeBetween(startTime, endTime),
                new VisitorTestData().getAllByEnterTimeBetweenArray());
    }

    /**
     * Checks the {@code IllegalArgumentException} when {@code startTime} is after {@code endTime}.
     */
    @Test
    public void getAllByEnterTimeBetweenIllegalArgument() {
        LocalDateTime startTime = LocalDateTime.of(2019, 1, 20, 5, 11);
        LocalDateTime endTime = LocalDateTime.of(2019, 1, 5, 22, 20);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Param start time=" + startTime +
                " must be before than param end time=" + endTime);
        service.getAllByEnterTimeBetween(startTime, endTime);
    }

    /**
     * Checks the {@code DataAccessException} when visitor with {@code startTime} is null
     * and {@code endTime} is not null create.
     */
    @Test
    public void createStartTimeConstraint() {
        Visitor newVisitor = testData.getNew();
        newVisitor.setExitTime(testData.getGotten().getExitTime());
        thrown.expect(DataAccessException.class);
        service.create(newVisitor);
    }

    /**
     * Checks the {@code DataAccessException} when visitor with {@code startTime} is null
     * and {@code endTime} is not null update.
     */
    @Test
    public void updateStartTimeConstraint() {
        Visitor updateVisitor = testData.getUpdated();
        updateVisitor.setEnterTime(null);
        thrown.expect(DataAccessException.class);
        service.update(updateVisitor);
    }

    /**
     * Checks the {@code DataAccessException} when visitor with duplicate temporary number create.
     */
    @Test
    public void createDuplicateTempNum() {
        Visitor tempNumDuplicateNew = testData.getNew();
        tempNumDuplicateNew.setTempNum(testData.getGotten().getTempNum());
        thrown.expect(DataAccessException.class);
        service.create(tempNumDuplicateNew);
    }

    /**
     * Checks the {@code DataAccessException} when visitor with duplicate temporary number update.
     */
    @Test
    public void updateDuplicateTempNum() {
        Visitor tempNumDuplicateUpd = testData.getUpdated();
        tempNumDuplicateUpd.setTempNum(VISITOR_3.getTempNum());
        thrown.expect(DataAccessException.class);
        service.update(tempNumDuplicateUpd);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        Visitor newVisitor = testData.getNew();
        newVisitor.setFirstName(null);
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setFirstName("");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setFirstName("    ");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setSecondName(null);
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setSecondName("");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setSecondName("    ");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setLastName(null);
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setLastName("");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setLastName("    ");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setTempNum(null);
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setTempNum("");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setTempNum("    ");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setDescription(null);
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setDescription("");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setDescription("    ");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setFirstName("Q");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setSecondName("Q");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setLastName("Q");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setTempNum("Q");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setDescription("Q");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setFirstName("More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setSecondName("More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setLastName("More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols" +
                "More than 100 symbols");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
        newVisitor.setId(null);
        newVisitor.setTempNum("More than 20 symbols" +
                "More than 20 symbols.");
        validateRootCause(() -> service.create(newVisitor), ConstraintViolationException.class);
    }
}
