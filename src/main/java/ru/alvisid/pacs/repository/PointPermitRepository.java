package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.PointPermit;

import java.util.List;

/**
 * The generalized functional for point's permit repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface PointPermitRepository extends TypicalRepository<PointPermit> {
    /**
     * Returns the list with all point's permits by employee's id.
     *
     * @param empId the employee's id.
     * @return the list with all point's permits by employee's id.
     */
    List<PointPermit> getAllByEmpId(int empId);

    /**
     * Returns the list with all point's permits by control point's id.
     *
     * @param cPointId the control point's id.
     * @return the list with all point's permits by control point's id.
     */
    List<PointPermit> getAllByControlPointId(int cPointId);
}
