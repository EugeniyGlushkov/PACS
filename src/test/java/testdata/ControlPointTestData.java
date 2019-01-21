package testdata;

import ru.alvisid.pacs.model.ControlPoint;

/**
 * Test data for {@code ControlPoint} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class ControlPointTestData extends AbstractTestData<ControlPoint> {

    /**
     * Object which represents existing entity in the data base.
     */
    public static final ControlPoint
            CONTROL_POINT_1 = new ControlPoint(1, "SA1232", "Турникет №1 на главной проходной."),
            CONTROL_POINT_2 = new ControlPoint(2, "SQ145", "Турникет №2 на главной проходной."),
            CONTROL_POINT_3 = new ControlPoint(3, "45SDD", "Электронный замок, дверь в подсобное помещение."),
            CONTROL_POINT_4 = new ControlPoint(4, "C34", "Электронный замок, лифт №1, первый этаж.");

    /**
     * New {@code ControlPoint} with id-null.
     */
    public static final ControlPoint NEW_CONTROL_POINT =
            new ControlPoint("NEWCODE", "Новая контрольная точка.");

    /**
     * CONTROL_POINT_1 with an updated data.
     *
     * @see ControlPointTestData#CONTROL_POINT_1
     */
    public static final ControlPoint UPDATED_CONTROL_POINT_1 =
            new ControlPoint(1, "UPDATED", "Updated description.");


    /**
     * Returns a new {@code ControlPoint} from the test data.
     *
     * @return the new {@code ControlPoint} from the test data.
     */
    @Override
    public ControlPoint getNew() {
        return new ControlPoint(NEW_CONTROL_POINT);
    }

    /**
     * Returns an updated {@code ControlPoint} from the test data.
     *
     * @return the updated {@code ControlPoint} from the test data.
     */
    @Override
    public ControlPoint getUpdated() {
        return new ControlPoint(UPDATED_CONTROL_POINT_1);
    }

    /**
     * Returns an {@code ControlPoint} that is equals entity which will be get from DB.
     *
     * @return the {@code ControlPoint} that is equals entity which will be get from DB.
     */
    @Override
    public ControlPoint getGotten() {
        return new ControlPoint(CONTROL_POINT_1);
    }

    /**
     * Returns an {@code ControlPoint}'s id for delete.
     *
     * @return the {@code ControlPoint}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return CONTROL_POINT_1.getId();
    }

    /**
     * Returns the all {@code ControlPoint} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code ControlPoint}.
     * @return the all {@code ControlPoint} from test data with the specified created {@code ControlPoint}.
     */
    @Override
    public ControlPoint[] getCreatedArray(ControlPoint expectedCreatedObj) {
        return new ControlPoint[]{
                CONTROL_POINT_3,
                CONTROL_POINT_4,
                expectedCreatedObj,
                CONTROL_POINT_1,
                CONTROL_POINT_2
        };
    }

    /**
     * Returns the all {@code ControlPoint} from the test data without the deleted {@code ControlPoint}.
     *
     * @return the all {@code ControlPoint} from the test data without the deleted {@code ControlPoint}.
     */
    @Override
    public ControlPoint[] getDeletedArray() {
        return new ControlPoint[]{
                CONTROL_POINT_3,
                CONTROL_POINT_4,
                CONTROL_POINT_2
        };
    }

    /**
     * Returns the all {@code ControlPoint} from the test data.
     *
     * @return the all {@code ControlPoint} from the test data.
     */
    @Override
    public ControlPoint[] getAllArray() {
        return new ControlPoint[]{
                CONTROL_POINT_3,
                CONTROL_POINT_4,
                CONTROL_POINT_1,
                CONTROL_POINT_2
        };
    }
}
