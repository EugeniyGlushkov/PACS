package ru.alvisid.pacs.service;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @Test
    public void getWithWeekEndsAndSched() {
        Department expectedDepartment = testData.getGotten();
        System.out.println(WeekDay.MONDAY.ordinal());
        Enum[] days = WeekDay.values();
        System.out.println(days.length + "\n");
        for (Enum e : days) {
            System.out.println(e == null ? 0 : e.ordinal() );
        }
        Department actualDepartment = service.getWithWeekEndsAndSched(expectedDepartment.getId());
        assertMatch(testData.IGNORING_FIELDS, actualDepartment, expectedDepartment);
    }
}
