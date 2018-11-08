package ru.alvisid.pacs.repository.datajpa;

import ru.alvisid.pacs.model.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for department.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudDepartmentRepository extends JpaRepository <Department, Integer> {
    /**
     * Saves a given department.
     *
     * @param department a department to save.
     * @return the saved department.
     */
    @Override
    @Transactional
    Department save(Department department);

    /**
     * Deletes a department by given id.
     *
     * @param id id of the department that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Department d WHERE d.id =: id")
    int delete(@Param("id") int id);

    /**
     * Returnes a department by given id.
     *
     * @param integer id of the department to return.
     * @return a department by given id.
     */
    @Override
    Optional <Department> findById(Integer integer);

    /**
     * Returnes all departments sorted with given sort.
     *
     * @param sort sort fo departments list.
     * @return list of all departments.
     */
    @Override
    List <Department> findAll(Sort sort);
}
