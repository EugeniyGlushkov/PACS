package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.PointAction;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for point's action.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudPointActionRepository extends JpaRepository <PointAction, Integer> {
    /**
     * Saves or updates a given point's action.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param pointAction an point's action to save.
     * @return the saved point's action.
     */
    @Transactional
    @Override
    PointAction save(PointAction pointAction);

    /**
     * Deletes a point's action by given id.
     *
     * @param id id of the point's action that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PointAction pa WHERE pa.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with a point's action by given id inside.
     *
     * @param integer id of the point's action to return.
     * @return a container with a point's action by given id inside.
     */
    @Override
    Optional <PointAction> findById(Integer integer);

    /**
     * Returns all point's actions sorted with a given sort.
     *
     * @param sort sort for point's actions list.
     * @return list of all point's actions.
     */
    @Override
    List <PointAction> findAll(Sort sort);

    /**
     * Returns the list with all point actions by control point's id.
     *
     * @param ctrlPointId the specified control point's id.
     * @return the list with all point actions by control point's id.
     */
    @Query("SELECT pa FROM PointAction pa WHERE pa.controlPoint.id=:ctrlPointId")
    List<PointAction> getAllByControlPointId(@Param("ctrlPointId") int ctrlPointId);


    /**
     * Returns a point action with filled fields: {@code controlPoint} and {@code actionType}
     * by specified id.
     *
     * @param id the specified point action's id.
     * @return the point action with filled fields: {@code controlPoint} and {@code actionType}.
     */
    @EntityGraph(attributePaths = {"controlPoint", "actionType"})
    @Query("SELECT pa FROM PointAction pa WHERE pa.id=?1")
    PointAction getWithCtrlPointAndActionType(int id);
}
