package testdata;

import ru.alvisid.pacs.model.ActionType;
import ru.alvisid.pacs.model.PointAction;

import static testdata.ControlPointTestData.*;

public class PointActionTestData extends AbstractTestData <PointAction> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final PointAction
            POINT_ACTION_1 = new PointAction(1, CONTROL_POINT_1, ActionType.ENTER),
            POINT_ACTION_2 = new PointAction(2, CONTROL_POINT_1, ActionType.EXIT),
            POINT_ACTION_3 = new PointAction(3, CONTROL_POINT_2, ActionType.ENTER),
            POINT_ACTION_4 = new PointAction(4, CONTROL_POINT_2, ActionType.EXIT),
            POINT_ACTION_5 = new PointAction(5, CONTROL_POINT_3, ActionType.UNLOCK),
            POINT_ACTION_6 = new PointAction(6, CONTROL_POINT_4, ActionType.UNLOCK);

    /**
     * New {@code PointAction} with id-null.
     */
    public static final PointAction
            NEW_POINT_ACTION = new PointAction(CONTROL_POINT_3, ActionType.LOCK);

    /**
     * POINT_ACTION_5 with an updated data.
     *
     * @see PointActionTestData#POINT_ACTION_5
     */
    public static final PointAction UPDATED_POINT_ACTION_5 = new PointAction(5, CONTROL_POINT_4, ActionType.LOCK);

    /**
     * Returns a new {@code PointAction} from the test data.
     *
     * @return the new {@code PointAction} from the test data.
     */
    @Override
    public PointAction getNew() {
        return new PointAction(NEW_POINT_ACTION);
    }

    /**
     * Returns an updated {@code PointAction} from the test data.
     *
     * @return the updated {@code PointAction} from the test data.
     */
    @Override
    public PointAction getUpdated() {
        return new PointAction(UPDATED_POINT_ACTION_5);
    }

    /**
     * Returns an {@code PointAction} that is equals entity which will be get from DB.
     *
     * @return the {@code PointAction} that is equals entity which will be get from DB.
     */
    @Override
    public PointAction getGotten() {
        return new PointAction(POINT_ACTION_3);
    }

    /**
     * Returns an {@code PointAction}'s id for delete.
     *
     * @return the {@code PointAction}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return POINT_ACTION_4.getId();
    }

    /**
     * Returns the all {@code PointAction} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created object.
     * @return the all {@code PointAction} from test data with the specified created {@code PointAction}.
     */
    @Override
    public PointAction[] getCreatedArray(PointAction expectedCreatedObj) {
        return new PointAction[]{
                expectedCreatedObj,
                POINT_ACTION_5,
                POINT_ACTION_6,
                POINT_ACTION_1,
                POINT_ACTION_2,
                POINT_ACTION_3,
                POINT_ACTION_4
        };
    }

    /**
     * Returns the all {@code PointAction} from the test data without the deleted {@code PointAction}.
     *
     * @return the all {@code PointAction} from the test data without the deleted {@code PointAction}.
     */
    @Override
    public PointAction[] getDeletedArray() {
        return new PointAction[]{
                POINT_ACTION_5,
                POINT_ACTION_6,
                POINT_ACTION_1,
                POINT_ACTION_2,
                POINT_ACTION_3
        };
    }

    /**
     * Returns the all {@code PointAction} from the test data.
     *
     * @return the all {@code PointAction} from the test data.
     */
    @Override
    public PointAction[] getAllArray() {
        return new PointAction[]{
                POINT_ACTION_5,
                POINT_ACTION_6,
                POINT_ACTION_1,
                POINT_ACTION_2,
                POINT_ACTION_3,
                POINT_ACTION_4
        };
    }

    /**
     * Constructs new {@code PointActionTestData} and sets
     * {@code IGNORING_FIELDS} field
     * in the superclass.
     *
     * @see AbstractTestData#IGNORING_FIELDS
     */
    public PointActionTestData() {
        super("controlPoint");
    }
}
