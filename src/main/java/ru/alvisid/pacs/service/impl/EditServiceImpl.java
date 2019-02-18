package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Edit;
import ru.alvisid.pacs.repository.EditRepository;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.EditService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code EditService} interface.
 * Extends <b>AbstractService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see EditService
 * @see AbstractService
 */
@Service
public class EditServiceImpl
        extends AbstractService<EditRepository, Edit> implements EditService {

    /**
     * Creates and saves a given edit in the data base
     * with inserted employee.
     *
     * @param edit  the edit to create.
     * @param empId the employee's id to insert.
     * @return the created object.
     */
    @Override
    public Edit create(Edit edit, int empId) {
        Assert.notNull(edit, currentClass.getSimpleName() + " must not be null");
        return repository.save(edit, empId);
    }

    /**
     * Updates an existing in the data base edit
     * with inserted employee.
     *
     * @param edit  the edit to create.
     * @param empId the employee's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    public void update(Edit edit, int empId) throws NotFoundException {
        Assert.notNull(edit, currentClass.getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(edit, empId), edit.getId());
    }

    /**
     * Returns all edits which are done by specific employee.
     * List is sorted by edit's time and edit's type.
     *
     * @param id the employee's id.
     * @return all edits which are done by specific employee.
     */
    @Override
    public List<Edit> getAllByEmpId(int id) {
        return repository.getAllByEmpId(id);
    }

    /**
     * Returns all edits in the specified time interval.
     * List is sorted by edit's time, edit's type, employee's last name, first name and second name.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     */
    @Override
    public List<Edit> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return repository.getAllBetween(start, end);
    }

    /**
     * Constructs new {@code EditServiceImpl} and set a specified edit's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified point action's repository implementation.
     */
    @Autowired
    public EditServiceImpl(EditRepository repository) {
        super(repository);
    }


}
