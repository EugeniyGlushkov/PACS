package testdata;

import ru.alvisid.pacs.model.EmpSchedule;

import java.time.LocalTime;

import static testdata.EmployeeTestData.*;

/**
 * Test data for {@code EmpSchedule} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class EmpScheduleTestData extends AbstractTestData <EmpSchedule> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final EmpSchedule
            EMP_SCHEDULE_1 = new EmpSchedule(10003, EMPLOYEE_1,
            null, null,
            null, null),
            EMP_SCHEDULE_2 = new EmpSchedule(10004, EMPLOYEE_3,
                    null, null,
                    LocalTime.of(12, 30), LocalTime.of(14, 0)),
            EMP_SCHEDULE_3 = new EmpSchedule(10005, EMPLOYEE_4,
                    LocalTime.of(9, 0), LocalTime.of(18, 0),
                    LocalTime.of(12, 0), LocalTime.of(13, 0));

    /**
     * New {@code EmpSchedule} with id-null.
     */
    public static final EmpSchedule NEW_EMP_SCHEDULE = new EmpSchedule(EMPLOYEE_5,
            null, LocalTime.of(17, 30),
            LocalTime.of(13, 0), LocalTime.of(14, 35));

    /**
     * EMP_SCHEDULE_2 with an updated data.
     *
     * @see EmpScheduleTestData#EMP_SCHEDULE_2
     */
    public static final EmpSchedule UPDATED_EMP_SCHEDULE_2 = new EmpSchedule(10004, EMPLOYEE_3,
            LocalTime.of(7, 30), LocalTime.of(16, 0),
            LocalTime.of(12, 30), LocalTime.of(14, 0));

    /**
     * Returns a new {@code EmpSchedule} from the test data.
     *
     * @return the new {@code EmpSchedule} from the test data.
     */
    @Override
    public EmpSchedule getNew() {
        return new EmpSchedule(NEW_EMP_SCHEDULE);
    }

    /**
     * Returns an updated {@code EmpSchedule} from the test data.
     *
     * @return the updated {@code EmpSchedule} from the test data.
     */
    @Override
    public EmpSchedule getUpdated() {
        return new EmpSchedule(UPDATED_EMP_SCHEDULE_2);
    }

    /**
     * Returns an {@code EmpSchedule} that is equals entity which will be get from DB.
     *
     * @return the {@code EmpSchedule} that is equals entity which will be get from DB.
     */
    @Override
    public EmpSchedule getGotten() {
        return new EmpSchedule(EMP_SCHEDULE_2);
    }

    /**
     * Returns an {@code EmpSchedule}'s id for delete.
     *
     * @return the {@code EmpSchedule}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return EMP_SCHEDULE_3.getId();
    }

    /**
     * Returns the all {@code EmpSchedule} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code EmpSchedule}.
     * @return the all {@code EmpSchedule} from test data with the specified created {@code EmpSchedule}.
     */
    @Override
    public EmpSchedule[] getCreatedArray(EmpSchedule expectedCreatedObj) {
        return new EmpSchedule[]{
                expectedCreatedObj,
                EMP_SCHEDULE_2,
                EMP_SCHEDULE_1,
                EMP_SCHEDULE_3
        };
    }

    /**
     * Returns the all {@code EmpSchedule} from the test data without the deleted {@code EmpSchedule}.
     *
     * @return the all {@code EmpSchedule} from the test data without the deleted {@code EmpSchedule}.
     */
    @Override
    public EmpSchedule[] getDeletedArray() {
        return new EmpSchedule[]{
                EMP_SCHEDULE_2,
                EMP_SCHEDULE_1
        };
    }

    /**
     * Returns the all {@code EmpSchedule} from the test data.
     *
     * @return the all {@code EmpSchedule} from the test data.
     */
    @Override
    public EmpSchedule[] getAllArray() {
        return new EmpSchedule[]{
                EMP_SCHEDULE_2,
                EMP_SCHEDULE_1,
                EMP_SCHEDULE_3
        };
    }
}
