package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.DepartmentTestData;
import testdata.DeptScheduleTestData;

import javax.validation.ConstraintViolationException;

import static util.TestUtil.assertMatch;

/**
 * Department schedule's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class DeptScheduleServiceTest extends AbstractServiceTest<DeptSchedule, DeptScheduleService> {
    /**
     * Current {@code DepartmentTestData} object.
     */
    DepartmentTestData departmentTestData = new DepartmentTestData();

    /**
     * Constructs new <em>DeptScheduleServiceTest</em> object.
     */
    public DeptScheduleServiceTest() {
        super(new DeptScheduleTestData());
    }

    /**
     * Sets the {@code DepartmentService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(DeptScheduleService service) {
        super.service = service;
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code department} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithDeptId() {
        DeptSchedule expectedDeptSchedule = testData.getNew();
        int expectedDeptId = expectedDeptSchedule.getDepartment().getId();
        expectedDeptSchedule.setDepartment(null);
        DeptSchedule actualDeptSchedule = service.create(expectedDeptSchedule, expectedDeptId);
        expectedDeptSchedule = testData.getNew();
        expectedDeptSchedule.setId(actualDeptSchedule.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedDeptSchedule));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code department} in the DB;
     * checks matching the actual list of all objects to the expected list of all objects from {@code testData}.
     */
    @Test
    public void updateWithDeptId() {
        DeptSchedule expectedDeptSchedule = testData.getUpdated();
        int expectedDeptId = expectedDeptSchedule.getDepartment().getId();
        expectedDeptSchedule.setDepartment(null);
        service.update(expectedDeptSchedule, expectedDeptId);
        DeptSchedule actualDeptSchedule = service.get(expectedDeptSchedule.getId());
        expectedDeptSchedule = testData.getUpdated();
        assertMatch(testData.IGNORING_FIELDS, actualDeptSchedule, expectedDeptSchedule);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted department) in the DB.
     */
    @Test
    public void updateWithDeptIdNotFound() {
        DeptSchedule updatedDeptSchedule = testData.getUpdated();
        updatedDeptSchedule.setId(testData.NOT_FOUND_ID);
        thrown.expect(NotFoundException.class);
        System.out.println(updatedDeptSchedule);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(updatedDeptSchedule, updatedDeptSchedule.getDepartment().getId());
    }


    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        DeptSchedule newDeptSchedule = testData.getNew();
        newDeptSchedule.setDepartment(null);
        validateRootCause(() -> service.create(newDeptSchedule), ConstraintViolationException.class);

    }
}
