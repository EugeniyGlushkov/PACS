package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
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
@Transactional(readOnly = true)
public interface CrudDepartmentRepository extends JpaRepository <Department, Integer> {
    /**
     * Saves or updates a given department.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
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
    @Query("DELETE FROM Department d WHERE d.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with department by given id inside.
     *
     * @param integer id of the department to return.
     * @return a container with department by given id inside.
     */
    @Override
    Optional <Department> findById(Integer integer);

    /**
     * Returns all departments sorted with given sort.
     *
     * @param sort sort fo departments list.
     * @return list of all departments.
     */
    @Override
    List <Department> findAll(Sort sort);

    /**
     * Returns a department with filled fields: {@code weekEnds} and {@code deptSchedule}
     * by specified id.
     *
     * @param id the specified department's id.
     * @return the department with filled fields: {@code weekEnds} and {@code deptSchedule}.
     */
    /*@EntityGraph(attributePaths = {"weekEnds", "deptSchedule"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM Department d WHERE d.id=?1")*/
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.weekEnds LEFT JOIN FETCH d.deptSchedule WHERE d.id=?1")
    Department getWithWeekEndsAndSched(int id);
}
