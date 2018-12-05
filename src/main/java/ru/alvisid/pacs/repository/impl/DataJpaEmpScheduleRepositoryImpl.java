package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.EmpSchedule;
import ru.alvisid.pacs.repository.EmpScheduleRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEmpScheduleRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the EmpScheduleRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaEmpScheduleRepositoryImpl implements EmpScheduleRepository {
    /**
     * Sort by employee's last name, first name and second name.
     */
    private static final Sort SORT_LNAME_FNAME_SNAME = new Sort(Sort.Direction.ASC,
            "employee.lastName",
            "employee.firstName",
            "employee.secondName");

    /**
     * An interface for employee schedule which extends JpaRepository.
     */
    private final CrudEmpScheduleRepository crudRepository;

    /**
     * Constructs a new DataJpaEmpScheduleRepositoryImpl with the specified CrudEmpScheduleRepository.
     *
     * @param crudRepository the specified CrudEmpScheduleRepository.
     */
    @Autowired
    public DataJpaEmpScheduleRepositoryImpl(CrudEmpScheduleRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given employee schedule.
     * Returns null if there aren't updating value in the data base.
     *
     * @param empSchedule a employee schedule to save.
     * @return the saved employee schedule.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public EmpSchedule save(EmpSchedule empSchedule) {
        if (empSchedule.isNew() || !Objects.isNull(get(empSchedule.getId()))) {
            return crudRepository.save(empSchedule);
        }

        return null;
    }

    /**
     * Deletes a employee schedule by given id.
     *
     * @param id id of the employee schedule that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a employee schedule by given id.
     *
     * @param id the id of the employee schedule to return.
     * @return an employee schedule by given id,
     * null - if there aren't employee schedule with cpecifiec id  in the DB.
     */
    @Override
    public EmpSchedule get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all employee schedules sorted.
     * List is sorted with employee's last name, first name and second name.
     *
     * @return the list of all employee schedules.
     */
    @Override
    public List <EmpSchedule> getAll() {
        return crudRepository.findAll(SORT_LNAME_FNAME_SNAME);
    }

    /**
     * Returns an employee schedule by given employee id.
     *
     * @param empId the employee id.
     * @return the employee schedule by given employee id.
     */
    @Override
    public EmpSchedule getByEmpId(int empId) {
        return DataAccessUtils.singleResult(crudRepository.findAllByEmployeeId(empId));
    }
}
