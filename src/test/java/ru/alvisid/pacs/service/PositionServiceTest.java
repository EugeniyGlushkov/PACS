package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.Position;
import testdata.PositionTestData;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static testdata.PositionTestData.*;

/**
 * Position's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class PositionServiceTest extends AbstractServiceTest <Position, PositionService> {
    /**
     * Constructs new <em>PositionServiceTest</em> object.
     */
    public PositionServiceTest() {
        super(new PositionTestData());
    }

    /**
     * The employee's service realization.
     */
    private EmployeeService employeeService;

    /**
     * Sets the {@code PositionService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(PositionService service) {
        this.service = service;
    }

    /**
     * Sets the {@code EmployeeService}.
     *
     * @param employeeService the specified EmployeeService.
     */
    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Updates employee with the deleted position.
     */
    @Test
    @Override
    public void delete() {
        List <Employee> updatedEmployees = employeeService.getAllByPositionId(testData.getDeletedId());

        for (Employee emp : updatedEmployees) {
            emp.setPosition(POSITION_5);
            employeeService.update(emp);
        }

        super.delete();
    }

    /**
     * Checks the {@code IllegalStateException} and it's message when there are some employees with deleted position.
     */
    @Test
    public void deleteOccupied() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Can not delete position with id=" + testData.getDeletedId()
                + ", because there are some employees with the position.");
        super.delete();
    }

    /**
     * Checks the {@code DataAccessException} when position with duplicate position-field create.
     */
    @Test
    public void createDuplicatePosition() {
        Position positionDuplicateNew = testData.getNew();
        positionDuplicateNew.setPosition(testData.getGotten().getPosition());
        thrown.expect(DataAccessException.class);
        service.create(positionDuplicateNew);
    }

    /**
     * Checks the {@code DataAccessException} when position with duplicate position-field update.
     */
    @Test
    public void updateDuplicatePosition() {
        Position positionDuplicateUpd = testData.getUpdated();
        positionDuplicateUpd.setPosition(POSITION_2.getPosition());
        thrown.expect(DataAccessException.class);
        service.update(positionDuplicateUpd);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Position(null, "Test validate, position is null")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("", "Test validate, position is empty")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("    ", "Test validate, position is blank")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("A", "Test validate, position's size < 2")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position(
                        "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255" +
                                "Test validate, position's size > 255",
                        "Test validate, position's size > 255")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description is null", null)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description is empty", "")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description is blank", "    ")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Position("Test validate, description's size < 2", "A")),
                ConstraintViolationException.class);
    }
}
