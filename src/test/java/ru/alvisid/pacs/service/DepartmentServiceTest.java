package ru.alvisid.pacs.service;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.model.WeekDay;
import util.DepartmentTestData;

import static util.DepartmentTestData.*;
import static util.TestUtil.assertMatch;

/**
 * Department's specific tests.
 */
public class DepartmentServiceTest extends AbstractServiceTest <Department, DepartmentService> {
    public DepartmentServiceTest() {
        super(new DepartmentTestData());
    }

    /**
     * Sets the {@code DepartmentService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(DepartmentService service) {
        super.service = service;
    }

    /**
     * Checks the {@code DataAccessException} when {@code Department} with duplicate name creating.
     */
    @Test
    public void createDuplicateName() {
        thrown.expect(DataAccessException.class);
        Department newDepartment = testData.getNew();
        newDepartment.setName(DEPARTMENT_2.getName());
        service.create(newDepartment);
    }

    /**
     * Checks the {@code DataAccessException} when {@code Department} with duplicate name updating.
     */
    @Test
    public void updateDuplicateName() {
        thrown.expect(DataAccessException.class);
        Department updated = testData.getUpdated();
        updated.setName(DEPARTMENT_2.getName());
        service.update(updated);
    }

    /**
     * Checks matching the actual gotten value from DB to the expected gotten value from {@code testData}
     * without ignoring fields.
     */
    @Test
    public void getWithWeekEndsAndSched() {
        Department expectedDepartment = testData.getGotten();
        Department actualDepartment = service.getWithWeekEndsAndSched(expectedDepartment.getId());
        assertMatch(testData.IGNORING_FIELDS, actualDepartment, expectedDepartment);
        assertMatch(actualDepartment.getWeekEnds(), expectedDepartment.getWeekEnds());
        assertMatch(actualDepartment.getDeptSchedule(), expectedDepartment.getDeptSchedule());
    }
}
