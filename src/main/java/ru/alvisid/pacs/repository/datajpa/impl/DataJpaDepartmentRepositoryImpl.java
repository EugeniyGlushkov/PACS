package ru.alvisid.pacs.repository.datajpa.impl;

import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.repository.DepartmentRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DataJpa implementation of the DepartmentRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaDepartmentRepositoryImpl implements DepartmentRepository {
    /**
     * Sort by name for department.
     */
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");
    /**
     * An interface for department which extends JpaRepository.
     */
    @Autowired
    CrudDepartmentRepository crudRepository;

    /**
     * Saves a given department.
     *
     * @param department a department to save.
     * @return the saved department.
     */
    @Override
    public Department save(Department department) {
        return crudRepository.save(department);
    }

    /**
     * Deletes a department by given id.
     *
     * @param id id of the department that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a department by given id.
     *
     * @param id id of the department to return.
     * @return a department by given id.
     */
    @Override
    public Department get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all departments sorted with specifiec sort.
     *
     * @return list of all departments.
     * @see DataJpaDepartmentRepositoryImpl#SORT_NAME
     */
    @Override
    public List<Department> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }
}
