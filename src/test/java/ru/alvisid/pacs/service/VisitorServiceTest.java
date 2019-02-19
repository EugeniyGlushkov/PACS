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
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", null, "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "    ", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", null,
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "    ",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor(null, "Новое имя", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("", "Новое имя", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("    ", "Новое имя", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                null, "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "    ", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "110120194", null,
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "110120194", "",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "110120194", "    ",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Q", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Q",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Q", "Новое имя", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "Q", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "110120194", "Q",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия",
                "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols",
                "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя",
                "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor(
                "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols",
                "Новое имя", "Новая фамилия",
                "110120194", "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                "More than 20 symbols" +
                        "More than 20 symbols.",
                "Посещение директора.",
                null,
                null)), ConstraintViolationException.class);
    }
}
