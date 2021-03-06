package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Edit;
import ru.alvisid.pacs.model.EditType;
import ru.alvisid.pacs.repository.EditRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEditRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEmployeeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the EditRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaEditRepositoryImpl implements EditRepository {
    /**
     * Sort by edit's time, edit's type, employee's last name, first name and second name.
     */
    private static final Sort SORT_TIME_TYPE_EMPL = new Sort(Sort.Direction.ASC,
            "editDateTime",
            "editType",
            "employee.lastName",
            "employee.firstName",
            "employee.secondName");

    /**
     * Sort by edit's time and edit's type.
     */
    private static final Sort SORT_TIME_TYPE = new Sort(Sort.Direction.ASC,
            "editDateTime", "editType");

    /**
     * An interface for edit repository which extends JpaRepository.
     */
    private final CrudEditRepository crudRepository;

    /**
     * An interface for employee repository which extends JpaRepository.
     */
    private final CrudEmployeeRepository crudEmployeeRepository;

    /**
     * Constructs a new DataJpaEditRepositoryImpl with the specified CrudEditRepository
     * and CrudEmployeeRepository.
     *
     * @param crudRepository         the specified <em>CrudEditRepository</em>.
     * @param crudEmployeeRepository the specified <em>CrudEmployeeRepository</em>.
     */
    @Autowired
    public DataJpaEditRepositoryImpl(CrudEditRepository crudRepository,
                                     CrudEmployeeRepository crudEmployeeRepository) {
        this.crudRepository = crudRepository;
        this.crudEmployeeRepository = crudEmployeeRepository;
    }

    /**
     * Saves or updates a given edit.
     * Returns null if there aren't updating value in the data base.
     *
     * @param edit an edit to save.
     * @return the saved edit.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Edit save(Edit edit) {
        if (edit.isNew() || !Objects.isNull(get(edit.getId()))) {
            return crudRepository.save(edit);
        }

        return null;
    }

    /**
     * Saves or updates a given object with inserted parameter.
     *
     * @param edit     the object to save or update.
     * @param empId    the employee's id, the employee will be inserted to the
     *                 saved object's {@code employee} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public Edit save(Edit edit, int empId) {
        edit.setEmployee(crudEmployeeRepository.getOne(empId));
        return save(edit);
    }

    /**
     * Deletes an edit by given id.
     *
     * @param id the specified id of a deleted edit.
     * @return {@code true} - the entity is deleted, {@code false} - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns an edit by given id.
     *
     * @param id the specifiec id of the object to get.
     * @return an edit by the given id,
     * null - if there aren't edit with cpecifiec id in the DB.
     */
    @Override
    public Edit get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all edits sorted with specified sort.
     * List is sorted by edit's time, edit's type, employee's last name, first name and second name.
     *
     * @return list of all edits
     * @see DataJpaEditRepositoryImpl#SORT_TIME_TYPE
     * @see DataJpaEditRepositoryImpl#getAllByEmpId(int)
     * @see DataJpaEditRepositoryImpl#getAllBetween(LocalDateTime, LocalDateTime)
     */
    @Override
    public List<Edit> getAll() {
        return crudRepository.findAll(SORT_TIME_TYPE_EMPL);
    }

    /**
     * Returns all edits which are done by specific employee sorted with specified sort.
     * List is sorted by edit's time and edit's type.
     *
     * @param id the employee's id.
     * @return all edits which are done by specific employee sorted with specified sort.
     * @see DataJpaEditRepositoryImpl#SORT_TIME_TYPE_EMPL
     * @see DataJpaEditRepositoryImpl#getAll()
     * @see DataJpaEditRepositoryImpl#getAllBetween(LocalDateTime, LocalDateTime)
     */
    @Override
    public List<Edit> getAllByEmpId(int id) {
        return crudRepository.findAllByEmployeeId(id, SORT_TIME_TYPE);
    }

    /**
     * Returns all edits in the specified time interval sorted with specified sort.
     * List is sorted by edit's time, edit's type, employee's last name, first name and second name.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @return all edits in the specified time interval sorted with specified sort.
     */
    @Override
    public List<Edit> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return crudRepository.findAllBetween(start, end, SORT_TIME_TYPE_EMPL);
    }
}
