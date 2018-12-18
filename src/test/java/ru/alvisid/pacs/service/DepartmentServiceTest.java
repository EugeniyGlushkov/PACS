package ru.alvisid.pacs.service;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.alvisid.pacs.model.Department;
import util.DepartmentTestData;

import static util.DepartmentTestData.*;

public class DepartmentServiceTest extends AbstractServiceTest<Department, DepartmentService, DepartmentTestData> {
    public DepartmentServiceTest() {
        super(new DepartmentTestData());
    }

    @Override
    @Autowired
    public void setService(DepartmentService service) {
        super.service = service;
    }

    @Test
    public void createDuplicateName(){
        thrown.expect(DataAccessException.class);
        Department newDepartment = testData.getNew();
        newDepartment.setName(DEPARTMENT_2.getName());
        service.create(newDepartment);
    }

    @Test
    public void updateDuplicateName() {
        thrown.expect(DataAccessException.class);
        Department updated = testData.getUpdated();
        updated.setName(DEPARTMENT_2.getName());
        service.update(updated);
    }
}
