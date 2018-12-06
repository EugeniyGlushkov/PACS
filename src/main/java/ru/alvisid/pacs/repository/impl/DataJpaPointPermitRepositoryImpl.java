package ru.alvisid.pacs.repository.impl;

import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.repository.PointPermitRepository;

import java.util.List;
@Repository
public class DataJpaPointPermitRepositoryImpl implements PointPermitRepository {
    @Override
    public PointPermit save(PointPermit pointPermit) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public PointPermit get(int id) {
        return null;
    }

    @Override
    public List<PointPermit> getAll() {
        return null;
    }

    @Override
    public List<PointPermit> getAllByEmpId(int id) {
        return null;
    }

    @Override
    public List<PointPermit> getAllByControlPointId(int id) {
        return null;
    }
}
