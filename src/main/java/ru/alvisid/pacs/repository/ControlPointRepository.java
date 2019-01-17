package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.ControlPoint;

/**
 * The generalized functional for control point repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface ControlPointRepository extends TypicalRepository <ControlPoint> {
    /**
     * Returns a control point by given serial code.
     *
     * @param serialCode the specified serial code.
     * @return the control point by given serial code.
     */
    ControlPoint getBySerialCode(String serialCode);
}
