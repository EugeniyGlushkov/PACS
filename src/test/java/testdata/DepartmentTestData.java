package testdata;

import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.model.WeekDay;

import java.util.*;

import static testdata.DeptScheduleTestData.*;

/**
 * Test data for {@code Department} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DepartmentTestData extends AbstractTestData <Department> {
    /**
     * Collection with weekend's days.
     */
    public static final List <WeekDay>
            WEEKENDS_1 = new ArrayList <>(),
            WEEKENDS_2 = new ArrayList <>(),
            WEEKENDS_3 = new ArrayList <>(),
            UPDATED_WEEKENDS_1 = new ArrayList <>();

    static {
        Collections.addAll(WEEKENDS_1, WeekDay.SATURDAY, WeekDay.SUNDAY);
        Collections.addAll(WEEKENDS_2, WeekDay.SATURDAY, WeekDay.SUNDAY);
        Collections.addAll(WEEKENDS_3, WeekDay.FRIDAY, WeekDay.SATURDAY, WeekDay.SUNDAY);
        Collections.addAll(UPDATED_WEEKENDS_1, WeekDay.FRIDAY, WeekDay.SATURDAY);
    }

    /**
     * Object which represents existing entity in the data base.
     */
    public static final Department
            DEPARTMENT_1 =
            new Department(1, "ООО \"Рога и копыта\"", "Компания по производству субпродуктов.", WEEKENDS_1, null),
            DEPARTMENT_2 =
                    new Department(2, "Отдел кадров", "Управление персоналом.", WEEKENDS_2, null),
            DEPARTMENT_3 =
                    new Department(3, "Бухгалтерия", "Финансовые операции и отчетность", WEEKENDS_3, null);

    /**
     * DEPARTMENT_1 with an updated data.
     *
     * @see DepartmentTestData#DEPARTMENT_1
     */
    public static final Department UPDATED_DEPARTMENT_1 =
            new Department(1, "Обновленное название.", "Обновленный комментарий.", UPDATED_WEEKENDS_1, null);

    static {
        DEPARTMENT_1.setDeptSchedule(DEPT_SCHEDULE_1);
        DEPARTMENT_2.setDeptSchedule(DEPT_SCHEDULE_2);
        UPDATED_DEPARTMENT_1.setDeptSchedule(DEPT_SCHEDULE_1);
    }

    /**
     * New {@code Department} with id-null.
     */
    public static final Department NEW_DEPARTMENT =
            new Department("Новый департамент", "Департамент для тестов");

    /**
     * Returns a new {@code Department} from the test data.
     *
     * @return the new {@code Department} from the test data.
     */
    @Override
    public Department getNew() {
        return new Department(NEW_DEPARTMENT);
    }

    /**
     * Returns an updated {@code Department} from the test data.
     *
     * @return the updated {@code Department} from the test data.
     */
    @Override
    public Department getUpdated() {
        return new Department(UPDATED_DEPARTMENT_1);
    }

    /**
     * Returns an {@code Department} that is equals entity which will be get from DB.
     *
     * @return the {@code Department} that is equals entity which will be get from DB.
     */
    @Override
    public Department getGotten() {
        return new Department(DEPARTMENT_2);
    }

    /**
     * Returns an {@code Department}'s id for delete.
     *
     * @return the {@code Department}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return DEPARTMENT_2.getId();
    }

    /**
     * Returns the all {@code Departments} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code Department}.
     * @return the all {@code Departments} from test data with the specified created {@code Department}.
     */
    @Override
    public Department[] getCreatedArray(Department expectedCreatedObj) {
        return new Department[]{DEPARTMENT_3, expectedCreatedObj, DEPARTMENT_1, DEPARTMENT_2};
    }

    /**
     * Returns the all {@code Departments} from the test data without the deleted {@code Department}.
     *
     * @return the all {@code Departments} from the test data without the deleted {@code Department}.
     */
    @Override
    public Department[] getDeletedArray() {
        return new Department[]{DEPARTMENT_3, DEPARTMENT_1};
    }

    /**
     * Returns the all {@code Departments} from the test data.
     *
     * @return the all {@code Departments} from the test data.
     */
    @Override
    public Department[] getAllArray() {
        return new Department[]{DEPARTMENT_3, DEPARTMENT_1, DEPARTMENT_2};
    }

    /**
     * Constructs new {@code DepartmentTestData} and sets
     * {@code IGNORING_FIELDS} field
     * in the superclass.
     *
     * @see AbstractTestData#IGNORING_FIELDS
     */
    public DepartmentTestData() {
        super("weekEnds", "deptSchedule");
    }
}
