package ru.alvisid.pacs.repository.datajpa;

import org.acs.domain.model.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JpaRepository interface for schedule.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudScheduleRepository extends JpaRepository<Schedule, Long> {
    /**
     * Saves a given schedule.
     *
     * @param schedule a schedule to save.
     * @return the saved schedule.
     */
    @Override
    @Transactional
    Schedule save(Schedule schedule);

    /**
     * Deletes a schedule by given id.
     *
     * @param id id of the schedule that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Schedule s WHERE s.id =: id")
    int delete(@Param("id") long id);

    /**
     * Returnes a schedule by given id.
     *
     * @param aLong id of the schedule to return.
     * @return a schedule by given id.
     */
    @Override
    Schedule findOne(Long aLong);

    /**
     * Returnes all schedules sorted with given sort.
     *
     * @param sort sort fo schedules list.
     * @return list of all schedules.
     */
    @Override
    List<Schedule> findAll(Sort sort);
}
