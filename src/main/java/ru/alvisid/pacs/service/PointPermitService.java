package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

/**
 * The specific functional for the point permit's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface PointPermitService extends TypicalService <PointPermit> {
    /**
     * Creates and saves a given point permit in the data base
     * with inserted employee and point action.
     *
     * @param pointPermit   the point permit to create.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @return the created object.
     */
    PointPermit create(PointPermit pointPermit, int empId, int pointActionId);

    /**
     * Updates an existing in the data base point permit
     * with inserted employee and point action.
     *
     * @param pointPermit   the point permit to update.
     * @param empId         the employee's id to insert.
     * @param pointActionId point action's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(PointPermit pointPermit, int empId, int pointActionId) throws NotFoundException;

    /**
     * Returns the list with all point permits by employee's id.
     *
     * @param empId the specified employee's id.
     * @return the list with all point permits by employee's id.
     * @see PointPermitService#getAllByControlPointId(int)
     */
    List< PointPermit> getAllByEmpId(int empId);

    /**
     * Returns the list with all point permits by control point's id.
     *
     * @param ctrlPointId the specified control point's id.
     * @return the list with all point permits by control point's id.
     * @see PointPermitService#getAllByEmpId(int)
     */
    List <PointPermit> getAllByControlPointId(int ctrlPointId);

    /**
     * Returns the list of point permits by employee's id and control point's id.
     *
     * @param empId       the employee's id.
     * @param ctrlPointId the control point's id.
     * @return the list of point permits by employee's id and control point's id.
     */
    List <PointPermit> getAllByEmpIdAndCtrlPointId(int empId, int ctrlPointId);
}
