package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.AbsenceReason;
import ru.alvisid.pacs.repository.impl.DataJpaAbsenceReasonRepositoryImpl;
import ru.alvisid.pacs.service.AbsenceReasonService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;


@Service
public class AbsenceReasonServiceImpl
        extends AbstractService<DataJpaAbsenceReasonRepositoryImpl, AbsenceReason> implements AbsenceReasonService {

    @Autowired
    public AbsenceReasonServiceImpl(DataJpaAbsenceReasonRepositoryImpl absenceReasonRepository) {
        super(absenceReasonRepository);
    }

    @Override
    public AbsenceReason create(AbsenceReason absenceReason) {
        return null;
    }

    @Override
    public void update(AbsenceReason absenceReason) throws NotFoundException {

    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public AbsenceReason get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<AbsenceReason> getAll() {
        return null;
    }
}
