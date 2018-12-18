package util;

import ru.alvisid.pacs.model.Department;

public class DepartmentTestData extends AbstractTestData <Department> {

    public DepartmentTestData() {
        super(100000, new String[]{"weekEnds", "deptSchedule"});
    }

    public static final Department
            DEPARTMENT_1 = new Department(1, "ООО \"Рога и копыта\"", "Компания по производству субпродуктов."),
            DEPARTMENT_2 = new Department(2, "Отдел кадров", "Управление персоналом."),
            DEPARTMENT_3 = new Department(3, "Бухгалтерия", "Финансовые операции и отчетность"),
            NEW_DEPARTMENT = new Department(null, "Новый департамент", "Департамент для тестов"),
            UPDATED_DEPARTMENT_1 = new Department(1, "Обновленное название.", "Обновленный комментарий.");

    @Override
    public Department getNew() {
        return new Department(NEW_DEPARTMENT);
    }

    @Override
    public Department getUpdated() {
        return new Department(UPDATED_DEPARTMENT_1);
    }

    @Override
    public int getDeletedId() {
        return DEPARTMENT_2.getId();
    }

    @Override
    public Department[] getCreatedArray(Department expectedObj) {
        return new Department[]{DEPARTMENT_3, expectedObj, DEPARTMENT_1, DEPARTMENT_2};
    }

    @Override
    public Department[] getDeletedArray() {
        return new Department[]{DEPARTMENT_3, DEPARTMENT_1};
    }

    @Override
    public Department getGetted() {
        return new Department(DEPARTMENT_2);
    }

    @Override
    public Department[] getAllArray() {
        return new Department[]{DEPARTMENT_3, DEPARTMENT_1, DEPARTMENT_2};
    }
}
