package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.PointAction;

/**
 * The generalized functional for point action's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface PointActionRepository extends TypicalRepository<PointAction> {
    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param pointAction    the object to save or update.
     * @param controlPointId the control point's id, the control point will be inserted to the
     *                       saved object's {@code controlPoint} field.
     * @param actionTypeId   the control point's id, the control point will be inserted to the
     *                       saved object's {@code controlPoint} field.
     * @return
     */
    PointAction save(PointAction pointAction, int controlPointId, int actionTypeId);
}
