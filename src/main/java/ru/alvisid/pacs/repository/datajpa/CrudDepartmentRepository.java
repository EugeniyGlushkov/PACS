package ru.alvisid.pacs.repository.datajpa;

import org.acs.domain.model.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JpaRepository interface for department.
 */
public interface CrudDepartmentRepository extends JpaRepository<Department, Long> {
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
    int delete(@Param("id") long id);

    /**
     * Returnes a department by given id.
     *
     * @param aLong id of the department to return.
     * @return a department by given id.
     */
    @Override
    Department findOne(Long aLong);

    /**
     * Returnes all departments sorted with given sort.
     *
     * @param sort sort fo departments list.
     * @return list of all departments.
     */
    @Override
    List<Department> findAll(Sort sort);
}
