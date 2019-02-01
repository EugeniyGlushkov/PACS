package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.EmpSchedule;
import testdata.EmpScheduleTestData;

import static util.TestUtil.assertMatch;

/**
 * Employee schedule's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class EmpScheduleServiceTest extends AbstractServiceTest <EmpSchedule, EmpScheduleService> {
    /**
     * Constructs new <em>EmpScheduleServiceTest</em> object.
     */
    public EmpScheduleServiceTest() {
        super(new EmpScheduleTestData());
    }

    /**
     * Sets the {@code EmpScheduleService} field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(EmpScheduleService service) {
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
        EmpSchedule expectedEmpSchedule = testData.getNew();
        int expectedEmpId = expectedEmpSchedule.getEmployee().getId();
        expectedEmpSchedule.setEmployee(null);
        EmpSchedule actualEmpSchedule = service.create(expectedEmpSchedule, expectedEmpId);
        expectedEmpSchedule = testData.getNew();
        expectedEmpSchedule.setId(actualEmpSchedule.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(expectedEmpSchedule));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object with inserted {@code employee} in the DB;
     * checks matching the actual list of all objects to the expected list of all objects from {@code testData}.
     */
    @Test
    public void updateWithEmpId() {
        EmpSchedule expectedEmpSchedule = testData.getUpdated();
        int expectedEmpId = expectedEmpSchedule.getEmployee().getId();
        expectedEmpSchedule.setEmployee(null);
        service.update(expectedEmpSchedule, expectedEmpId);
        EmpSchedule actualEmpShedule = service.get(expectedEmpSchedule.getId());
        expectedEmpSchedule = testData.getUpdated();
        expectedEmpSchedule.setId(actualEmpShedule.getId());
        assertMatch(testData.IGNORING_FIELDS, actualEmpShedule, expectedEmpSchedule);
    }
}
