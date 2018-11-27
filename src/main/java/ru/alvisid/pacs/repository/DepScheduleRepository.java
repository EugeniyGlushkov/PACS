package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.DeptSchedule;

import java.util.List;

/**
 * The interface with a generalized functional for schedule's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DepScheduleRepository {
    /**
     * Saves or updates a given schedule.
     *
     * @param schedule schedule to save or update.
     * @return a saved or update schedule,
     * null - if there aren't updated schedule in the repository.
     */
    DeptSchedule save(DeptSchedule schedule);

    /**
     * Deletes the schedule with specifiec id.
     *
     * @param id the specifiec id of a deleted schedule.
     * @return true - if a schedule with the specifiec id are deleted,
     * false - if there aren't the schedule with the cpecifiec id in the DB.
     */
    boolean delete(long id);

    /**
     * Returns a schedule with the cpecifiec id.
     *
     * @param id the specifiec id of schedule to get.
     * @return a schedule with the cpecifiec id,
     * null - if there aren't schedule with cpecifiec id  in the DB.
     */
    DeptSchedule get(long id);

    /**
     * Returns list with all schedules.
     *
     * @return list with all schedules.
     */
    List<DeptSchedule> getAll();

    /**
     * Returns list with schedules by department {@code id}.
     *
     * @return llist with schedules by department {@code id}.
     */
    List<DeptSchedule> getAllByDeptId();
}
