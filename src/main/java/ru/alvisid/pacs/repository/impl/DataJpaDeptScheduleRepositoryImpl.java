package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.repository.DeptScheduleRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDepartmentRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDeptScheduleRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the DeptScheduleRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaDeptScheduleRepositoryImpl implements DeptScheduleRepository {
    /**
     * Sort by department's id.
     */
    private static final Sort SORT_DEPTID = new Sort(Sort.Direction.ASC, "department.id");

    /**
     * An interface for department schedule repository which extends JpaRepository.
     */
    private final CrudDeptScheduleRepository crudRepository;

    /**
     * An interface for department repository which extends JpaRepository.
     */
    private final CrudDepartmentRepository crudDepartmentRepository;

    /**
     * Constructs a new DataJpaDeptScheduleRepositoryImpl with the specified CrudDeptScheduleRepository
     * and CrudDepartmentRepository.
     *
     * @param crudRepository           the specified <em>CrudDeptScheduleRepository</em>.
     * @param crudDepartmentRepository the specified <em>CrudDepartmentRepository</em>.
     */
    @Autowired
    public DataJpaDeptScheduleRepositoryImpl(CrudDeptScheduleRepository crudRepository,
                                             CrudDepartmentRepository crudDepartmentRepository) {
        this.crudRepository = crudRepository;
        this.crudDepartmentRepository = crudDepartmentRepository;
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
     * Saves or updates a given object with inserted parameter.
     *
     * @param schedule the object to save or update.
     * @param deptId   department's id, the department will be inserted to the object's
     *                 {@code department} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public DeptSchedule save(DeptSchedule schedule, int deptId) {
        schedule.setDepartment(crudDepartmentRepository.getOne(deptId));
        return save(schedule);
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
     * null - if there aren't department schedule with specified id  in the DB.
     */
    @Override
    public DeptSchedule get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all department schedules sorted.
     * List is sorted with department's id.
     *
     * @return the list of all department schedules.
     */
    @Override
    public List<DeptSchedule> getAll() {
        return crudRepository.findAll(SORT_DEPTID);
    }

    /**
     * Returns a department schedule by given department id.
     *
     * @param deptId the department id.
     * @return the department schedule by given department id,
     * null - if there aren't department schedule with specified department's id in the DB.
     */
    @Override
    public DeptSchedule getByDeptId(int deptId) {
        return DataAccessUtils.singleResult(crudRepository.findAllByDeptId(deptId));
    }
}
