package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.*;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.DepartmentTestData;
import testdata.EmployeeTestData;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static util.TestUtil.assertMatch;
import static testdata.DepartmentTestData.*;
import static testdata.EmployeeTestData.*;
import static testdata.PositionTestData.*;

/**
 * Employee's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class EmployeeServiceTest extends AbstractServiceTest <Employee, EmployeeService> {
    /**
     * Constructs new <em>EmployeeServiceTest</em> object.
     */
    public EmployeeServiceTest() {
        super(new EmployeeTestData());
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
     * Sets the {@code EmployeeService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(EmployeeService service) {
        this.service = service;
    }

    /**
     * Sets the {@code PointPermitService}, {@code ActionService}, {@code AbsenceService} and {@code EditService}.
     *
     * @param pointPermitService the specified PointPermitService.
     * @param actionService      the specified ActionService.
     * @param absenceService     the specified AbsenceService.
     * @param editService        the specified EditService.
     */
    @Autowired
    public void setSupportingServices(PointPermitService pointPermitService,
                                      ActionService actionService,
                                      AbsenceService absenceService,
                                      EditService editService) {
        this.pointPermitService = pointPermitService;
        this.actionService = actionService;
        this.absenceService = absenceService;
        this.editService = editService;
    }

    /**
     * Cleans point permits, actions, absences and edits tables in the data base
     * and execute test method in the superclass.
     */
    @Override
    public void delete() {
        List <PointPermit> pointPermitsForDel = pointPermitService.getAll();
        List <Action> actionsForDel = actionService.getAll();
        List <Absence> absencesForDel = absenceService.getAll();
        List <Edit> editsForDel = editService.getAll();

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

        super.delete();
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code department} and {@code position} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithDeptIdAndPosId() {
        Employee expectedEmployee = testData.getNew();
        int expectedDeptId = expectedEmployee.getDepartment().getId();
        int expectedPosId = expectedEmployee.getPosition().getId();
        expectedEmployee.setDepartment(null);
        expectedEmployee.setPosition(null);
        Employee actualEmployee = service.create(expectedEmployee, expectedDeptId, expectedPosId);
        expectedEmployee = testData.getNew();
        expectedEmployee.setId(actualEmployee.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedEmployee));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code department} and {@code position} in the DB;
     * checks matching the actual value to the expected value from {@code testData}.
     */
    @Test
    public void updateWithDeptIdAndPosId() {
        Employee expectedEmployee = testData.getUpdated();
        int expectedDeptId = expectedEmployee.getDepartment().getId();
        int expectedPosId = expectedEmployee.getPosition().getId();
        expectedEmployee.setDepartment(null);
        expectedEmployee.setPosition(null);
        service.update(expectedEmployee, expectedDeptId, expectedPosId);
        Employee actualEmployee = service.get(expectedEmployee.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmployee, expectedEmployee);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted {@code department} and {@code position}) in the DB.
     */
    @Test
    public void updateWithDeptIdAndPosIdNotFound() {
        Employee expectedEmployee = testData.getUpdated();
        expectedEmployee.setId(testData.NOT_FOUND_ID);
        int expectedDeptId = expectedEmployee.getDepartment().getId();
        int expectedPosId = expectedEmployee.getPosition().getId();
        expectedEmployee.setDepartment(null);
        expectedEmployee.setPosition(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(expectedEmployee, expectedDeptId, expectedPosId);
    }

    /**
     * Checks the actual gotten list of the all objects by department's id from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByDeptId() {
        List <Employee> actualEmployeesByDeptId = service.getAllByDeptId(DEPARTMENT_2.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmployeesByDeptId,
                EMPLOYEE_6,
                EMPLOYEE_4,
                EMPLOYEE_2);
    }

    /**
     * Checks the actual gotten list of the all objects by position's id from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByPositionId() {
        List <Employee> actualEmployeesByPositionId = service.getAllByPositionId(POSITION_4.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmployeesByPositionId,
                Arrays.asList(EMPLOYEE_4));
    }

    /**
     * Checks the actual gotten object by email from DB to
     * the expected object from {@code testData}.
     */
    @Test
    public void getByEmail() {
        Employee expectedEmployee = testData.getGotten();
        Employee actualEmployee = service.getByEmail(expectedEmployee.getEmail());
        assertMatch(testData.IGNORING_FIELDS, actualEmployee, expectedEmployee);
    }

    /**
     * Checks the {@code NotFoundException} when there are no object with specified email in the DB.
     */
    @Test
    public void getByEmailNotFound() {
        String notFoundEmail = "notfound@mail.ru";
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with email=" + notFoundEmail);
        service.getByEmail(notFoundEmail);
    }

    /**
     * Checks matching the actual gotten value from DB to the expected gotten value from {@code testData}
     * with department and position values.
     */
    @Test
    public void getWithDeptAndPosition() {
        Employee expectedEmployee = testData.getGotten();
        Employee actualEmployee = service.getWithDeptAndPosition(expectedEmployee.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmployee, expectedEmployee);
        assertMatch(new DepartmentTestData().IGNORING_FIELDS, actualEmployee.getDepartment(), expectedEmployee.getDepartment());
        assertMatch(actualEmployee.getPosition(), expectedEmployee.getPosition());
    }


    /**
     * Checks the {@code NotFoundException} when there are no object (with department and position values)
     * with specified id in the DB.
     */
    @Test
    public void getWithDeptAndPositionNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.getWithDeptAndPosition(testData.NOT_FOUND_ID);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Employee("Горошина", null, "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "    ", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", null,
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "    ",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee(null, "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("    ", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Q", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "Q",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Q", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина",
                "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols",
                "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена",
                "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee(
                "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols" +
                        "More than 100 symbols",
                "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "Эдуардовна",
                DEPARTMENT_3, null, 124324, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, null, "buh_1@list.ru")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "invalid_email")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Employee("Горошина", "Елена", "Эдуардовна",
                DEPARTMENT_3, POSITION_5, 124324, "    ")), ConstraintViolationException.class);
    }
}
