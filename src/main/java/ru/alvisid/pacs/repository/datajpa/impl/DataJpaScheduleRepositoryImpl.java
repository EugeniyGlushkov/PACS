package ru.alvisid.pacs.repository.datajpa.impl;

import org.acs.domain.model.Schedule;
import org.acs.domain.repository.ScheduleRepository;
import org.acs.domain.repository.datajpa.CrudScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DataJpa implementation of the ScheduleRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaScheduleRepositoryImpl implements ScheduleRepository {
    /**
     * An interface for schedule which extends JpaRepository.
     */
    @Autowired
    CrudScheduleRepository crudRepository;

    /**
     * Saves a given schedule.
     *
     * @param schedule a schedule to save.
     * @return the saved schedule.
     */
    @Override
    public Schedule save(Schedule schedule) {
        return crudRepository.save(schedule);
    }

    /**
     * Deletes a schedule by given id.
     *
     * @param id id of the schedule that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(long id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a schedule by given id.
     *
     * @param id id of the schedule to return.
     * @return a schedule by given id.
     */
    @Override
    public Schedule get(long id) {
        return crudRepository.getOne(id);
    }

    /**
     * Returns all schedules sorted.
     *
     * @return list of all schedules.
     */
    @Override
    public List<Schedule> getAll() {
        return crudRepository.findAll();
    }
}
