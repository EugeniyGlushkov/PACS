package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.PointAction;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

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
}
