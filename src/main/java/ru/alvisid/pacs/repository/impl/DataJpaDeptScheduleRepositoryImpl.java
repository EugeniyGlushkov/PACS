package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.repository.DeptScheduleRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDeptScheduleRepository;

import java.util.List;
import java.util.Objects;

import static ru.alvisid.pacs.util.ValidationUtil.*;

/**
 * DataJpa implementation of the DeptScheduleRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaDeptScheduleRepositoryImpl implements DeptScheduleRepository {
    /**
     * An interface for department schedule which extends JpaRepository.
     */
    private final CrudDeptScheduleRepository crudRepository;

    /**
     * Constructs a new DataJpaDeptScheduleRepositoryImpl with the specified CrudDeptScheduleRepository.
     *
     * @param crudRepository the specified CrudDeptScheduleRepository.
     */
    @Autowired
    public DataJpaDeptScheduleRepositoryImpl(CrudDeptScheduleRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given department schedule.
     * Returns null if there aren't updating value in the data base.
     *
     * @param schedule a department schedule to save.
     * @return the saved department schedule.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public DeptSchedule save(DeptSchedule schedule) {
        if (schedule.isNew() || !Objects.isNull(get(schedule.getId()))) {
            return crudRepository.save(schedule);
        }

        return null;
    }

    /**
     * Deletes a department schedule by given id.
     *
     * @param id id of the department schedule that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a department schedule by given id.
     *
     * @param id the id of the department schedule to return.
     * @return a department schedule by given id,
     * null - if there aren't department schedule with cpecifiec id  in the DB.
     */
    @Override
    public DeptSchedule get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all department schedules sorted.
     *
     * @return the list of all department schedules.
     */
    @Override
    public List <DeptSchedule> getAll() {
        return crudRepository.findAll();
    }

    /**
     * Returns a department schedule by given department id.
     *
     * @param deptId the department id.
     * @return the department schedule by given department id.
     */
    @Override
    public DeptSchedule getByDeptId(int deptId) {
        return DataAccessUtils.singleResult(crudRepository.fintAllByDeptId(deptId));
    }
}
