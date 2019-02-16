package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.DayOff;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.DayOffTestData;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static util.TestUtil.assertMatch;
import static testdata.DepartmentTestData.*;
import static testdata.DayOffTestData.*;

/**
 * DayOff's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class DayOffServiceTest extends AbstractServiceTest<DayOff, DayOffService> {
    /**
     * Constructs new <em>DeptScheduleServiceTest</em> object.
     */
    public DayOffServiceTest() {
        super(new DayOffTestData());
    }

    /**
     * Sets the {@code DayOffService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(DayOffService service) {
        this.service = service;
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code department} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithDeptId() {
        DayOff expectedDayOff = testData.getNew();
        int expectedDeptId = expectedDayOff.getDepartment().getId();
        expectedDayOff.setDepartment(null);
        DayOff actualDayOff = service.create(expectedDayOff, expectedDeptId);
        expectedDayOff = testData.getNew();
        expectedDayOff.setId(actualDayOff.getId());
        assertMatch(service.getAll(), testData.getCreatedArray(expectedDayOff));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code department} in the DB;
     * checks matching the actual value to the expected value from {@code testData}.
     */
    @Test
    public void updateWithDeptId() {
        DayOff expectedDayOff = testData.getUpdated();
        int expectedDeptId = expectedDayOff.getDepartment().getId();
        expectedDayOff.setDepartment(null);
        service.update(expectedDayOff, expectedDeptId);
        DayOff actualDayOff = service.get(expectedDayOff.getId());
        expectedDayOff = testData.getUpdated();
        assertMatch(actualDayOff, expectedDayOff);
    }


    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted department) in the DB.
     */
    @Test
    public void updateWithDeptIdNotFound() {
        DayOff updatedDayOff = testData.getUpdated();
        updatedDayOff.setId(testData.NOT_FOUND_ID);
        int deptId = updatedDayOff.getDepartment().getId();
        updatedDayOff.setDepartment(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(updatedDayOff, deptId);
    }

    /**
     * Checks the actual gotten list of the all objects by department's id from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByDeptId() {
        List<DayOff> actualListDayOff = service.getAllByDeptId(DEPARTMENT_2.getId());
        assertMatch(actualListDayOff, DAY_OFF_2, DAY_OFF_6, DAY_OFF_9);
    }

    /**
     * Checks the actual gotten list of the all objects by date from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByDate() {
        List<DayOff> actualListDayOff = service.getAllByDate(LocalDate.of(2018, 5, 1));
        assertMatch(actualListDayOff, DAY_OFF_5, DAY_OFF_6, DAY_OFF_7);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new DayOff(null,
                LocalDate.of(2018, 5, 10))), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DayOff(DEPARTMENT_1, null)), ConstraintViolationException.class);
    }
}
