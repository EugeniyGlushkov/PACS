package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.ControlPoint;
import ru.alvisid.pacs.repository.ControlPointRepository;
import ru.alvisid.pacs.repository.datajpa.CrudControlPointRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the ControlPointRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaControlPointRepositoryImpl implements ControlPointRepository {
    /**
     * Sort by control point's serial code.
     */
    private static final Sort SORT_CODE = new Sort(Sort.Direction.ASC, "serialCode");

    /**
     * An interface for control point which extends JpaRepository.
     */
    private final CrudControlPointRepository crudRepository;

    /**
     * Constructs a new DataJpaControlPointRepositoryImpl with the specified CrudControlPointRepository.
     *
     * @param crudRepository the specified CrudControlPointRepository.
     */
    @Autowired
    public DataJpaControlPointRepositoryImpl(CrudControlPointRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given control point.
     * Returns null if there aren't updating value in the data base.
     *
     * @param controlPoint a control point to save.
     * @return the saved control point.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public ControlPoint save(ControlPoint controlPoint) {
        if (controlPoint.isNew() || !Objects.isNull(get(controlPoint.getId()))){
            return crudRepository.save(controlPoint);
        }

        return null;
    }

    /**
     * Deletes a control point by given id.
     *
     * @param id the specifiec id of the deleted control point.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a control point by given id.
     *
     * @param id the specifiec id of control point to get.
     * @return an control point by the given id,
     * null - if there aren't control point with cpecifiec id in the DB.
     */
    @Override
    public ControlPoint get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all control points sorted with specified sort.
     * List is sorted by control point's serial code.
     *
     * @return list of all control points.
     */
    @Override
    public List<ControlPoint> getAll() {
        return crudRepository.findAll(SORT_CODE);
    }
}
