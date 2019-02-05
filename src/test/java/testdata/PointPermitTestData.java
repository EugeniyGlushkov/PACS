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
            POINT_PERMIT_29 = new PointPermit(29, POINT_ACTION_5, EMPLOYEE_2),
            POINT_PERMIT_30 = new PointPermit(30, POINT_ACTION_5, EMPLOYEE_3);

    /**
     * New {@code PointPermit} with id-null.
     */
    public final static PointPermit
            NEW_POINT_PERMIT = new PointPermit(POINT_ACTION_6, EMPLOYEE_3);

    @Override
    public PointPermit getNew() {
        return null;
    }

    @Override
    public PointPermit getUpdated() {
        return null;
    }

    @Override
    public PointPermit getGotten() {
        return null;
    }

    @Override
    public int getDeletedId() {
        return 0;
    }

    @Override
    public PointPermit[] getCreatedArray(PointPermit expectedCreatedObj) {
        return new PointPermit[0];
    }

    @Override
    public PointPermit[] getDeletedArray() {
        return new PointPermit[0];
    }

    @Override
    public PointPermit[] getAllArray() {
        return new PointPermit[0];
    }
}
