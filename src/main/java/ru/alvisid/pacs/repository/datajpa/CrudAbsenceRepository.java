package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Absence;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for absence.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudAbsenceRepository extends JpaRepository <Absence, Integer> {
    /**
     * Saves or updates a given absence.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param absence an absence to save.
     * @return the saved absence.
     */
    @Transactional
    @Override
    Absence save(Absence absence);

    /**
     * Deletes an absence by given id.
     *
     * @param id id of the absence that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Absence a WHERE a.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with an absence by given id inside.
     *
     * @param integer id of the absence to return.
     * @return a container with an absence by given id inside.
     */
    @Override
    Optional <Absence> findById(Integer integer);

    /**
     * Returns all absences sorted with a given sort.
     *
     * @param sort sort for absences list.
     * @return list of all absences.
     */
    @Override
    List <Absence> findAll(Sort sort);

    /**
     * Returns the list with all absences by employee's id and sorted by specified sort.
     *
     * @param empId the employee's id.
     * @param sort  the specified list's sort.
     * @return the list with all absences by employee's id and sorted by specified sort.
     */
    @Query("SELECT a FROM Absence a WHERE a.employee.id=:empId")
    List <Absence> findAllByEmployeeId(@Param("empId") int empId, Sort sort);

    /**
     * Returns the list with all absences by id of the employee's department
     * and sorted by specified sort.
     *
     * @param deptId the department's id.
     * @param sort   the specified list's sort.
     * @return the list with all absences by id of the employee's department
     * and sorted by specified sort.
     */
    @Query("SELECT a FROM Absence a WHERE a.employee.department.id=:deptId")
    List <Absence> findAllByEmployeeDepartmentId(@Param("deptId") int deptId, Sort sort);
}
