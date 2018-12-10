package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.repository.impl.DataJpaEmployeeRepositoryImpl;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.EmployeeService;
import ru.alvisid.pacs.service.TypicalService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;
import static ru.alvisid.pacs.util.ValidationUtil.checkNotFound;

@Service
public class EmployeeServiceImpl
        extends AbstractService<DataJpaEmployeeRepositoryImpl, Employee> implements EmployeeService{

    @Autowired
    public EmployeeServiceImpl(DataJpaEmployeeRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public List<Employee> getAllByDeptId(int deptId) {
        return repository.getAllByDeptId(deptId);
    }

    @Override
    public Employee getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "Not found entity with email=" + email);
    }
}
