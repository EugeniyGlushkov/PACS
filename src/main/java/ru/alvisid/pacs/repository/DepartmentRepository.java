package ru.alvisid.pacs.repository;


import org.acs.domain.model.Department;

import java.util.List;

/**
 * The interface with a generalized functional for departmet's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DepartmentRepository {
    /**
     * Saves or updates a given department.
     *
     * @param department department to save or update.
     * @return a saved or update department,
     * null - if there aren't updated department in the repository.
     */
    Department save(Department department);

    /**
     * Deletes the department with specifiec id.
     *
     * @param id the specifiec id of a deleted department.
     * @return true - if a department with the specifiec id are deleted,
     * false - if there aren't the department with the cpecifiec id in the DB.
     */
    boolean delete(long id);

    /**
     * Returnes a department with the cpecifiec id.
     *
     * @param id the specifiec id of department to get.
     * @return a department with the cpecifiec id,
     * null - if there aren't department with cpecifiec id in the DB.
     */
    Department get(long id);

    /**
     * Returnes list with all departments.
     *
     * @return list with all departments.
     */
    List<Department> getAll();
}
