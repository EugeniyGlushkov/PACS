package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.ControlPoint;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

/**
 * The specific functional for the control point's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface ControlPointService extends TypicalService <ControlPoint> {
    /**
     * Returns a control point by the serial code.
     *
     * @param serialCode the specified serial code.
     * @return the control point by the given serial code.
     * @throws NotFoundException if the control point with the specified serial code isn't found.
     */
    ControlPoint getBySerialCode(String serialCode) throws NotFoundException;
}
