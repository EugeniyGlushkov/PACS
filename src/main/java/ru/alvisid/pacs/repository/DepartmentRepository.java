package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Department;

import java.util.List;

/**
 * The generalized functional for department's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DepartmentRepository {
    /**
     * Saves or updates a given department.
     *
     * @param department the department to save or update.
     * @return a saved or update department,
     * null - if there aren't updated department in the repository.
     */
    Department save(Department department);

    /**
     * Deletes the department with specifiec id.
     *
     * @param id the specifiec id of a deleted department.
     * @return true - if a department with the specifiec id is deleted,
     * false - if there aren't the department with the cpecifiec id in the DB.
     */
    boolean delete(int id);

    /**
     * Returns a department with the cpecifiec id.
     *
     * @param id the specifiec id of department to get.
     * @return a department with the cpecifiec id,
     * null - if there aren't department with cpecifiec id in the DB.
     */
    Department get(int id);

    /**
     * Returns the list with all departments.
     *
     * @return the list with all departments.
     */
    List<Department> getAll();
}
