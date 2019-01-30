package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.DepartmentChief;

/**
 * The generalized functional for department chief's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DepartmentChiefRepository extends TypicalRepository <DepartmentChief> {
    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param departmentChief the object to save or update.
     * @param deptId          the department's id, the department will be inserted to the
     *                        saved object's {@code department} field.
     * @param emplId          the employee's id, the employee will be inserted to the
     *                        saved object's {@code employee} field.
     * @returna saved or update object,
     * null - if there aren't updated object in the data base.
     */
    DepartmentChief save(DepartmentChief departmentChief, int deptId, int emplId);

    /**
     * Returns a department's chief by a department's id.
     *
     * @param deptId the specified department's id.
     * @return the department's chief by a department's id.
     */
    DepartmentChief getByDeptId(int deptId);
}
