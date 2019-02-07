package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import testdata.ControlPointTestData;
import testdata.EmployeeTestData;
import testdata.PointPermitTestData;

import java.util.List;

import static util.TestUtil.assertMatch;
import static testdata.PointPermitTestData.*;

public class PointPermitServiceTest extends AbstractServiceTest <PointPermit, PointPermitService> {
    /**
     * Constructs new <em>PointPermitServiceTest</em> object.
     */
    public PointPermitServiceTest() {
        super(new PointPermitTestData());
    }

    /**
     * Sets the {@code PointPermitService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(PointPermitService service) {
        this.service = service;
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object with inserted {@code employee} and {@code point action} to the DB;
     * checks matching the actual list of all objects
     * to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void createWithEmpIdAndPointActId() {
        PointPermit expectedPointPermit = testData.getNew();
        int expectedEmployeeId = expectedPointPermit.getEmployee().getId();
        int expectedPointActId = expectedPointPermit.getPointAction().getId();
        expectedPointPermit.setEmployee(null);
        expectedPointPermit.setPointAction(null);
        PointPermit actualPointPermit = service.create(expectedPointPermit, expectedEmployeeId, expectedPointActId);
        expectedPointPermit = testData.getNew();
        expectedPointPermit.setId(actualPointPermit.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedPointPermit));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code employee} {@code point action} in the DB;
     * checks matching the actual list of all objects to the expected list of all objects from {@code testData}.
     */
    @Test
    public void updateWithEmpIdAndPointActId() {
        PointPermit expectedPointPermit = testData.getUpdated();
        int expectedEmployeeId = expectedPointPermit.getEmployee().getId();
        int expectedPointActId = expectedPointPermit.getPointAction().getId();
        expectedPointPermit.setEmployee(null);
        expectedPointPermit.setPointAction(null);
        service.update(expectedPointPermit, expectedEmployeeId, expectedPointActId);
        PointPermit actualPointPermit = service.get(expectedPointPermit.getId());
        expectedPointPermit = testData.getUpdated();
        assertMatch(testData.IGNORING_FIELDS, actualPointPermit, expectedPointPermit);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id (updated object
     * with inserted employee and point action) in the DB.
     */
    @Test
    public void updateWithEmpIdAndPointActIdNotFound() {
        PointPermit expectedPointPermit = testData.getUpdated();
        expectedPointPermit.setId(testData.NOT_FOUND_ID);
        int expectedEmployeeId = expectedPointPermit.getEmployee().getId();
        int expectedPointActId = expectedPointPermit.getPointAction().getId();
        expectedPointPermit.setEmployee(null);
        expectedPointPermit.setPointAction(null);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(expectedPointPermit, expectedEmployeeId, expectedPointActId);
    }

    /**
     * Checks the actual gotten list of the all objects by employee's id from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByEmpId() {
        List <PointPermit> actualPointPermits = service.getAllByEmpId(EmployeeTestData.EMPLOYEE_3.getId());
        assertMatch(testData.IGNORING_FIELDS,
                actualPointPermits,
                POINT_PERMIT_30,
                POINT_PERMIT_9,
                POINT_PERMIT_10,
                POINT_PERMIT_11,
                POINT_PERMIT_12);
    }

    /**
     * Checks the actual gotten list of the all objects by control point's id from DB to
     * the expected list of the objects from {@code testData}.
     */
    @Test
    public void getAllByControlPointId() {
        List<PointPermit> actualPointPermits = service.getAllByControlPointId(ControlPointTestData.CONTROL_POINT_2.getId());
        assertMatch(testData.IGNORING_FIELDS,
                actualPointPermits,
                POINT_PERMIT_23,
                POINT_PERMIT_24,
                POINT_PERMIT_19,
                POINT_PERMIT_20,
                POINT_PERMIT_11,
                POINT_PERMIT_12,
                POINT_PERMIT_3,
                POINT_PERMIT_4,
                POINT_PERMIT_15,
                POINT_PERMIT_16,
                POINT_PERMIT_7,
                POINT_PERMIT_8);
    }

    /**
     * Checks matching the actual gotten value from DB to the expected gotten value from {@code testData}.
     */
}
