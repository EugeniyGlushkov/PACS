package testdata;

import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDateTime;

/**
 * Test data for {@code Visitor} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class VisitorTestData extends AbstractTestData<Visitor> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final Visitor
            VISITOR_1 = new Visitor(10001, "Иванов", "Иван", "Иванович",
            "300320181", "Посещение отдела кадров.",
            LocalDateTime.of(2018, 3, 30, 8, 45),
            LocalDateTime.of(2018, 3, 30, 13, 52)),
            VISITOR_2 = new Visitor(10002, "Петров", "Пётр", "Петрович",
                    "100120192", "Посещение отдела инвентаризации.",
                    LocalDateTime.of(2019, 1, 10, 10, 22),
                    LocalDateTime.of(2019, 1, 10, 13, 54)),
            VISITOR_3 = new Visitor(10003, "Сидоров", "Сидор", "Сидорович",
                    "110120191", "Посещение отдела инвентаризации.",
                    LocalDateTime.of(2019, 1, 10, 12, 35),
                    LocalDateTime.of(2019, 1, 10, 12, 44)),
            VISITOR_4 = new Visitor(10004, "Андреев", "Тимур", "Иванович",
                    "110120192", "Встреча с секретарём.",
                    LocalDateTime.of(2019, 1, 10, 12, 40),
                    null),
            VISITOR_5 = new Visitor(10005, "Романов", "Пётр", "Григорьевич",
                    "110120193", "Посещение директора.",
                    null,
                    null);

    /**
     * VISITOR_4 with an updated data.
     *
     * @see VisitorTestData#VISITOR_4
     */
    public static final Visitor UPDATED_VISITOR_4 =
            new Visitor(10004, "Обновлённый Иванов", "Обновлённый Иван", "Обновлённый Иванович",
                    "Обн 110120192", "Обновлённый Встреча с секретарём.",
                    LocalDateTime.of(2019, 1, 10, 13, 40),
                    LocalDateTime.of(2019, 1, 10, 14, 40));


    /**
     * New {@code Visitor} with id, enter time and exit time-null.
     */
    public static final Visitor NEW_VISITOR =
            new Visitor("Новая фамилия", "Новое имя", "Новая фамилия",
                    "110120194", "Посещение директора.",
                    null,
                    null);


    /**
     * Returns a new {@code Visitor} from the test data.
     *
     * @return the new {@code Visitor} from the test data.
     */
    @Override
    public Visitor getNew() {
        return new Visitor(NEW_VISITOR);
    }

    /**
     * Returns an updated {@code Visitor} from the test data.
     *
     * @return the updated {@code Visitor} from the test data.
     */
    @Override
    public Visitor getUpdated() {
        return new Visitor(UPDATED_VISITOR_4);
    }

    /**
     * Returns an {@code Visitor} that is equals entity which will be get from DB.
     *
     * @return the {@code Visitor} that is equals entity which will be get from DB.
     */
    @Override
    public Visitor getGotten() {
        return new Visitor(VISITOR_3);
    }

    /**
     * Returns an {@code Visitor}'s id for delete.
     *
     * @return the {@code Visitor}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return VISITOR_3.getId();
    }

    /**
     * Returns the all {@code Visitor} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code Visitor}.
     * @return the all {@code Visitor} from test data with the specified created {@code Visitor}.
     */
    @Override
    public Visitor[] getCreatedArray(Visitor expectedCreatedObj) {
        return new Visitor[]{
                VISITOR_5,
                expectedCreatedObj,
                VISITOR_4,
                VISITOR_3,
                VISITOR_2,
                VISITOR_1
        };
    }

    /**
     * Returns the all {@code Visitor} from the test data without the deleted {@code Visitor}.
     *
     * @return the all {@code Visitor} from the test data without the deleted {@code Visitor}.
     */
    @Override
    public Visitor[] getDeletedArray() {
        return new Visitor[]{
                VISITOR_5,
                VISITOR_4,
                VISITOR_2,
                VISITOR_1
        };
    }

    /**
     * Returns the all {@code Visitor} from the test data.
     *
     * @return the all {@code Visitor} from the test data.
     */
    @Override
    public Visitor[] getAllArray() {
        return new Visitor[]{
                VISITOR_5,
                VISITOR_4,
                VISITOR_3,
                VISITOR_2,
                VISITOR_1
        };
    }

    public Visitor[] getAllByEnterTimeBetweenArray() {
        return new Visitor[]{
                VISITOR_4,
                VISITOR_3,
                VISITOR_2
        };
    }
}
