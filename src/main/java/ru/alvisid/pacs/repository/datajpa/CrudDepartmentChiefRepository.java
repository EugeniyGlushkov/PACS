package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.DepartmentChief;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for department chief.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudDepartmentChiefRepository extends JpaRepository <DepartmentChief, Integer> {
    /**
     * Saves or updates a given department's chief.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param deptChief a department's chief to save.
     * @return the saved department's chief.
     */
    @Transactional
    @Override
    DepartmentChief save(DepartmentChief deptChief);

    /**
     * Deletes a department's chief by given id.
     *
     * @param id id of the department's chief that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM DepartmentChief dc WHERE dc.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with a department's chief by given id inside.
     *
     * @param integer id of the department's chief to return.
     * @return a container with a department's chief by given id inside.
     */
    @Override
    Optional <DepartmentChief> findById(Integer integer);

    /**
     * Returns all department's chiefs sorted with a given sort.
     *
     * @param sort sort for department's chiefs list.
     * @return list of all department's chiefs.
     */
    @Override
    List <DepartmentChief> findAll(Sort sort);

    /**
     * Returns a department's chief by a department's id.
     *
     * @param deptId the specified department's id.
     * @return the department's chief by a department's id.
     */
    @Query("SELECT dc FROM DepartmentChief dc WHERE dc.department.id=:deptId")
    DepartmentChief getByDepartmentId(@Param("deptId") int deptId);
}
