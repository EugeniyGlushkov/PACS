package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.DeptSchedule;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for department schedule.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudDepScheduleRepository extends JpaRepository<DeptSchedule, Integer> {
    /**
     * Saves a given department schedule.
     *
     * @param schedule a department schedule to save.
     * @return the saved department schedule.
     */
    @Override
    @Transactional
    DeptSchedule save(DeptSchedule schedule);

    /**
     * Deletes a department schedule by given id.
     *
     * @param id id of the department schedule that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Schedule s WHERE s.id =: id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with  department schedule by given id inside.
     *
     * @param integer id of the department schedule to return.
     * @return a container with  department schedule by given id inside.
     */
    @Override
    Optional<DeptSchedule> findById(Integer integer);

    /**
     * Returns all department schedules sorted with given sort.
     *
     * @param sort sort for department schedules list.
     * @return list of all department schedules.
     */
    @Override
    List<DeptSchedule> findAll(Sort sort);


}
