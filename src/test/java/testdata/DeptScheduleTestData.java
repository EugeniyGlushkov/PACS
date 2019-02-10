package testdata;

import ru.alvisid.pacs.model.DeptSchedule;

import java.time.LocalTime;

import static testdata.DepartmentTestData.*;

/**
 * Test data for {@code DeptSchedule} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DeptScheduleTestData extends AbstractTestData<DeptSchedule> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final DeptSchedule
            DEPT_SCHEDULE_1 = new DeptSchedule(10001, DEPARTMENT_1,
            LocalTime.of(9, 0), LocalTime.of(18, 0),
            LocalTime.of(12, 0), LocalTime.of(13, 0)),
            DEPT_SCHEDULE_2 = new DeptSchedule(10002, DEPARTMENT_2,
                    LocalTime.of(8, 0), LocalTime.of(17, 0),
                    LocalTime.of(11, 0), LocalTime.of(12, 0));

    /**
     * New {@code DeptSchedule} with id-null.
     */
    public static final DeptSchedule NEW_DEPT_SCHEDULE_3 = new DeptSchedule(DEPARTMENT_3,
            LocalTime.of(9, 0), LocalTime.of(18, 0),
            LocalTime.of(12, 30), LocalTime.of(13, 30));

    /**
     * DEPT_SCHEDULE_1 with an updated data.
     *
     * @see DeptScheduleTestData#DEPT_SCHEDULE_1
     */
    public static final DeptSchedule UPDATED_DEPT_SCHEDULE_1 = new DeptSchedule(10001, DEPARTMENT_1,
            LocalTime.of(8, 20), LocalTime.of(17, 20),
            LocalTime.of(13, 0), LocalTime.of(14, 0));

    /**
     * Returns a new {@code DeptSchedule} from the test data.
     *
     * @return the new {@code DeptSchedule} from the test data.
     */
    @Override
    public DeptSchedule getNew() {
        return new DeptSchedule(NEW_DEPT_SCHEDULE_3);
    }

    /**
     * Returns an updated {@code DeptSchedule} from the test data.
     *
     * @return the updated {@code DeptSchedule} from the test data.
     */
    @Override
    public DeptSchedule getUpdated() {
        return new DeptSchedule(UPDATED_DEPT_SCHEDULE_1);
    }

    /**
     * Returns a {@code DeptSchedule} that is equals entity which will be get from DB.
     *
     * @return the {@code DeptSchedule} that is equals entity which will be get from DB.
     */
    @Override
    public DeptSchedule getGotten() {
        return new DeptSchedule(DEPT_SCHEDULE_2);
    }

    /**
     * Returns a {@code DeptSchedule}'s id for delete.
     *
     * @return the {@code DeptSchedule}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return DEPT_SCHEDULE_1.getId();
    }

    /**
     * Returns the all {@code DeptSchedule} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code DeptSchedule}.
     * @return the all {@code DeptSchedules} from test data with the specified created {@code DeptSchedule}.
     */
    @Override
    public DeptSchedule[] getCreatedArray(DeptSchedule expectedCreatedObj) {
        return new DeptSchedule[]{DEPT_SCHEDULE_1, DEPT_SCHEDULE_2, expectedCreatedObj};
    }

    /**
     * Returns the all {@code DeptSchedule} from the test data without the deleted {@code DeptSchedule}.
     *
     * @return the all {@code DeptSchedule} from the test data without the deleted {@code DeptSchedule}.
     */
    @Override
    public DeptSchedule[] getDeletedArray() {
        return new DeptSchedule[]{DEPT_SCHEDULE_2};
    }

    /**
     * Returns the all {@code DeptSchedule} from the test data.
     *
     * @return the all {@code DeptSchedule} from the test data.
     */
    @Override
    public DeptSchedule[] getAllArray() {
        return new DeptSchedule[]{DEPT_SCHEDULE_1, DEPT_SCHEDULE_2};
    }
}
