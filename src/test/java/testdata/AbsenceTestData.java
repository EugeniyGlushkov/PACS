package testdata;

import ru.alvisid.pacs.model.Absence;

import java.time.LocalDate;

import static testdata.EmployeeTestData.*;
import static testdata.AbsenceReasonTestData.*;

/**
 * Test data for {@code Absence} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class AbsenceTestData extends AbstractTestData<Absence> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final Absence
            ABSENCE_1 = new Absence(1, EMPLOYEE_1, ABSENCE_REASON_1,
            LocalDate.of(2018, 5, 13), LocalDate.of(2018, 5, 31),
            "больничный лист №12345."),
            ABSENCE_2 = new Absence(2, EMPLOYEE_4, ABSENCE_REASON_3,
                    LocalDate.of(2018, 8, 2), LocalDate.of(2018, 8, 16),
                    "Отпуск."),
            ABSENCE_3 = new Absence(3, EMPLOYEE_2, ABSENCE_REASON_2,
                    LocalDate.of(2018, 6, 8), LocalDate.of(2018, 7, 1),
                    "Служебная командировка в Тольятти."),
            ABSENCE_4 = new Absence(4, EMPLOYEE_6, ABSENCE_REASON_4,
                    LocalDate.of(2018, 7, 8), LocalDate.of(2018, 7, 8),
                    "Отгул.");

    /**
     * New {@code Absence} with id-null.
     */
    public static final Absence NEW_ABSENCE = new Absence(EMPLOYEE_5, ABSENCE_REASON_3,
            LocalDate.of(2018, 12, 25), LocalDate.of(2019, 1, 22),
            "Отпуск.");


    /**
     * ABSENCE_3 with an updated data.
     *
     * @see AbsenceTestData#ABSENCE_3
     */
    public static final Absence UPDATED_ABSENCE_3 = new Absence(3, EMPLOYEE_5, ABSENCE_REASON_2,
            LocalDate.of(2018, 7, 10), LocalDate.of(2018, 8, 1),
            "Командировка в Анапу.");

    /**
     * Returns a new {@code Absence} from the test data.
     *
     * @return the new {@code Absence} from the test data.
     */
    @Override
    public Absence getNew() {
        return new Absence(NEW_ABSENCE);
    }

    /**
     * Returns an updated {@code Absence} from the test data.
     *
     * @return the updated {@code Absence} from the test data.
     */
    @Override
    public Absence getUpdated() {
        return new Absence(UPDATED_ABSENCE_3);
    }

    /**
     * Returns an {@code Absence} that is equals entity which will be get from DB.
     *
     * @return the {@code Absence} that is equals entity which will be get from DB.
     */
    @Override
    public Absence getGotten() {
        return new Absence(ABSENCE_2);
    }

    /**
     * Returns an {@code Absence}'s id for delete.
     *
     * @return the {@code Absence}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return ABSENCE_4.getId();
    }

    /**
     * Returns the all {@code Absence} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code Absence}.
     * @return the all {@code Absence} from test data with the specified created {@code Absence}.
     */
    @Override
    public Absence[] getCreatedArray(Absence expectedCreatedObj) {
        return new Absence[]{
                ABSENCE_4,
                expectedCreatedObj,
                ABSENCE_1,
                ABSENCE_2,
                ABSENCE_3
        };
    }

    /**
     * Returns the all {@code Absence} from the test data without the deleted {@code Absence}.
     *
     * @return the all {@code Absence} from the test data without the deleted {@code Absence}.
     */
    @Override
    public Absence[] getDeletedArray() {
        return new Absence[]{
                ABSENCE_1,
                ABSENCE_2,
                ABSENCE_3
        };
    }

    /**
     * Returns the all {@code Absence} from the test data.
     *
     * @return the all {@code Absence} from the test data.
     */
    @Override
    public Absence[] getAllArray() {
        return new Absence[]{
                ABSENCE_4,
                ABSENCE_1,
                ABSENCE_2,
                ABSENCE_3
        };
    }
}
