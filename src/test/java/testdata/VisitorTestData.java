package testdata;

import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDateTime;

/**
 * Test data for {@code Visitor} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class VisitorTestData extends AbstractTestData <Visitor> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final Visitor
            VISITOR_1 = new Visitor(10000, "Иванов", "Иван", "Иванович",
            "300320181", "Посещение отдела кадров.",
            LocalDateTime.of(2018, 30, 3, 8, 45),
            LocalDateTime.of(2018, 30, 3, 13, 52)),
            VISITOR_2 = new Visitor(10001, "Петров", "Пётр", "Петрович",
                    "100120192", "Посещение отдела инвентаризации.",
                    LocalDateTime.of(2019, 10, 1, 10, 22),
                    LocalDateTime.of(2019, 10, 1, 13, 54)),
            VISITOR_3 = new Visitor(10002, "Сидоров", "Сидор", "Сидорович",
                    "110120191", "Посещение отдела инвентаризации.",
                    LocalDateTime.of(2019, 10, 1, 12, 35),
                    LocalDateTime.of(2019, 10, 1, 12, 44)),
            VISITOR_4 = new Visitor(10003, "Иванов", "Иван", "Иванович",
                    "110120192", "Встреча с секретарём.",
                    LocalDateTime.of(2019, 10, 1, 12, 40),
                    null),
            VISITOR_5 = new Visitor(10004, "Иванов", "Пётр", "Григорьевич",
                    "110120193", "Посещение директора.",
                    null,
                    null);

    /**
     * VISITOR_4 with an updated data.
     *
     * @see VisitorTestData#VISITOR_4
     */
    public static final Visitor UPDATED_VISITOR_4 =
            new Visitor(10003, "Обновлённый Иванов", "Обновлённый Иван", "Обновлённый Иванович",
                    "Обновлённый 110120192", "Обновлённый Встреча с секретарём.",
                    LocalDateTime.of(2019, 10, 1, 13, 40),
                    LocalDateTime.of(2019, 10, 1, 14, 40));
}
