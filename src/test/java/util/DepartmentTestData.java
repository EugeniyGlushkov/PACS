package util;

import ru.alvisid.pacs.model.Department;

public class DepartmentTestData {
    public static final String[] IGNORING_FIELDS = {"weekEnds", "deptSchedule"};

    public static final Department
            DEPARTMENT_1 = new Department(1, "ООО \"Рога и копыта\"", "Компания по производству субпродуктов."),
            DEPARTMENT_2 = new Department(2, "Отдел кадров", "Управление персоналом."),
            DEPARTMENT_3 = new Department(3, "Бухгалтерия", "Финансовые операции и отчетность");

    public static Department getUpdated() {
        return new Department(1, "Обновленное название.", "Обновленный комментарий.");
    }
}
