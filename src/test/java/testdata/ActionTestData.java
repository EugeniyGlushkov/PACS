package testdata;

import ru.alvisid.pacs.model.Action;

import java.time.LocalDateTime;

import static testdata.PointActionTestData.*;
import static testdata.EmployeeTestData.*;

/**
 * Test data for {@code Action} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class ActionTestData extends AbstractTestData <Action> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final Action
            ACTION_1 = new Action(1, EMPLOYEE_1, POINT_ACTION_1, LocalDateTime.of(2018, 7, 4, 9, 22, 17)),
            ACTION_2 = new Action(2, EMPLOYEE_2, POINT_ACTION_3, LocalDateTime.of(2018, 7, 4, 8, 32, 25)),
            ACTION_3 = new Action(3, EMPLOYEE_1, POINT_ACTION_6, LocalDateTime.of(2018, 7, 4, 9, 33, 27)),
            ACTION_4 = new Action(4, EMPLOYEE_4, POINT_ACTION_1, LocalDateTime.of(2018, 7, 4, 7, 30, 11)),
            ACTION_5 = new Action(5, EMPLOYEE_4, POINT_ACTION_5, LocalDateTime.of(2018, 7, 4, 10, 54, 11)),
            ACTION_6 = new Action(6, EMPLOYEE_1, POINT_ACTION_4, LocalDateTime.of(2018, 7, 4, 12, 15, 8)),
            ACTION_7 = new Action(7, EMPLOYEE_4, POINT_ACTION_4, LocalDateTime.of(2018, 7, 4, 13, 20, 22));

    /**
     * New {@code Action} with id-null.
     */
    public static final Action
            NEW_ACTION = new Action(EMPLOYEE_2, POINT_ACTION_2, LocalDateTime.of(2018, 7, 4, 17, 30, 16));

    /**
     * ABSENCE_3 with an updated data.
     *
     * @see AbsenceTestData#ABSENCE_3
     */
    public static final Action
            UPDATED_ACTION_4 = new Action(4, EMPLOYEE_4, POINT_ACTION_3, LocalDateTime.of(2018, 7, 4, 9, 25, 18));

    /**
     * Returns a new {@code Action} from the test data.
     *
     * @return the new {@code Action} from the test data.
     */
    @Override
    public Action getNew() {
        return new Action(NEW_ACTION);
    }

    /**
     * Returns an updated {@code Action} from the test data.
     *
     * @return the updated {@code Action} from the test data.
     */
    @Override
    public Action getUpdated() {
        return new Action(UPDATED_ACTION_4);
    }

    /**
     * Returns an {@code Action} that is equals entity which will be get from DB.
     *
     * @return the {@code Action} that is equals entity which will be get from DB.
     */
    @Override
    public Action getGotten() {
        return new Action(ACTION_2);
    }

    /**
     * Returns an {@code Action}'s id for delete.
     *
     * @return the {@code Action}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return ACTION_6.getId();
    }

    /**
     * Returns the all {@code Action} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code Action}.
     * @return the all {@code Action} from test data with the specified created {@code Action}.
     */
    @Override
    public Action[] getCreatedArray(Action expectedCreatedObj) {
        return new Action[]{
                ACTION_4,
                ACTION_1,
                expectedCreatedObj,
                ACTION_2,
                ACTION_6,
                ACTION_7,
                ACTION_5,
                ACTION_3
        };
    }

    /**
     * Returns the all {@code Action} from the test data without the deleted {@code Action}.
     *
     * @return the all {@code Action} from the test data without the deleted {@code Action}.
     */
    @Override
    public Action[] getDeletedArray() {
        return new Action[]{
                ACTION_4,
                ACTION_1,
                ACTION_2,
                ACTION_7,
                ACTION_5,
                ACTION_3
        };
    }

    /**
     * Returns the all {@code Action} from the test data.
     *
     * @return the all {@code Action} from the test data.
     */
    @Override
    public Action[] getAllArray() {
        return new Action[]{
                ACTION_4,
                ACTION_1,
                ACTION_2,
                ACTION_6,
                ACTION_7,
                ACTION_5,
                ACTION_3
        };
    }
}
