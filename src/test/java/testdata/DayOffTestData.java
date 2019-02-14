package testdata;

import ru.alvisid.pacs.model.DayOff;

import java.time.LocalDate;

import static testdata.DepartmentTestData.*;

/**
 * Test data for {@code DayOff} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DayOffTestData extends AbstractTestData<DayOff> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final DayOff
            DAY_OFF_1 = new DayOff(1, DEPARTMENT_1, LocalDate.of(2018, 1, 1)),
            DAY_OFF_2 = new DayOff(2, DEPARTMENT_2, LocalDate.of(2018, 1, 1)),
            DAY_OFF_3 = new DayOff(3, DEPARTMENT_3, LocalDate.of(2018, 1, 1)),
            DAY_OFF_4 = new DayOff(4, DEPARTMENT_1, LocalDate.of(2018, 1, 2)),
            DAY_OFF_5 = new DayOff(5, DEPARTMENT_1, LocalDate.of(2018, 5, 1)),
            DAY_OFF_6 = new DayOff(6, DEPARTMENT_2, LocalDate.of(2018, 5, 1)),
            DAY_OFF_7 = new DayOff(7, DEPARTMENT_3, LocalDate.of(2018, 5, 1)),
            DAY_OFF_8 = new DayOff(8, DEPARTMENT_1, LocalDate.of(2018, 5, 9)),
            DAY_OFF_9 = new DayOff(9, DEPARTMENT_2, LocalDate.of(2018, 5, 9)),
            DAY_OFF_10 = new DayOff(10, DEPARTMENT_3, LocalDate.of(2018, 5, 9));

    /**
     * New {@code DayOff} with id-null.
     */
    public static final DayOff
            NEW_DAY_OFF = new DayOff(DEPARTMENT_1, LocalDate.of(2018, 5, 10));

    /**
     * DAY_OFF_6 with an updated data.
     *
     * @see DayOffTestData#DAY_OFF_6
     */
    public static final DayOff UPDATED_DAY_OFF_6 =  new DayOff(6, DEPARTMENT_3, LocalDate.of(2018, 5, 2));

    /**
     * Returns a new {@code DayOff} from the test data.
     *
     * @return the new {@code DayOff} from the test data.
     */
    @Override
    public DayOff getNew() {
        return new DayOff(NEW_DAY_OFF);
    }

    /**
     * Returns an updated {@code DayOff} from the test data.
     *
     * @return the updated {@code DayOff} from the test data.
     */
    @Override
    public DayOff getUpdated() {
        return new DayOff(UPDATED_DAY_OFF_6);
    }

    /**
     * Returns a {@code DayOff} that is equals entity which will be get from DB.
     *
     * @return the {@code DayOff} that is equals entity which will be get from DB.
     */
    @Override
    public DayOff getGotten() {
        return new DayOff(DAY_OFF_4);
    }

    /**
     * Returns a {@code DayOff}'s id for delete.
     *
     * @return the {@code DayOff}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return DAY_OFF_8.getId();
    }

    /**
     * Returns the all {@code DayOff} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code DayOff}.
     * @return the all {@code DayOff} from test data with the specified created {@code DayOff}.
     */
    @Override
    public DayOff[] getCreatedArray(DayOff expectedCreatedObj) {
        return new DayOff[]{
                DAY_OFF_1,
                DAY_OFF_4,
                DAY_OFF_5,
                DAY_OFF_8,
                expectedCreatedObj,
                DAY_OFF_2,
                DAY_OFF_6,
                DAY_OFF_9,
                DAY_OFF_3,
                DAY_OFF_7,
                DAY_OFF_10
        };
    }

    /**
     * Returns the all {@code DayOff} from the test data without the deleted {@code DayOff}.
     *
     * @return the all {@code DayOff} from the test data without the deleted {@code DayOff}.
     */
    @Override
    public DayOff[] getDeletedArray() {
        return new DayOff[]{
                DAY_OFF_1,
                DAY_OFF_4,
                DAY_OFF_5,
                DAY_OFF_2,
                DAY_OFF_6,
                DAY_OFF_9,
                DAY_OFF_3,
                DAY_OFF_7,
                DAY_OFF_10
        };
    }

    /**
     * Returns the all {@code DayOff} from the test data.
     *
     * @return the all {@code DayOff} from the test data.
     */
    @Override
    public DayOff[] getAllArray() {
        return new DayOff[]{
                DAY_OFF_1,
                DAY_OFF_4,
                DAY_OFF_5,
                DAY_OFF_8,
                DAY_OFF_2,
                DAY_OFF_6,
                DAY_OFF_9,
                DAY_OFF_3,
                DAY_OFF_7,
                DAY_OFF_10
        };
    }
}
