package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.DayOff;
import ru.alvisid.pacs.repository.DayOffRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDayOffRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDepartmentRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the DayOffRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaDayOffRepositoryImpl implements DayOffRepository {
    /**
     * Sort by department's id and date.
     */
    private static final Sort SORT_DEPTID_DATE =
            new Sort(Sort.Direction.ASC, "department.id,", "dateOff");

    /**
     * An interface for day off repository which extends JpaRepository.
     */
    private final CrudDayOffRepository crudRepository;

    /**
     * An interface for department repository which extends JpaRepository.
     */
    private final CrudDepartmentRepository crudDepartmentRepository;

    /**
     * Constructs a new DataJpaDayOffRepositoryImpl with the specified CrudDayOffRepository
     * and CrudDepartmentRepository.
     *
     * @param crudRepository           the specified <em>CrudDayOffRepository</em>.
     * @param crudDepartmentRepository the specified <em>CrudDepartmentRepository</em>.
     */
    @Autowired
    public DataJpaDayOffRepositoryImpl(CrudDayOffRepository crudRepository,
                                       CrudDepartmentRepository crudDepartmentRepository) {
        this.crudRepository = crudRepository;
        this.crudDepartmentRepository = crudDepartmentRepository;
    }

    /**
     * Saves or updates a given day off.
     * Returns null if there aren't updating value in the data base.
     *
     * @param dayOff an day off to save.
     * @return the saved day off.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public DayOff save(DayOff dayOff) {
        if (dayOff.isNew() || !Objects.isNull(get(dayOff.getId()))) {
            return crudRepository.save(dayOff);
        }

        return null;
    }

    /**
     * Saves or updates a given object with inserted parameter.
     *
     * @param dayOff the object to save or update.
     * @param deptId department's id, the department will be inserted to the object's
     *               {@code department} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public DayOff save(DayOff dayOff, int deptId) {
        dayOff.setDepartment(crudDepartmentRepository.getOne(deptId));
        return save(dayOff);
    }

    /**
     * Deletes a day off by given id.
     *
     * @param id the specifiec id of the deleted day off.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a day off by given id.
     *
     * @param id the specifiec id of day off to get.
     * @return a day off by the given id,
     * null - if there aren't day off with cpecifiec id in the DB.
     */
    @Override
    public DayOff get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all days off sorted with specified sort.
     * List is sorted by department's id and date.
     *
     * @return list of all days off.
     */
    @Override
    public List<DayOff> getAll() {
        return null;
    }
}
