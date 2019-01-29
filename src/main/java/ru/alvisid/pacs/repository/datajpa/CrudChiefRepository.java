package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Chief;

import java.util.List;
import java.util.Optional;

/**
 * The generalized functional for chief's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudChiefRepository extends JpaRepository <Chief, Integer> {
    /**
     * Saves or updates a given employee's chief.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param chief an employee's chief to save.
     * @return the saved employee's chief.
     */
    @Transactional
    @Override
    Chief save(Chief chief);

    /**
     * Deletes a employee's chief by given id.
     *
     * @param id id of the employee's chief that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Chief c WHERE c.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with an employee's chief by a given id inside.
     *
     * @param integer id of the employee's chief to return.
     * @return a container with an employee's chief by given id inside.
     */
    @Override
    Optional <Chief> findById(Integer integer);

    /**
     * Returns all employee's chiefs sorted with a given sort.
     *
     * @param sort sort for employee's chiefs list.
     * @return list of all employee's chiefs.
     */
    @Override
    List <Chief> findAll(Sort sort);

    /**
     * Returns an employee's chief by an employee's id.
     *
     * @param empId the specified employee's id.
     * @return the employee's chief by a employee's id.
     */
    @Query("SELECT c FROM Chief c WHERE c.employee.id=:empId")
    Chief getByEmployeeId(@Param("empId") int empId);
}
