package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

public interface EmployeeService extends TypicalService<Employee> {
    List<Employee> getAllByDeptId(int deptId);

    Employee getByEmail(String email) throws NotFoundException;
}
