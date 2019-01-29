package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.alvisid.pacs.model.Chief;
import ru.alvisid.pacs.repository.ChiefRepository;
import ru.alvisid.pacs.repository.datajpa.CrudChiefRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEmployeeRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the ChiefRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DataJpaChiefRepositoryImpl implements ChiefRepository {
    /**
     * Sort by employee's id.
     */
    public static final Sort SORT_EMPLOYEE_ID = new Sort(Sort.Direction.ASC, "employee.id");


    /**
     * An interface for department's chief repository which extends JpaRepository.
     */
    private final CrudChiefRepository crudRepository;

    /**
     * An interface for employee repository which extends JpaRepository.
     */
    private final CrudEmployeeRepository crudEmployeeRepository;

    /**
     * Constructs a new DataJpaChiefRepositoryImpl with the specified CrudChiefRepository and CrudEmployeeRepository.
     *
     * @param crudRepository         the specified <em>CrudChiefRepository</em>.
     * @param crudEmployeeRepository the specified <em>CrudEmployeeRepository</em>.
     */
    @Autowired
    public DataJpaChiefRepositoryImpl(CrudChiefRepository crudRepository,
                                      CrudEmployeeRepository crudEmployeeRepository) {
        this.crudRepository = crudRepository;
        this.crudEmployeeRepository = crudEmployeeRepository;
    }

    /**
     * Saves or updates a given employee's chief.
     * Returns null if there aren't updating value in the data base.
     *
     * @param chief a employee's chief to save.
     * @return the saved employee's chief.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Chief save(Chief chief) {
        if (chief.isNew() || !Objects.isNull(get(chief.getId()))) {
            return save(chief);
        }

        return null;
    }

    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param chief   the object to save or update.
     * @param empId   the employee's id, the employee will be inserted to the
     *                saved object's {@code employee} field.
     * @param chiefId the chief's id, the chief will be inserted to the
     *                saved object's {@code chief} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public Chief save(Chief chief, int empId, int chiefId) {
        chief.setEmployee(crudEmployeeRepository.getOne(empId));
        chief.setChief(crudEmployeeRepository.getOne(chiefId));
        return save(chief);
    }

    /**
     * Deletes an employee's chief by given id.
     *
     * @param id the specific id of the deleted employee's chief.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a employee's chief by given id.
     *
     * @param id the specific id of employee's chief to get.
     * @return a employee's chief by the given id,
     * null - if there aren't department's chief with specific id in the DB.
     */
    @Override
    public Chief get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all employee's chiefs sorted with the specified sort.
     * List is sorted by employee's id.
     *
     * @return list of all employee's chiefs.
     */
    @Override
    public List <Chief> getAll() {
        return crudRepository.findAll(SORT_EMPLOYEE_ID);
    }

    /**
     * Returns an employee's chief by a employee's id.
     *
     * @param empId the specified employee's id.
     * @return the employee's chief by an employee's id.
     */
    @Override
    public Chief getByEmployeeId(int empId) {
        return crudRepository.getByEmployeeId(empId);
    }
}
