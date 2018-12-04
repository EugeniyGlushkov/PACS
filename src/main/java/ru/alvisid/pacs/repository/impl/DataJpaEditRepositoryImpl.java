package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Edit;
import ru.alvisid.pacs.repository.EditRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEditRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class DataJpaEditRepositoryImpl implements EditRepository {
    private static final Sort SORT_TIME_TYPE_EMPL = new Sort(Sort.Direction.ASC,
            "editDateTime", "editType", "employee");

    private static final Sort SORT_TIME_TYPE = new Sort(Sort.Direction.ASC,
            "editDateTime", "editType");

    private final CrudEditRepository crudRepository;

    @Autowired
    public DataJpaEditRepositoryImpl(CrudEditRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Edit save(Edit edit) {
        if (edit.isNew() || !Objects.isNull(get(edit.getId()))) {
            return crudRepository.save(edit);
        }

        return null;
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Edit get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List <Edit> getAll() {
        return crudRepository.findAll(SORT_TIME_TYPE_EMPL);
    }

    @Override
    public List <Edit> getAllByEmpId(int id) {
        return crudRepository.findAllByEmployeeId(id, SORT_TIME_TYPE);
    }

    @Override
    public List <Edit> getAllBBetween(LocalDateTime start, LocalDateTime end) {
        return crudRepository.findAllBetween(start, end, SORT_TIME_TYPE_EMPL);
    }
}
