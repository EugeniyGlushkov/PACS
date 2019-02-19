package ru.alvisid.pacs.service;

import javax.validation.ConstraintViolationException;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.alvisid.pacs.model.*;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.DepartmentTestData;

import java.util.List;

import static testdata.DepartmentTestData.*;
import static testdata.EmployeeTestData.*;
import static util.TestUtil.assertMatch;

/**
 * Department's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class DepartmentServiceTest extends AbstractServiceTest <Department, DepartmentService> {
    /**
     * Constructs new <em>DepartmentServiceTest</em> object.
     */
    public DepartmentServiceTest() {
        super(new DepartmentTestData());
    }

    /**
     * The point action's service realization.
     */
    private PointPermitService pointPermitService;

    /**
     * The point action's service realization.
     */
    private ActionService actionService;

    /**
     * The absence's service realization.
     */
    private AbsenceService absenceService;

    /**
     * The edit's service realization.
     */
    private EditService editService;

    /**
     * The employee's service realization.
     */
    private EmployeeService employeeService;

    /**
     * Sets the {@code DepartmentService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(DepartmentService service) {
        this.service = service;
    }

    /**
     * Sets the {@code PointPermitService}, {@code ActionService}, {@code AbsenceService},
     * {@code EditService} and {@code EmployeeService}.
     *
     * @param pointPermitService the specified PointPermitService.
     * @param actionService      the specified ActionService.
     * @param absenceService     the specified AbsenceService.
     * @param editService        the specified EditService.
     * @param employeeService    the specified EmployeeService.
     */
    @Autowired
    public void setSupportingServices(PointPermitService pointPermitService,
                                      ActionService actionService,
                                      AbsenceService absenceService,
                                      EditService editService,
                                      EmployeeService employeeService) {
        this.pointPermitService = pointPermitService;
        this.actionService = actionService;
        this.absenceService = absenceService;
        this.editService = editService;
        this.employeeService = employeeService;
    }
    /**
     * Cleans point permits, actions, absences, edits and employee tables in the data base
     * and execute test method in the superclass.
     */
    @Override
    public void delete() {
        List <PointPermit> pointPermitsForDel = pointPermitService.getAll();
        List <Action> actionsForDel = actionService.getAll();
        List <Absence> absencesForDel = absenceService.getAll();
        List <Edit> editsForDel = editService.getAll();
        List <Employee> employeesForDel = employeeService.getAll();

        for (PointPermit pp : pointPermitsForDel) {
            pointPermitService.delete(pp.getId());
        }

        for (Action act : actionsForDel) {
            actionService.delete(act.getId());
        }

        for (Absence abs : absencesForDel) {
            absenceService.delete(abs.getId());
        }

        for (Edit edit : editsForDel) {
            editService.delete(edit.getId());
        }

        for (Employee empl : employeesForDel) {
            employeeService.delete(empl.getId());
        }

        super.delete();
    }

    /**
     * Checks the {@code IllegalStateException} and it's message when deleted department is not empty.
     */
    @Test
    public void deleteNotEmpty() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Can not delete department with id=" + testData.getDeletedId() + ", it must be empty.");
        super.delete();
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

    /**
     * Checks the {@code NotFoundException} when there are no object with the specified id in the DB.
     */
    @Test
    public void getWithWeekEndsAndSchedNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.getWithWeekEndsAndSched(testData.NOT_FOUND_ID);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Department(null, "Test validate, name is null")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department("", "Test validate, name is empty")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department("    ", "Test validate, name is blank")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department("A", "Test validate, name's size < 2")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department(
                        "Test validate, name's size > 255" +
                                "Test validate, name's size > 255" +
                                "Test validate, name's size > 255" +
                                "Test validate, name's size > 255" +
                                "Test validate, name's size > 255" +
                                "Test validate, name's size > 255" +
                                "Test validate, name's size > 255" +
                                "Test validate, name's size > 255",
                        "Test validate, name's size > 255")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department("Test validate, description is null", null)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department("Test validate, description is empty", "")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department("Test validate, description is blank", "    ")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Department("Test validate, description's size < 2", "A")),
                ConstraintViolationException.class);
    }
}
