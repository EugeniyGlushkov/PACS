package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.alvisid.pacs.model.Absence;
import ru.alvisid.pacs.repository.AbsenceRepository;
import ru.alvisid.pacs.repository.datajpa.CrudAbsenceRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the AbsenceRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DataJpaAbsenceRepository implements AbsenceRepository {
    /**
     * Sort by employee's last name, first name, second name and absence's start date.
     */
    private static final Sort SORT_LNAME_FNAME_SNAME_STARTDATE =
            new Sort(Sort.Direction.ASC,
                    "employee.lastName",
                    "employee.firstName",
                    "employee.secondName",
                    "startAbsenceDate");

    /**
     * Sort by absence's start date.
     */
    private static final Sort SORT_STARTDATE = new Sort(Sort.Direction.ASC, "startAbsenceDate");

    /**
     * An interface for absence which extends JpaRepository.
     */
    @Autowired
    CrudAbsenceRepository crudRepository;

    /**
     * Saves or updates a given absence.
     * Returns null if there aren't updating value in the data base.
     *
     * @param absence a absence to save.
     * @return the saved absence.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Absence save(Absence absence) {
        if (absence.isNew() || !Objects.isNull(crudRepository.findById(absence.getId()))) {
            return crudRepository.save(absence);
        }

        return null;
    }

    /**
     * Deletes a absence by given id.
     *
     * @param id the specifiec id of the deleted absence.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a absence by given id.
     *
     * @param id the specifiec id of absence to get.
     * @return a absence by the given id,
     * null - if there aren't absence with cpecifiec id in the DB.
     */
    @Override
    public Absence get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all departments sorted with specifiec sort.
     * List is sorted by employee's last name, first name, second name and absence's start date.
     *
     * @return list of all departments.
     * @see DataJpaAbsenceRepository#SORT_STARTDATE
     * @see DataJpaAbsenceRepository#getAllByEmplId(int)
     * @see DataJpaAbsenceRepository#getAllByEmplDeptId(int)
     */
    @Override
    public List<Absence> getAll() {
        return crudRepository.findAll(SORT_LNAME_FNAME_SNAME_STARTDATE);
    }

    /**
     * Returns the list with all absences by employee's id.
     * List is sorted by absence's start date.
     *
     * @param id the employee's id.
     * @return the list with all absences by employee's id.
     * @see DataJpaAbsenceRepository#SORT_LNAME_FNAME_SNAME_STARTDATE
     * @see DataJpaAbsenceRepository#getAll()
     * @see DataJpaAbsenceRepository#getAllByEmplDeptId(int)
     */
    @Override
    public List<Absence> getAllByEmplId(int id) {
        return crudRepository.findAllByEmployeeId(id, SORT_STARTDATE);
    }

    /**
     * Returns the list with all absences by id of the employee's department.
     * List is sorted by employee's last name, first name, second name and absence's start date.
     *
     * @param id the department's id.
     * @return the list with all absences by id of the employee's department.
     * @see DataJpaAbsenceRepository#SORT_STARTDATE
     * @see DataJpaAbsenceRepository#getAll()
     * @see DataJpaAbsenceRepository#getAllByEmplId(int)
     */
    @Override
    public List<Absence> getAllByEmplDeptId(int id) {
        return crudRepository.findAllByEmployeeDepartmentId(id, SORT_LNAME_FNAME_SNAME_STARTDATE);
    }
}
