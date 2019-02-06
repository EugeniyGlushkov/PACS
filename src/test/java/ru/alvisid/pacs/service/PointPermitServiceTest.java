package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.model.PointPermit;
import testdata.PointPermitTestData;

import static util.TestUtil.assertMatch;

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
        Employee expectedEmployee = expectedPointPermit.getEmployee();
        PointAction expectedPointAct = expectedPointPermit.getPointAction();
        expectedPointPermit.setEmployee(null);
        expectedPointPermit.setPointAction(null);
        PointPermit actualPointPermit = service.create(expectedPointPermit, expectedEmployee.getId(), expectedPointAct.getId());
        expectedPointPermit = testData.getNew();
        expectedPointPermit.setId(actualPointPermit.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedPointPermit));
    }
}
