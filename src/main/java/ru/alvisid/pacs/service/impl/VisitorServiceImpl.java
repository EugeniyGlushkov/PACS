package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Visitor;
import ru.alvisid.pacs.repository.impl.DataJpaVisitorRepositoryImpl;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.VisitorService;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the {@code DepartmentService} interface.
 * Extends <b>AbstractService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see VisitorService
 * @see AbstractService
 */
@Service
public class VisitorServiceImpl
        extends AbstractService <DataJpaVisitorRepositoryImpl, Visitor> implements VisitorService {
    /**
     * Returns a visitors list which contains visitors with specified visit's date.
     *
     * @param localDate the visit's date.
     * @return the visitors list which contains visitors with specified visit's date.
     */
    @Override
    public List <Visitor> getAllByVisitDate(LocalDate localDate) {
        return null;
    }

    /**
     * Constructs new {@code VisitorServiceImpl} and set a specified visitor's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified visitor's repository implementation.
     */
    @Autowired
    public VisitorServiceImpl(DataJpaVisitorRepositoryImpl repository) {
        super(repository);
    }
}
