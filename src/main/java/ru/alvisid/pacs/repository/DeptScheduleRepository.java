package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.DeptSchedule;

import java.util.List;

/**
 * The generalized functional for schedule's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DeptScheduleRepository {
    /**
     * Saves or updates a given schedule.
     *
     * @param schedule the schedule to save or update.
     * @return a saved or update schedule,
     * null - if there aren't updated schedule in the repository.
     */
    DeptSchedule save(DeptSchedule schedule);

    /**
     * Deletes the schedule with specifiec id.
     *
     * @param id the specifiec id of a deleted schedule.
     * @return true - if a schedule with the specifiec id is deleted,
     * false - if there aren't the schedule with the cpecifiec id in the DB.
     */
    boolean delete(int id);

    /**
     * Returns a schedule with the cpecifiec id.
     *
     * @param id the specifiec id of schedule to get.
     * @return a schedule with the cpecifiec id,
     * null - if there aren't department schedule with cpecifiec id  in the DB.
     */
    DeptSchedule get(int id);

    /**
     * Returns the list with all schedules.
     *
     * @return the list with all schedules.
     */
    List<DeptSchedule> getAll();

    /**
     * Returns the department's schedule by department {@code id}.
     *
     * @param deptId the department's id.
     * @return the department's schedule by department {@code id}.
     */
    DeptSchedule getByDeptId(int deptId);
}
