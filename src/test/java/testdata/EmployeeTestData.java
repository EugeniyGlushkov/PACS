package testdata;

import ru.alvisid.pacs.model.Employee;

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
            EMPLOYEE_1 = new Employee(10006, "Иванов", "Иван", "Иванович", DEPARTMENT_1, POSITION_1, 333222, "ivanov@mail.ru"),
            EMPLOYEE_2 = new Employee(10007, "Тищенко", "Лидия", "Петровна", DEPARTMENT_2, POSITION_2, 225485, "tlpet@gmail.com"),
            EMPLOYEE_3 = new Employee(10008, "Иванков", "Пётр", "Ильич", DEPARTMENT_3, POSITION_3, 256432, "ivpil@list.ru"),
            EMPLOYEE_4 = new Employee(10009, "Сидоркина", "Анна", "Андреевна", DEPARTMENT_2, POSITION_4, 965677, "secret@list.ru"),
            EMPLOYEE_5 = new Employee(10010, "Батурина", "Ольга", "Игоревна", DEPARTMENT_3, POSITION_5, 124344, "buhg@list.ru"),
            EMPLOYEE_6 = new Employee(10011, "Балабанов", "Евгений", "Олегович", DEPARTMENT_2, POSITION_6, 555433, "admin@bk.ru");

    @Override
    public Employee getNew() {
        return null;
    }

    @Override
    public Employee getUpdated() {
        return null;
    }

    @Override
    public Employee getGotten() {
        return null;
    }

    @Override
    public int getDeletedId() {
        return 0;
    }

    @Override
    public Employee[] getCreatedArray(Employee expectedCreatedObj) {
        return new Employee[0];
    }

    @Override
    public Employee[] getDeletedArray() {
        return new Employee[0];
    }

    @Override
    public Employee[] getAllArray() {
        return new Employee[0];
    }

    public EmployeeTestData() {
        super("department", "position");
    }
}
