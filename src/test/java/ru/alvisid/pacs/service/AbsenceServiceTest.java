package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Absence;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.AbsenceTestData;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static testdata.AbsenceReasonTestData.ABSENCE_REASON_3;
import static util.TestUtil.assertMatch;
import static testdata.EmployeeTestData.*;
import static testdata.AbsenceTestData.*;

/**
 * Absence's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class AbsenceServiceTest extends AbstractServiceTest<Absence, AbsenceService> {
    /**
     * Constructs new <em>AbsenceServiceTest</em> object.
     */
    public AbsenceServiceTest() {
        super(new AbsenceTestData());
    }


    /**
     * Sets the {@code AbsenceService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(AbsenceService service) {
        this.service = service;
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code employee} and {@code absence reason} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithEmpIdAndAbsReasonId() {
        Absence expectedAbsence = testData.getNew();
        int expectedEmpId = expectedAbsence.getEmployee().getId();
        int expectedAbsReasonId = expectedAbsence.getAbsenceReason().getId();
        expectedAbsence.setEmployee(null);
        expectedAbsence.setAbsenceReason(null);
        Absence actualAbsence = service.create(expectedAbsence, expectedEmpId, expectedAbsReasonId);
        expectedAbsence = testData.getNew();
        expectedAbsence.setId(actualAbsence.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedAbsence));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code employee} {@code absence reason} in the DB;
     * checks matching the actual value to the expected value from {@code testData}.
     */
    @Test
    public void updateWithEmpIdAndAbsReasonId() {
        Absence expectedAbsence = testData.getUpdated();
        int expectedEmpId = expectedAbsence.getEmployee().getId();
        int expectedAbsReasonId = expectedAbsence.getAbsenceReason().getId();
        expectedAbsence.setEmployee(null);
        expectedAbsence.setAbsenceReason(null);
        service.update(expectedAbsence, expectedEmpId, expectedAbsReasonId);
        Absence actualAbsence = service.get(expectedAbsence.getId());
        expectedAbsence = testData.getUpdated();
        assertMatch(testData.IGNORING_FIELDS, actualAbsence, expectedAbsence);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted employee and absence reason) in the DB.
     */
    @Test
    public void updateWithEmpIdAndAbsReasonIdNotFound() {
        Absence absence = testData.getUpdated();
        int empId = absence.getEmployee().getId();
        int absReasonId = absence.getAbsenceReason().getId();
        absence.setId(testData.NOT_FOUND_ID);
        absence.setEmployee(null);
        absence.setAbsenceReason(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(absence, empId, absReasonId);
    }

    /**
     * Checks the actual gotten list of the all objects by employee's id from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByEmpId() {
        List<Absence> actualAbsences = service.getAllByEmplId(EMPLOYEE_2.getId());
        List<Absence> expectedAbsence = List.of(ABSENCE_3);
        assertMatch(testData.IGNORING_FIELDS, actualAbsences, expectedAbsence);
    }

    /**
     * Checks the actual gotten list of the all objects by id of the employee's department from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByEmplDeptId() {
        List<Absence> actualAbsences = service.getAllByEmplDeptId(EMPLOYEE_2.getDepartment().getId());
        assertMatch(testData.IGNORING_FIELDS, actualAbsences, ABSENCE_4, ABSENCE_2, ABSENCE_3);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Absence(EMPLOYEE_5, null,
                        LocalDate.of(2018, 12, 25), LocalDate.of(2019, 1, 22),
                        "Отпуск.")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Absence(EMPLOYEE_5, ABSENCE_REASON_3,
                        null, LocalDate.of(2019, 1, 22),
                        "Отпуск.")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Absence(EMPLOYEE_5, ABSENCE_REASON_3,
                        LocalDate.of(2018, 12, 25), null,
                        "Отпуск.")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Absence(null, ABSENCE_REASON_3,
                        LocalDate.of(2018, 12, 25), LocalDate.of(2019, 1, 22),
                        "Отпуск.")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Absence(EMPLOYEE_5, ABSENCE_REASON_3,
                        LocalDate.of(2018, 12, 25), LocalDate.of(2019, 1, 22),
                        null)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Absence(EMPLOYEE_5, ABSENCE_REASON_3,
                        LocalDate.of(2018, 12, 25), LocalDate.of(2019, 1, 22),
                        "")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Absence(EMPLOYEE_5, ABSENCE_REASON_3,
                        LocalDate.of(2018, 12, 25), LocalDate.of(2019, 1, 22),
                        "      ")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Absence(EMPLOYEE_5, ABSENCE_REASON_3,
                        LocalDate.of(2018, 12, 25), LocalDate.of(2019, 1, 22),
                        "К")),
                ConstraintViolationException.class);
    }
}
