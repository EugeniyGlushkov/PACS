package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.EmpSchedule;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.EmpScheduleTestData;
import testdata.EmployeeTestData;

import javax.validation.ConstraintViolationException;
import java.time.LocalTime;

import static util.TestUtil.assertMatch;

/**
 * Employee schedule's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class EmpScheduleServiceTest extends AbstractServiceTest<EmpSchedule, EmpScheduleService> {
    /**
     * Constructs new <em>EmpScheduleServiceTest</em> object.
     */
    public EmpScheduleServiceTest() {
        super(new EmpScheduleTestData());
    }

    /**
     * Sets the {@code EmpScheduleService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(EmpScheduleService service) {
        this.service = service;
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code employee} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithEmpId() {
        EmpSchedule expectedEmpSchedule = testData.getNew();
        int expectedEmpId = expectedEmpSchedule.getEmployee().getId();
        expectedEmpSchedule.setEmployee(null);
        EmpSchedule actualEmpSchedule = service.create(expectedEmpSchedule, expectedEmpId);
        expectedEmpSchedule = testData.getNew();
        expectedEmpSchedule.setId(actualEmpSchedule.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedEmpSchedule));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code employee} in the DB;
     * checks matching the actual value to the expected value from {@code testData}.
     */
    @Test
    public void updateWithEmpId() {
        EmpSchedule expectedEmpSchedule = testData.getUpdated();
        int expectedEmpId = expectedEmpSchedule.getEmployee().getId();
        expectedEmpSchedule.setEmployee(null);
        service.update(expectedEmpSchedule, expectedEmpId);
        EmpSchedule actualEmpShedule = service.get(expectedEmpSchedule.getId());
        expectedEmpSchedule = testData.getUpdated();
        assertMatch(testData.IGNORING_FIELDS, actualEmpShedule, expectedEmpSchedule);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted employee) in the DB.
     */
    @Test
    public void updateWithEmpIdNotFound() {
        EmpSchedule updatedEmpSchedule = testData.getUpdated();
        updatedEmpSchedule.setId(testData.NOT_FOUND_ID);
        int empId = updatedEmpSchedule.getEmployee().getId();
        updatedEmpSchedule.setEmployee(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(updatedEmpSchedule, empId);
    }

    /**
     * Checks matching the actual gotten value by employee's id from DB to the expected gotten value from {@code testData}.
     */
    @Test
    public void getByEmpId() {
        EmpSchedule expectedEmpSchedule = testData.getGotten();
        EmpSchedule actualEmpSchedule = service.getByEmpId(expectedEmpSchedule.getEmployee().getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmpSchedule, expectedEmpSchedule);
    }

    /**
     * Checks matching the actual gotten value by employee's id from DB to the expected gotten value from {@code testData}
     * when there are not employee's schedule and gotten value is created by department's schedule of the employee.
     */
    @Test
    public void getByEmpIdNotFoundEmpSchedule() {
        EmpSchedule expectedEmpSchedule = new EmpSchedule(EmployeeTestData.EMPLOYEE_6,
                LocalTime.of(8, 0), LocalTime.of(17, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0));
        EmpSchedule actualEmpSchedule = service.getByEmpId(EmployeeTestData.EMPLOYEE_6.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmpSchedule, expectedEmpSchedule);
    }

    /**
     * Checks matching the actual gotten value by employee's id from DB to the expected gotten value from {@code testData}
     * when there are not employee's schedule, department's value and gotten value is created with null-schedule values.
     */
    @Test
    public void getByEmpIdNotFoundDeptSchedule() {
        EmpSchedule expectedEmpSchedule = new EmpSchedule(EmployeeTestData.EMPLOYEE_5,
                null, null, null, null);
        EmpSchedule actualEmpSchedule = service.getByEmpId(EmployeeTestData.EMPLOYEE_5.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmpSchedule, expectedEmpSchedule);
    }

    /**
     * Checks the {@code NotFoundException} when there are no employee with expected id in the DB.
     */
    @Test
    public void getByEmpIdNotFoundEmoloyeeId() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.getByEmpId(testData.NOT_FOUND_ID);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        EmpSchedule newEmpSchedule = testData.getNew();
        newEmpSchedule.setEmployee(null);
        validateRootCause(() -> service.create(newEmpSchedule), ConstraintViolationException.class);
    }
}
