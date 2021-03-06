package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.ActionType;
import ru.alvisid.pacs.model.PointPermit;

import java.util.List;

/**
 * The generalized functional for point's permit repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface PointPermitRepository extends TypicalRepository <PointPermit> {
    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param pointPermit   the object to save or update.
     * @param empId         the employee's id, the employee will be inserted to the
     *                      saved object's {@code employee} field.
     * @param pointActionId the point action's id, the point action will be inserted to the
     *                      saved object's {@code pointAction} field.
     * @return the saved or updated object, null - if there aren't updated object in the data base.
     */
    PointPermit save(PointPermit pointPermit, int empId, int pointActionId);

    /**
     * Returns the list with all point's permits by employee's id.
     *
     * @param empId the employee's id.
     * @return the list with all point's permits by employee's id.
     */
    List <PointPermit> getAllByEmpId(int empId);

    /**
     * Returns the list with all point's permits by control point's id.
     *
     * @param ctrlPointId the control point's id.
     * @return the list with all point's permits by control point's id.
     */
    List <PointPermit> getAllByControlPointId(int ctrlPointId);

    /**
     * Returns the point permit by employee's id, control point's id and action type.
     *
     * @param empId       the employee's id.
     * @param ctrlPointId the control point's id.
     * @param actionType  the action type.
     * @return the point permit by employee's id, control point's id and action type.
     */
    PointPermit getByEmpIdCtrlPointIdAndActType(int empId, int ctrlPointId, ActionType actionType);
}
