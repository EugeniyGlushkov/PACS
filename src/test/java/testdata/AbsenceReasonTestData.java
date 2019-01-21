package testdata;

import ru.alvisid.pacs.model.AbsenceReason;

/**
 * Test data for {@code AbsenceReason} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class AbsenceReasonTestData extends AbstractTestData <AbsenceReason> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final AbsenceReason
            ABSENCE_REASON_1 = new AbsenceReason(1, "больничный", "отсутствие по болезни."),
            ABSENCE_REASON_2 = new AbsenceReason(2, "командировка", "нахождение в командировке."),
            ABSENCE_REASON_3 = new AbsenceReason(3, "отпуск", "отдых в отпуске."),
            ABSENCE_REASON_4 = new AbsenceReason(4, "отгул", "отсутствие по причине отгула.");

    /**
     * New {@code AbsenceReason} with id-null.
     */
    public static final AbsenceReason NEW_ABSENCE_REASON =
            new AbsenceReason("новая причина отсутствия", "Новое описание");

    /**
     * ABSENCE_REASON_2 with an updated data.
     *
     * @see AbsenceReasonTestData#ABSENCE_REASON_2
     */
    public static final AbsenceReason UPDATED_ABSENCE_REASON_2 =
            new AbsenceReason(2, "обновленный больничный", "обновленное отсутствие по болезни.");

    /**
     * Returns a new {@code AbsenceReason} from the test data.
     *
     * @return the new {@code AbsenceReason} from the test data.
     */
    @Override
    public AbsenceReason getNew() {
        return new AbsenceReason(NEW_ABSENCE_REASON);
    }

    /**
     * Returns an updated {@code AbsenceReason} from the test data.
     *
     * @return the updated {@code AbsenceReason} from the test data.
     */
    @Override
    public AbsenceReason getUpdated() {
        return new AbsenceReason(UPDATED_ABSENCE_REASON_2);
    }

    /**
     * Returns an {@code AbsenceReason} that is equals entity which will be get from DB.
     *
     * @return the {@code AbsenceReason} that is equals entity which will be get from DB.
     */
    @Override
    public AbsenceReason getGotten() {
        return new AbsenceReason(ABSENCE_REASON_2);
    }

    /**
     * Returns an {@code AbsenceReason}'s id for delete.
     *
     * @return the {@code AbsenceReason}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return ABSENCE_REASON_2.getId();
    }

    /**
     * Returns the all {@code AbsenceReason} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code AbsenceReason}.
     * @return the all {@code AbsenceReason} from test data with the specified created {@code AbsenceReason}.
     */
    @Override
    public AbsenceReason[] getCreatedArray(AbsenceReason expectedCreatedObj) {
        return new AbsenceReason[]{
                ABSENCE_REASON_1,
                ABSENCE_REASON_2,
                expectedCreatedObj,
                ABSENCE_REASON_4,
                ABSENCE_REASON_3
        };
    }

    /**
     * Returns the all {@code AbsenceReason} from the test data without the deleted {@code AbsenceReason}.
     *
     * @return the all {@code AbsenceReason} from the test data without the deleted {@code AbsenceReason}.
     */
    @Override
    public AbsenceReason[] getDeletedArray() {
        return new AbsenceReason[]{
                ABSENCE_REASON_1,
                ABSENCE_REASON_4,
                ABSENCE_REASON_3
        };
    }

    /**
     * Returns the all {@code AbsenceReason} from the test data.
     *
     * @return the all {@code AbsenceReason} from the test data.
     */
    @Override
    public AbsenceReason[] getAllArray() {
        return new AbsenceReason[]{
                ABSENCE_REASON_1,
                ABSENCE_REASON_2,
                ABSENCE_REASON_4,
                ABSENCE_REASON_3
        };
    }
}
