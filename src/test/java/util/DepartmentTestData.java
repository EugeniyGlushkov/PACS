package util;

import ru.alvisid.pacs.model.Department;

public class DepartmentTestData {
    public static final String[] IGNORING_FIELDS = {"weekEnds", "deptSchedule"};

    public static final Department
            DEPARTMENT_1 = new Department(1, "Компания по производству субпродуктов.", "ООО \"Рога и копыта\""),
            DEPARTMENT_2 = new Department(2, "Управление персоналом.", "Отдел кадров"),
            DEPARTMENT_3 = new Department(3, "Финансовые операции и отчетность", "Бухгалтерия");
}
