package testdata;

import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.Role;

import java.util.Set;

import static testdata.DepartmentTestData.*;
import static testdata.PositionTestData.*;

/**
 * Test data for {@code Employee} class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class EmployeeTestData extends AbstractTestData <Employee> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final Employee
            EMPLOYEE_1 = new Employee(10006, "Иванов", "Иван", "Иванович",
            DEPARTMENT_1, POSITION_1, 333222, "ivanov@mail.ru",
            Set.of(Role.ROLE_USER, Role.ALL_READ)),
            EMPLOYEE_2 = new Employee(10007, "Тищенко", "Лидия", "Петровна",
                    DEPARTMENT_2, POSITION_2, 225485, "tlpet@gmail.com",
                    Set.of(Role.ROLE_DEPSREAD, Role.ROLE_EMPSWRIGHT, Role.ROLE_USER)),
            EMPLOYEE_3 = new Employee(10008, "Иванков", "Пётр", "Ильич",
                    DEPARTMENT_3, POSITION_3, 256432, "ivpil@list.ru",
                    Set.of(Role.ROLE_DEPSREAD, Role.ROLE_USER)),
            EMPLOYEE_4 = new Employee(10009, "Сидоркина", "Анна", "Андреевна",
                    DEPARTMENT_2, POSITION_4, 965677, "secret@list.ru",
                    Set.of(Role.ROLE_USER)),
            EMPLOYEE_5 = new Employee(10010, "Батурина", "Ольга", "Игоревна",
                    DEPARTMENT_3, POSITION_5, 124344, "buhg@list.ru",
                    Set.of(Role.ROLE_USER)),
            EMPLOYEE_6 = new Employee(10011, "Балабанов", "Евгений", "Олегович",
                    DEPARTMENT_2, POSITION_6, 555433, "admin@bk.ru",
                    Set.of(Role.ROLE_USER, Role.ROLE_ADMIN));

    /**
     * New {@code Employee} with id-null.
     */
    public static final Employee
            NEW_EMPLOYEE = new Employee("Горошина", "Елена", "Эдуардовна",
            DEPARTMENT_3, POSITION_5, 124324, "buh_1@list.ru",
            Set.of(Role.ROLE_USER));

    /**
     * EMPLOYEE_4 with an updated data.
     *
     * @see EmployeeTestData#EMPLOYEE_4
     */
    public static final Employee
            UPDATED_EMPLOYEE_4 = new Employee(10009, "Сидоркина", "Анна", "Андреевна",
            DEPARTMENT_3, POSITION_5, 225533, "buh-sid@mail.ru",
            Set.of(Role.ROLE_USER));

    /**
     * Returns a new {@code Employee} from the test data.
     *
     * @return the new {@code Employee} from the test data.
     */
    @Override
    public Employee getNew() {
        return new Employee(NEW_EMPLOYEE);
    }

    /**
     * Returns an updated {@code Employee} from the test data.
     *
     * @return the updated {@code Employee} from the test data.
     */
    @Override
    public Employee getUpdated() {
        return new Employee(UPDATED_EMPLOYEE_4);
    }

    /**
     * Returns an {@code Employee} that is equals entity which will be get from DB.
     *
     * @return the {@code Employee} that is equals entity which will be get from DB.
     */
    @Override
    public Employee getGotten() {
        return new Employee(EMPLOYEE_2);
    }

    /**
     * Returns an {@code Employee}'s id for delete.
     *
     * @return the {@code Employee}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return EMPLOYEE_5.getId();
    }

    /**
     * Returns the all {@code Employee} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created object.
     * @return the all {@code Employee} from test data with the specified created {@code Employee}.
     */
    @Override
    public Employee[] getCreatedArray(Employee expectedCreatedObj) {
        return new Employee[]{
                EMPLOYEE_1,
                EMPLOYEE_6,
                EMPLOYEE_4,
                EMPLOYEE_2,
                EMPLOYEE_5,
                expectedCreatedObj,
                EMPLOYEE_3,
        };
    }

    /**
     * Returns the all {@code Employee} from the test data without the deleted {@code Employee}.
     *
     * @return the all {@code Employee} from the test data without the deleted {@code Employee}.
     */
    @Override
    public Employee[] getDeletedArray() {
        return new Employee[]{
                EMPLOYEE_1,
                EMPLOYEE_6,
                EMPLOYEE_4,
                EMPLOYEE_2,
                EMPLOYEE_3,
        };
    }

    /**
     * Returns the all {@code Employee} from the test data.
     *
     * @return the all {@code Employee} from the test data.
     */
    @Override
    public Employee[] getAllArray() {
        return new Employee[]{
                EMPLOYEE_1,
                EMPLOYEE_6,
                EMPLOYEE_4,
                EMPLOYEE_2,
                EMPLOYEE_5,
                EMPLOYEE_3,
        };
    }

    /**
     * Constructs new {@code EmployeeTestData} and sets
     * {@code IGNORING_FIELDS} field
     * in the superclass.
     *
     * @see AbstractTestData#IGNORING_FIELDS
     */
    public EmployeeTestData() {
        super("department", "position");
    }
}
