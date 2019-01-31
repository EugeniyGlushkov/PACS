package testdata;

import ru.alvisid.pacs.model.EmpSchedule;

public class EmpScheduleTestData extends AbstractTestData<EmpSchedule> {
    @Override
    public EmpSchedule getNew() {
        return null;
    }

    @Override
    public EmpSchedule getUpdated() {
        return null;
    }

    @Override
    public EmpSchedule getGotten() {
        return null;
    }

    @Override
    public int getDeletedId() {
        return 0;
    }

    @Override
    public EmpSchedule[] getCreatedArray(EmpSchedule expectedCreatedObj) {
        return new EmpSchedule[0];
    }

    @Override
    public EmpSchedule[] getDeletedArray() {
        return new EmpSchedule[0];
    }

    @Override
    public EmpSchedule[] getAllArray() {
        return new EmpSchedule[0];
    }
}
