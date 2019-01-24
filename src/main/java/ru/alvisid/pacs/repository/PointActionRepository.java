package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.PointAction;

import java.util.List;

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
     *                       saved object's {@code controlPoint} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    PointAction save(PointAction pointAction, int controlPointId);

    /**
     * Returns the list with all point actions by control point's id.
     *
     * @param ctrlPointId the specified control point's id.
     * @return the list with all point actions by control point's id.
     */
    List<PointAction> getAllByControlPointId(int ctrlPointId);

    /**
     * Returns a point action with filled field: {@code controlPoint}
     * by specified id.
     *
     * @param id the specified point action's id.
     * @return the point action with filled field: {@code controlPoint}.
     */
    PointAction getWithCtrlPoint(int id);
}
