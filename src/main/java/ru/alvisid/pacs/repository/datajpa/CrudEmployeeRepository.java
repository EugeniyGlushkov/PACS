package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.Position;
import ru.alvisid.pacs.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * JpaRepository interface for employee.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudEmployeeRepository extends JpaRepository <Employee, Integer> {
    /**
     * Saves or updates a given employee.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param employee a employee to save.
     * @return the saved employee.
     */
    @Override
    @Transactional
    Employee save(Employee employee);

    /**
     * Deletes an employee by given id.
     *
     * @param id id of the employee that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with an employee by the given id inside.
     *
     * @param integer id of the employee to return.
     * @return a container with an employee by the given id inside.
     */
    @Override
    Optional <Employee> findById(Integer integer);

    /**
     * Returns all employees sorted with a given sort.
     *
     * @param sort the sort for employees list.
     * @return list of all employees sorted with a given sort.
     */
    @Override
    List <Employee> findAll(Sort sort);

    /**
     * Returns all employees by department id sorted with a given sort.
     *
     * @param deptId the department's id.
     * @param sort   the sort for employees list.
     * @return list of all employees by department id sorted with a given sort.
     */
    @Query("SELECT e FROM Employee e WHERE e.department.id=:deptId")
    List <Employee> findAllByDeptId(@Param("deptId") int deptId, Sort sort);

    /**
     * Returns an employee by the given email.
     *
     * @param email the specified email.
     * @return the employee by the given email.
     */
    Employee getByEmail(String email);
}
