package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.repository.PointActionRepository;
import ru.alvisid.pacs.repository.datajpa.CrudPointActionRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the PointActionRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaPointActionRepositoryImpl implements PointActionRepository {
    /**
     * Sort by control point's serial code and action's type.
     */
    private static final Sort SORT_CPOINT_ACTTYPE = new Sort(Sort.Direction.ASC,
            "controlPoint.serialCode",
            "actionType.type");

    /**
     * An interface for point's action which extends JpaRepository.
     */
    private final CrudPointActionRepository crudRepository;

    /**
     * Constructs a new DataJpaPointActionRepositoryImpl with the specified CrudPointActionRepository.
     *
     * @param crudRepository the specified CrudPointActionRepository.
     */
    @Autowired
    public DataJpaPointActionRepositoryImpl(CrudPointActionRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given point's action.
     * Returns null if there aren't updating value in the data base.
     *
     * @param pointAction a point's action to save.
     * @return the saved point's action.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public PointAction save(PointAction pointAction) {
        if (pointAction.isNew() || !Objects.isNull(get(pointAction.getId()))){
            return crudRepository.save(pointAction);
        }

        return null;
    }

    /**
     * Deletes a point's action by given id.
     *
     * @param id the specifiec id of the deleted point's action.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a point's action by given id.
     *
     * @param id the specifiec id of point's action to get.
     * @return a point's action by the given id,
     * null - if there aren't point's action with cpecifiec id in the DB.
     */
    @Override
    public PointAction get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all point's actions sorted with specified sort.
     * List is sorted by control point's serial code and action's type.
     *
     * @return list of all point's actions.
     */
    @Override
    public List <PointAction> getAll() {
        return crudRepository.findAll(SORT_CPOINT_ACTTYPE);
    }
}
