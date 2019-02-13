package ru.alvisid.pacs.service.impl;

import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.DayOff;
import ru.alvisid.pacs.repository.DayOffRepository;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.DayOffService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code DayOffService} interface.
 * Extends <b>AbstractService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see DayOffService
 * @see AbstractService
 */
@Service
public class DayOffServiceImpl
        extends AbstractService <DayOffRepository, DayOff> implements DayOffService {
    /**
     * Creates and saves a given department's day off in the data base
     * with inserted department.
     *
     * @param dayOff the department's day off to create.
     * @param deptId the department's id to insert.
     * @return the created object.
     */
    @Override
    public DayOff create(DayOff dayOff, int deptId) {
        Assert.notNull(dayOff, currentClass.getSimpleName() + " must not be null");
        return repository.save(dayOff, deptId);
    }

    /**
     * Updates an existing in the data base department's day off
     * with inserted department.
     *
     * @param dayOff the department's day off to create.
     * @param deptId the department's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    public void update(DayOff dayOff, int deptId) throws NotFoundException {
        Assert.notNull(dayOff, currentClass.getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(dayOff, deptId), dayOff.getId());
    }

    /**
     * Returns the list with all objects by a specified <b>department's id</b>.
     *
     * @param deptId the specified <b>department's id</b>.
     * @return the list with all objects by a specified <b>department's id</b>.
     */
    @Override
    public List <DayOff> getAllByDeptId(int deptId) {
        return repository.getAllByDeptId(deptId);
    }

    /**
     * Returns the list with all objects by a specified <b>date</b>.
     *
     * @param date the specified <b>date</b>.
     * @return the list with all objects by a specified <b>date</b>.
     */
    @Override
    public List <DayOff> getAllByDate(LocalDate date) {
        Assert.notNull(date, LocalDate.class.getSimpleName() + " must not be null");
        return repository.getAllByDate(date);
    }

    /**
     * Constructs new {@code DayOffServiceImpl} and set a specified day's off repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified day's off repository implementation.
     */
    public DayOffServiceImpl(DayOffRepository repository) {
        super(repository);
    }
}
