package testdata;

import ru.alvisid.pacs.model.PointPermit;

import static testdata.PointActionTestData.*;
import static testdata.EmployeeTestData.*;

/**
 * Test data for {@code PointPermit} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class PointPermitTestData extends AbstractTestData<PointPermit> {
    /**
     * Object which represents existing entity in the data base.
     */
    public final static PointPermit
            POINT_PERMIT_1 = new PointPermit(1, POINT_ACTION_1, EMPLOYEE_1),
            POINT_PERMIT_2 = new PointPermit(2, POINT_ACTION_2, EMPLOYEE_1),
            POINT_PERMIT_3 = new PointPermit(3, POINT_ACTION_3, EMPLOYEE_1),
            POINT_PERMIT_4 = new PointPermit(4, POINT_ACTION_4, EMPLOYEE_1),
            POINT_PERMIT_5 = new PointPermit(5, POINT_ACTION_1, EMPLOYEE_2),
            POINT_PERMIT_6 = new PointPermit(6, POINT_ACTION_2, EMPLOYEE_2),
            POINT_PERMIT_7 = new PointPermit(7, POINT_ACTION_3, EMPLOYEE_2),
            POINT_PERMIT_8 = new PointPermit(8, POINT_ACTION_4, EMPLOYEE_2),
            POINT_PERMIT_9 = new PointPermit(9, POINT_ACTION_1, EMPLOYEE_3),
            POINT_PERMIT_10 = new PointPermit(10, POINT_ACTION_2, EMPLOYEE_3),
            POINT_PERMIT_11 = new PointPermit(11, POINT_ACTION_3, EMPLOYEE_3),
            POINT_PERMIT_12 = new PointPermit(12, POINT_ACTION_4, EMPLOYEE_3),
            POINT_PERMIT_13 = new PointPermit(13, POINT_ACTION_1, EMPLOYEE_4),
            POINT_PERMIT_14 = new PointPermit(14, POINT_ACTION_2, EMPLOYEE_4),
            POINT_PERMIT_15 = new PointPermit(15, POINT_ACTION_3, EMPLOYEE_4),
            POINT_PERMIT_16 = new PointPermit(16, POINT_ACTION_4, EMPLOYEE_4),
            POINT_PERMIT_17 = new PointPermit(17, POINT_ACTION_1, EMPLOYEE_5),
            POINT_PERMIT_18 = new PointPermit(18, POINT_ACTION_2, EMPLOYEE_5),
            POINT_PERMIT_19 = new PointPermit(19, POINT_ACTION_3, EMPLOYEE_5),
            POINT_PERMIT_20 = new PointPermit(20, POINT_ACTION_4, EMPLOYEE_5),
            POINT_PERMIT_21 = new PointPermit(21, POINT_ACTION_1, EMPLOYEE_6),
            POINT_PERMIT_22 = new PointPermit(22, POINT_ACTION_2, EMPLOYEE_6),
            POINT_PERMIT_23 = new PointPermit(23, POINT_ACTION_3, EMPLOYEE_6),
            POINT_PERMIT_24 = new PointPermit(24, POINT_ACTION_4, EMPLOYEE_6),
            POINT_PERMIT_25 = new PointPermit(25, POINT_ACTION_5, EMPLOYEE_1),
            POINT_PERMIT_26 = new PointPermit(26, POINT_ACTION_6, EMPLOYEE_1),
            POINT_PERMIT_27 = new PointPermit(27, POINT_ACTION_5, EMPLOYEE_4),
            POINT_PERMIT_28 = new PointPermit(28, POINT_ACTION_6, EMPLOYEE_4),
            POINT_PERMIT_29 = new PointPermit(29, POINT_ACTION_6, EMPLOYEE_2),
            POINT_PERMIT_30 = new PointPermit(30, POINT_ACTION_6, EMPLOYEE_3);

    /**
     * New {@code PointPermit} with id-null.
     */
    public final static PointPermit
            NEW_POINT_PERMIT = new PointPermit(POINT_ACTION_6, EMPLOYEE_5);

    /**
     * POINT_PERMIT_16 with an updated data.
     *
     * @see PointPermitTestData#POINT_PERMIT_16
     */
    public final static PointPermit
            UPDATE_POINT_PERMIT_16 = new PointPermit(16, POINT_ACTION_5, EMPLOYEE_6);

    /**
     * Returns a new {@code PointPermit} from the test data.
     *
     * @return the new {@code PointPermit} from the test data.
     */
    @Override
    public PointPermit getNew() {
        return new PointPermit(NEW_POINT_PERMIT);
    }

    /**
     * Returns an updated {@code PointPermit} from the test data.
     *
     * @return the updated {@code PointPermit} from the test data.
     */
    @Override
    public PointPermit getUpdated() {
        return new PointPermit(UPDATE_POINT_PERMIT_16);
    }

    /**
     * Returns an {@code PointPermit} that is equals entity which will be get from DB.
     *
     * @return the {@code PointPermit} that is equals entity which will be get from DB.
     */
    @Override
    public PointPermit getGotten() {
        return new PointPermit(POINT_PERMIT_22);
    }

    /**
     * Returns an {@code PointPermit}'s id for delete.
     *
     * @return the {@code PointPermit}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return POINT_PERMIT_13.getId();
    }

    /**
     * Returns the all {@code PointPermit} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created object.
     * @return the all {@code PointPermit} from test data with the specified created {@code PointPermit}.
     */
    @Override
    public PointPermit[] getCreatedArray(PointPermit expectedCreatedObj) {
        return new PointPermit[]{
                POINT_PERMIT_25,
                POINT_PERMIT_27,
                expectedCreatedObj,
                POINT_PERMIT_30,
                POINT_PERMIT_26,
                POINT_PERMIT_28,
                POINT_PERMIT_29,
                POINT_PERMIT_21,
                POINT_PERMIT_22,
                POINT_PERMIT_17,
                POINT_PERMIT_18,
                POINT_PERMIT_9,
                POINT_PERMIT_10,
                POINT_PERMIT_1,
                POINT_PERMIT_2,
                POINT_PERMIT_13,
                POINT_PERMIT_14,
                POINT_PERMIT_5,
                POINT_PERMIT_6,
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
                POINT_PERMIT_8
        };
    }

    /**
     * Returns the all {@code PointPermit} from the test data without the deleted {@code PointPermit}.
     *
     * @return the all {@code PointPermit} from the test data without the deleted {@code PointPermit}.
     */
    @Override
    public PointPermit[] getDeletedArray() {
        return new PointPermit[]{
                POINT_PERMIT_25,
                POINT_PERMIT_27,
                POINT_PERMIT_30,
                POINT_PERMIT_26,
                POINT_PERMIT_28,
                POINT_PERMIT_29,
                POINT_PERMIT_21,
                POINT_PERMIT_22,
                POINT_PERMIT_17,
                POINT_PERMIT_18,
                POINT_PERMIT_9,
                POINT_PERMIT_10,
                POINT_PERMIT_1,
                POINT_PERMIT_2,
                POINT_PERMIT_14,
                POINT_PERMIT_5,
                POINT_PERMIT_6,
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
                POINT_PERMIT_8
        };
    }

    /**
     * Returns the all {@code PointPermit} from the test data.
     *
     * @return the all {@code PointPermit} from the test data.
     */
    @Override
    public PointPermit[] getAllArray() {
        return new PointPermit[]{
                POINT_PERMIT_25,
                POINT_PERMIT_27,
                POINT_PERMIT_30,
                POINT_PERMIT_26,
                POINT_PERMIT_28,
                POINT_PERMIT_29,
                POINT_PERMIT_21,
                POINT_PERMIT_22,
                POINT_PERMIT_17,
                POINT_PERMIT_18,
                POINT_PERMIT_9,
                POINT_PERMIT_10,
                POINT_PERMIT_1,
                POINT_PERMIT_2,
                POINT_PERMIT_13,
                POINT_PERMIT_14,
                POINT_PERMIT_5,
                POINT_PERMIT_6,
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
                POINT_PERMIT_8
        };
    }
}
