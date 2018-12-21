package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.EmpSchedule;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for employee schedule.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudEmpScheduleRepository extends JpaRepository <EmpSchedule, Integer> {
    /**
     * Saves or updates a given employee schedule.
     *
     * @param schedule a employee schedule to save.
     * @return the saved employee schedule.
     */
    @Transactional
    @Override
    EmpSchedule save(EmpSchedule schedule);

    /**
     * Deletes a employee schedule by given id.
     *
     * @param id id of the employee schedule that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM EmpSchedule es WHERE es.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with  employee schedule by given id inside.
     *
     * @param integer id of the employee schedule to return.
     * @return a container with  employee schedule by given id inside.
     */
    @Override
    Optional <EmpSchedule> findById(Integer integer);

    /**
     * Returns all employee schedules sorted with given sort.
     *
     * @param sort sort for employee schedules list.
     * @return list of all employee schedules.
     */
    @Override
    List <EmpSchedule> findAll(Sort sort);

    /**
     * Returns a list of the all employee schedules by specified employee id.
     *
     * @param empId the employee id.
     * @return the list of the all employee schedules by employee id.
     */
    @Query("SELECT es FROM EmpSchedule es WHERE es.employee.id=:empId")
    List <EmpSchedule> findAllByEmployeeId(@Param("empId") int empId);
}
