package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.repository.PointActionRepository;
import ru.alvisid.pacs.repository.datajpa.CrudControlPointRepository;
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
            "controlPoint.id",
            "actionType");

    /**
     * An interface for point's action repository which extends JpaRepository.
     */
    private final CrudPointActionRepository crudRepository;

    /**
     * An interface for control point's repository which extends JpaRepository.
     */
    private final CrudControlPointRepository crudControlPointRepository;

    /**
     * Constructs a new DataJpaPointActionRepositoryImpl with the specified CrudPointActionRepository,
     * CrudControlPointRepository and CrudActionTypeRepository.
     *
     * @param crudRepository             the specified <em>CrudPointActionRepository</em>.
     * @param crudControlPointRepository the specified <em>CrudControlPointRepository</em>.
     */
    @Autowired
    public DataJpaPointActionRepositoryImpl(CrudPointActionRepository crudRepository,
                                            CrudControlPointRepository crudControlPointRepository) {
        this.crudRepository = crudRepository;
        this.crudControlPointRepository = crudControlPointRepository;
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
        if (pointAction.isNew() || !Objects.isNull(get(pointAction.getId()))) {
            return crudRepository.save(pointAction);
        }

        return null;
    }

    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param pointAction    the object to save or update.
     * @param controlPointId the control point's id, the control point will be inserted to the
     *                       saved object's {@code controlPoint} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public PointAction save(PointAction pointAction, int controlPointId) {
        pointAction.setControlPoint(crudControlPointRepository.getOne(controlPointId));
        return save(pointAction);
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
    public List<PointAction> getAll() {
        return crudRepository.findAll(SORT_CPOINT_ACTTYPE);
    }

    /**
     * Returns the list with all point actions by control point's id.
     *
     * @param ctrlPointId the specified control point's id.
     * @return the list with all point actions by control point's id.
     */
    @Override
    public List <PointAction> getAllByControlPointId(int ctrlPointId) {
        return crudRepository.getAllByControlPointId(ctrlPointId);
    }

    /**
     * Returns a point action with filled fields {@code controlPoint}
     * by specified id.
     *
     * @param id the specified point action's id.
     * @return the point action with filled fields {@code controlPoint}.
     */
    @Override
    public PointAction getWithCtrlPoint(int id) {
        return crudRepository.getWithCtrlPoint(id);
    }
}
