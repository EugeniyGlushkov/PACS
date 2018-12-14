package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.repository.impl.DataJpaDepartmentRepositoryImpl;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.DepartmentService;

/**
 * Implementation of the {@code DepartmentService} interface.
 * Extends <b>AbstractService</b>'s functionality.
 *
 * @see DepartmentService
 * @see AbstractService
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Service
public class DepartmentServiceImpl
extends AbstractService<DataJpaDepartmentRepositoryImpl, Department> implements DepartmentService {
    /**
     * Constructs new {@code DepartmentServiceImpl} and set a specified department's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified department's repository implementation.
     */
    @Autowired
    public DepartmentServiceImpl(DataJpaDepartmentRepositoryImpl repository) {
        super(repository);
    }
}
