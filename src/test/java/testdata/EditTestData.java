package testdata;

import ru.alvisid.pacs.model.Edit;
import ru.alvisid.pacs.model.EditType;

import java.time.LocalDateTime;

import static testdata.EmployeeTestData.*;

/**
 * Test data for {@code Edit} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class EditTestData extends AbstractTestData<Edit> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final Edit
            EDIT_1 = new Edit(1, EditType.CREATE, EMPLOYEE_2,
            LocalDateTime.of(2017, 1, 23, 9, 22, 17),
            "[CREATE], employee [id=10010, {Бухгалтерия}, {бухгалтер}, {Прошкина Ольга Игоревна}]"),
            EDIT_2 = new Edit(2, EditType.UPDATE, EMPLOYEE_2,
            LocalDateTime.of(2017, 5, 13, 12, 15, 19),
            "[UPDATE], old employee [id=10010, {Бухгалтерия}, {бухгалтер}, {Прошкина Ольга Игоревна}] -> new employee [id=10010, {Бухгалтерия}, {бухгалтер}, {Батурина Ольга Игоревна}]"),
            EDIT_3 = new Edit(3, EditType.DELETE, EMPLOYEE_6,
                    LocalDateTime.of(2017, 6, 27, 11, 47, 7),
                    "[DELETE], schedule [id=10004] of the employee [id=10010]"),
            EDIT_4 = new Edit(4, EditType.UPDATE, EMPLOYEE_6,
                    LocalDateTime.of(2017, 11, 9, 17, 30, 56),
                    "[UPDATE], depertment schedule [id=10002], old value: department [id=2] [08:30:00, 17:30:00, 11:00:00, 12:00:00] -> new value: department [id=2] [08:00:00, 17:00:00, 11:00:00, 12:00:00]");

    /**
     * New {@code Edit} with id-null.
     */
    public static final Edit
    NEW_EDIT = new Edit(EditType.CREATE, EMPLOYEE_6,
            LocalDateTime.of(2018, 10, 22, 14, 36, 47),
            "[CREATE], schedule [id=10004] of the department [id=3] [09:00:00, 18:00:00, 12:30:00, 13:30:00]");

    /**
     * POINT_ACTION_5 with an updated data.
     *
     * @see PointActionTestData#POINT_ACTION_5
     */
}
