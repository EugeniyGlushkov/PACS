package ru.alvisid.pacs.repository.impl;

import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.repository.datajpa.CrudDepartmentRepository;

import java.util.List;
import java.util.Objects;

import static ru.alvisid.pacs.util.ValidationUtil.*;

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
     * An interface for department repository which extends JpaRepository.
     */
    private final CrudDepartmentRepository crudRepository;

    /**
     * Constructs a new DataJpaDepartmentRepositoryImpl with the specified CrudDepartmentRepository.
     *
     * @param crudRepository the specified CrudDepartmentRepository.
     */
    @Autowired
    public DataJpaDepartmentRepositoryImpl(CrudDepartmentRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given department.
     * Returns null if there aren't updating value in the data base.
     *
     * @param department a department to save.
     * @return the saved department.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Department save(Department department) {
        if (department.isNew() || !Objects.isNull(get(department.getId()))) {
            return crudRepository.save(department);
        }

        return null;
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
     * @return a department by given id,
     * null - if there aren't department with cpecifiec id in the DB.
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
    public List <Department> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    /**
     * Returns a department with filled fields: {@code weekEnds} and {@code deptSchedule}
     * by specified id.
     *
     * @param id the specified department's id.
     * @return the department with filled fields: {@code weekEnds} and {@code deptSchedule}.
     */
    @Override
    public Department getWithWeekEndsAndSched(int id) {
        Department department = crudRepository.getWithWeekEndsAndSched(id);
        return department;
    }
}
