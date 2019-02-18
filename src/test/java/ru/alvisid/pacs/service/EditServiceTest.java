package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Edit;
import ru.alvisid.pacs.model.EditType;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.EditTestData;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import static util.TestUtil.assertMatch;
import static testdata.EmployeeTestData.*;
import static testdata.EditTestData.*;

/**
 * Edit's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class EditServiceTest extends AbstractServiceTest<Edit, EditService> {
    /**
     * Constructs new <em>EditServiceTest</em> object.
     */
    public EditServiceTest() {
        super(new EditTestData());
    }

    /**
     * Sets the {@code EditService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(EditService service) {
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
        Edit expectedEdit = testData.getNew();
        int expectedEmpId = expectedEdit.getEmployee().getId();
        expectedEdit.setEmployee(null);
        Edit actualCreatedEdit = service.create(expectedEdit, expectedEmpId);
        expectedEdit = testData.getNew();
        expectedEdit.setId(actualCreatedEdit.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedEdit));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code employee} in the DB;
     * checks matching the actual value to the expected value from {@code testData}.
     */
    @Test
    public void updateWithEmpId() {
        Edit expectedEdit = testData.getUpdated();
        int expectedEmpId = expectedEdit.getEmployee().getId();
        expectedEdit.setEmployee(null);
        service.update(expectedEdit, expectedEmpId);
        Edit actualEdit = service.get(expectedEdit.getId());
        expectedEdit = testData.getUpdated();
        assertMatch(testData.IGNORING_FIELDS, actualEdit, expectedEdit);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted employee) in the DB.
     */
    @Test
    public void updateWithEmpIdNotFound() {
        Edit expectedEdit = testData.getUpdated();
        expectedEdit.setId(testData.NOT_FOUND_ID);
        int expectedEmpId = expectedEdit.getEmployee().getId();
        expectedEdit.setEmployee(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(expectedEdit, expectedEmpId);
    }

    /**
     * Checks the actual gotten list of the all objects gotten by employee's id from DB to
     * the expected gotten values of the objects from {@code testData}.
     */
    @Test
    public void getAllByEmpId() {
        List<Edit> actualEditsByEmpId = service.getAllByEmpId(EMPLOYEE_6.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEditsByEmpId, EDIT_3, EDIT_4);
    }

    /**
     * Checks the actual gotten list of the all objects gotten between start date and end date from DB to
     * the expected gotten values of the objects from {@code testData}.
     */
    @Test
    public void getAllBetween() {
        List<Edit> actualEditsBetween = service.getAllBetween(
                LocalDateTime.of(2017, 2, 23, 0, 0, 0),
                LocalDateTime.of(2017, 8, 27, 0, 0, 0));
        assertMatch(testData.IGNORING_FIELDS, actualEditsBetween, EDIT_2, EDIT_3);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Edit(null, EMPLOYEE_6,
                        LocalDateTime.of(2017, 10, 22, 14, 36, 47),
                        "[CREATE], schedule [id=10004] of the department [id=3] [09:00:00, 18:00:00, 12:30:00, 13:30:00]")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Edit(EditType.CREATE, null,
                        LocalDateTime.of(2017, 10, 22, 14, 36, 47),
                        "[CREATE], schedule [id=10004] of the department [id=3] [09:00:00, 18:00:00, 12:30:00, 13:30:00]")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Edit(EditType.CREATE, EMPLOYEE_6,
                        null,
                        "[CREATE], schedule [id=10004] of the department [id=3] [09:00:00, 18:00:00, 12:30:00, 13:30:00]")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Edit(EditType.CREATE, EMPLOYEE_6,
                        LocalDateTime.of(2017, 10, 22, 14, 36, 47),
                        null)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Edit(EditType.CREATE, EMPLOYEE_6,
                        LocalDateTime.of(2017, 10, 22, 14, 36, 47),
                        "")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Edit(EditType.CREATE, EMPLOYEE_6,
                        LocalDateTime.of(2017, 10, 22, 14, 36, 47),
                        "      ")),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Edit(EditType.CREATE, EMPLOYEE_6,
                        LocalDateTime.of(2017, 10, 22, 14, 36, 47),
                        "A")),
                ConstraintViolationException.class);
    }
}
