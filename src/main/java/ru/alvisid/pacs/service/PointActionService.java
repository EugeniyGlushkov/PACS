package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

/**
 * The specific functional for the point action's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface PointActionService extends TypicalService <PointAction> {
    /**
     * Creates and saves a given point action in the data base
     * with inserted control point.
     *
     * @param pointAction    the point action to create.
     * @param controlPointId the control point's id to insert.
     * @return the created object.
     */
    PointAction create(PointAction pointAction, int controlPointId);

    /**
     * Updates an existing in the data base point action
     * with inserted control point.
     *
     * @param pointAction    the point action to update.
     * @param controlPointId the control point's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(PointAction pointAction, int controlPointId) throws NotFoundException;


    /**
     * Returns the list with all point actions by control point's id.
     *
     * @param ctrlPointId the specified control point's id.
     * @return the list with all point actions by control point's id.
     */
    List <PointAction> getAllByControlPointId(int ctrlPointId);

    /**
     * Returns a point action with filled fields: {@code controlPoint} and {@code actionType}
     * by specified id.
     *
     * @param id the specified point action's id.
     * @return the point action with filled fields: {@code controlPoint} and {@code actionType}.
     * @throws NotFoundException if the entity with the specified id isn't found.
     */
    PointAction getWithCtrlPointAndActionType(int id) throws NotFoundException;
}
