package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.ControlPoint;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for control point.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudControlPointRepository extends JpaRepository <ControlPoint, Integer> {
    /**
     * Saves or updates a given control point.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param controlPoint an absence to save.
     * @return the saved control point.
     */
    @Transactional
    @Override
    ControlPoint save(ControlPoint controlPoint);

    /**
     * Deletes a control point by given id.
     *
     * @param id id of the control point that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM ControlPoint cp WHERE cp.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with a control point by given id inside.
     *
     * @param integer id of the control point to return.
     * @return a container with control point by given id inside.
     */
    @Override
    Optional <ControlPoint> findById(Integer integer);

    /**
     * Returns all control points sorted with a given sort.
     *
     * @param sort sort for control points list.
     * @return list of all control points.
     */
    @Override
    List <ControlPoint> findAll(Sort sort);

    /**
     * Returns a control point by the serial code.
     *
     * @param serialCode the specified serial code.
     * @return the control point by the given serial code.
     */
    ControlPoint findBySerialCode(String serialCode);
}
