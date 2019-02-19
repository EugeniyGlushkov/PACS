package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.ActionType;
import ru.alvisid.pacs.model.PointPermit;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for point permit.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudPointPermitRepository extends JpaRepository <PointPermit, Integer> {
    /**
     * Saves or updates a given point permit.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param pointPermit a point permit to save.
     * @return the saved point permit.
     */
    @Transactional
    @Override
    PointPermit save(PointPermit pointPermit);

    /**
     * Deletes a point permit by given id.
     *
     * @param id id of the point permit that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PointPermit pp WHERE pp.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with a point permit by given id inside.
     *
     * @param integer id of the point permit to return.
     * @return a container with a point permit by given id inside.
     */
    @Override
    Optional <PointPermit> findById(Integer integer);

    /**
     * Returns all point permits sorted with a given sort.
     *
     * @param sort sort for point permits list.
     * @return list of all point permits.
     */
    @Override
    List <PointPermit> findAll(Sort sort);

    /**
     * Returns the list with all point permits by employee's id and sorted by
     * control point's serial code and action's type of the point action.
     *
     * @param empId the employee's id.
     * @param sort  the specified sort.
     * @return the sorted list with all point permits by employee's id.
     */
    @Query("SELECT pp FROM PointPermit pp WHERE pp.employee.id=:empId")
    List <PointPermit> findAllByEmployeeId(@Param("empId") int empId, Sort sort);

    /**
     * Returns the list with all point permits by the control point's id of the point permit
     * and sorted by specified sort.
     *
     * @param ctrlPointId the department's id.
     * @param sort        the specified list's sort.
     * @return the list with all point permits by the control point's id of the point permit
     * and sorted by specified sort.
     */
    @Query("SELECT pp FROM PointPermit pp WHERE pp.pointAction.controlPoint.id=:ctrlPointId")
    List <PointPermit> findAllByControlPointId(@Param("ctrlPointId") int ctrlPointId, Sort sort);

    /**
     * Returns the point permit by employee's id, control point's id and action type.
     *
     * @param empId       the employee's id.
     * @param ctrlPointId the control point's id.
     * @param actionType  the action type.
     * @return the point permit by employee's id, control point's id and action type.
     */
    @Query("SELECT pp FROM PointPermit pp WHERE pp.employee.id=?1 AND pp.pointAction.controlPoint.id=?2 AND pp.pointAction.actionType=?3")
    PointPermit getByEmpIdCtrlPointIdAndActType(int empId, int ctrlPointId, ActionType actionType);
}
