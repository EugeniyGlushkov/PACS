package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Absence;
import ru.alvisid.pacs.repository.AbsenceRepository;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.AbsenceService;
import org.springframework.util.Assert;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code AbsenceService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbsenceService
 * @see AbstractService
 */
@Service
public class AbsenceServiceImpl
        extends AbstractService<AbsenceRepository, Absence> implements AbsenceService {
    /**
     * Creates and saves a given absence in the data base
     * with inserted employee and absence reason.
     *
     * @param absence         the absence to create.
     * @param empId           the employee's id to insert.
     * @param absenceReasonId the absence reason's id to insert.
     * @return the created object.
     */
    @Override
    public Absence create(Absence absence, int empId, int absenceReasonId) {
        Assert.notNull(absence, absence.getClass().getSimpleName() + " must not be null");
        return repository.save(absence, empId, absenceReasonId);
    }

    /**
     * Updates an existing in the data base absence
     * with inserted employee and absence reason.
     *
     * @param absence         the absence to update.
     * @param empId           the employee's id to insert.
     * @param absenceReasonId the absence reason's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    public void update(Absence absence, int empId, int absenceReasonId) throws NotFoundException {
        Assert.notNull(absence, absence.getClass().getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(absence, empId, absenceReasonId), absence.getId());
    }

    /**
     * Returns the list with all absences by employee's id.
     *
     * @param id the employee's id.
     * @return the list with all absences by employee's id.
     * @see AbsenceServiceImpl#getAllByEmplDeptId(int)
     */
    @Override
    public List<Absence> getAllByEmplId(int id) {
        return repository.getAllByEmplId(id);
    }

    /**
     * Returns the list with all absences by id of the employee's department.
     *
     * @param id the department's id.
     * @return the list with all absences by id of the employee's department.
     * @see AbsenceServiceImpl#getAllByEmplId(int)
     */
    @Override
    public List<Absence> getAllByEmplDeptId(int id) {
        return repository.getAllByEmplDeptId(id);
    }

    /**
     * Constructs new {@code AbsenceServiceImpl} and set a specified absence's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified absence's repository implementation.
     */
    @Autowired
    public AbsenceServiceImpl(AbsenceRepository repository) {
        super(repository);
    }
}
