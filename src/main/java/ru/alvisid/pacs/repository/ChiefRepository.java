package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Chief;
import ru.alvisid.pacs.model.Employee;

/**
 * The generalized functional for the repository of the employee's chief.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface ChiefRepository extends TypicalRepository <Chief> {
    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param chief   the object to save or update.
     * @param empId   the employee's id, the employee will be inserted to the
     *                saved object's {@code employee} field.
     * @param chiefId the chief's id, the chief will be inserted to the
     *                saved object's {@code chief} field.
     * @returna saved or update object,
     * null - if there aren't updated object in the data base.
     */
    Chief save(Chief chief, int empId, int chiefId);

    /**
     * Returns a chief by an employee's id.
     *
     * @param empId the specified employee's id.
     * @return the employee's chief by an employee's id.
     */
    Chief getByEmployeeId(int empId);
}
