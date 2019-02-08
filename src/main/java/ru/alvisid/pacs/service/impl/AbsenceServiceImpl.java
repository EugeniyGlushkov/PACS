package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Absence;
import ru.alvisid.pacs.repository.AbsenceRepository;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.AbsenceService;

import java.util.List;

/**
 * Implementation of the {@code PointPermitService} interface.
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
    @Override
    public Absence create(Absence absence, int empId, int absenceReasonId) {
        return null;
    }

    @Override
    public void update(Absence absence, int empId, int absenceReasonId) {

    }

    @Override
    public List <Absence> getAllByEmplId(int id) {
        return null;
    }

    @Override
    public List <Absence> getAllByEmplDeptId(int id) {
        return null;
    }

    @Autowired
    public AbsenceServiceImpl(AbsenceRepository repository) {
        super(repository);
    }
}
