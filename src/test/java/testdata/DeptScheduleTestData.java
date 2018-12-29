package testdata;

import ru.alvisid.pacs.model.DeptSchedule;

import java.time.LocalTime;

import static testdata.DepartmentTestData.*;

public class DeptScheduleTestData extends AbstractTestData <DeptSchedule> {

    public static final DeptSchedule
            DEPT_SCHEDULE_1 = new DeptSchedule(10001,
            LocalTime.of(9, 0), LocalTime.of(18, 0),
            LocalTime.of(12, 0), LocalTime.of(13, 0),
            DEPARTMENT_1),
            DEPT_SCHEDULE_2 = new DeptSchedule(10002,
                    LocalTime.of(8, 0), LocalTime.of(17, 0),
                    LocalTime.of(11, 0), LocalTime.of(12, 0),
                    DEPARTMENT_2);

    public static final DeptSchedule NEW_DEPT_SCHEDULE_3 = new DeptSchedule(null,
            LocalTime.of(9, 0), LocalTime.of(18, 0),
            LocalTime.of(12, 30), LocalTime.of(13, 30),
            DEPARTMENT_3);

    public static final DeptSchedule UPDATED_DEPT_SCHEDULE_1 = new DeptSchedule(10001,
            LocalTime.of(8, 20), LocalTime.of(17, 20),
            LocalTime.of(13, 0), LocalTime.of(14, 0),
            DEPARTMENT_1);

    @Override
    public DeptSchedule getNew() {
        return new DeptSchedule(NEW_DEPT_SCHEDULE_3);
    }

    @Override
    public DeptSchedule getUpdated() {
        return new DeptSchedule(UPDATED_DEPT_SCHEDULE_1);
    }

    @Override
    public DeptSchedule getGotten() {
        return null;
    }

    @Override
    public int getDeletedId() {
        return 0;
    }

    @Override
    public DeptSchedule[] getCreatedArray(DeptSchedule expectedCreatedObj) {
        return new DeptSchedule[0];
    }

    @Override
    public DeptSchedule[] getDeletedArray() {
        return new DeptSchedule[0];
    }

    @Override
    public DeptSchedule[] getAllArray() {
        return new DeptSchedule[0];
    }

    public DeptScheduleTestData() {
        super();
    }
}
