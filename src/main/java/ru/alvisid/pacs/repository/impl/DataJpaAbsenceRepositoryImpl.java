package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
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
@Repository
public class DataJpaAbsenceRepositoryImpl implements AbsenceRepository {
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
    private final CrudAbsenceRepository crudRepository;

    /**
     * Constructs a new DataJpaAbsenceRepositoryImpl with the specified CrudAbsenceRepository.
     *
     * @param crudRepository the specified CrudAbsenceRepository.
     */
    @Autowired
    public DataJpaAbsenceRepositoryImpl(CrudAbsenceRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given absence.
     * Returns null if there aren't updating value in the data base.
     *
     * @param absence an absence to save.
     * @return the saved absence.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Absence save(Absence absence) {
        if (absence.isNew() || !Objects.isNull(get(absence.getId()))) {
            return crudRepository.save(absence);
        }

        return null;
    }

    /**
     * Deletes an absence by given id.
     *
     * @param id the specifiec id of the deleted absence.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns an absence by given id.
     *
     * @param id the specifiec id of absence to get.
     * @return an absence by the given id,
     * null - if there aren't absence with cpecifiec id in the DB.
     */
    @Override
    public Absence get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all absences sorted with specified sort.
     * List is sorted by employee's last name, first name, second name and absence's start date.
     *
     * @return list of all absences.
     * @see DataJpaAbsenceRepositoryImpl#SORT_STARTDATE
     * @see DataJpaAbsenceRepositoryImpl#getAllByEmplId(int)
     * @see DataJpaAbsenceRepositoryImpl#getAllByEmplDeptId(int)
     */
    @Override
    public List <Absence> getAll() {
        return crudRepository.findAll(SORT_LNAME_FNAME_SNAME_STARTDATE);
    }

    /**
     * Returns the list with all absences by employee's id.
     * List is sorted by absence's start date.
     *
     * @param id the employee's id.
     * @return the list with all absences by employee's id.
     * @see DataJpaAbsenceRepositoryImpl#SORT_LNAME_FNAME_SNAME_STARTDATE
     * @see DataJpaAbsenceRepositoryImpl#getAll()
     * @see DataJpaAbsenceRepositoryImpl#getAllByEmplDeptId(int)
     */
    @Override
    public List <Absence> getAllByEmplId(int id) {
        return crudRepository.findAllByEmployeeId(id, SORT_STARTDATE);
    }

    /**
     * Returns the list with all absences by id of the employee's department.
     * List is sorted by employee's last name, first name, second name and absence's start date.
     *
     * @param id the department's id.
     * @return the list with all absences by id of the employee's department.
     * @see DataJpaAbsenceRepositoryImpl#SORT_STARTDATE
     * @see DataJpaAbsenceRepositoryImpl#getAll()
     * @see DataJpaAbsenceRepositoryImpl#getAllByEmplId(int)
     */
    @Override
    public List <Absence> getAllByEmplDeptId(int id) {
        return crudRepository.findAllByEmployeeDepartmentId(id, SORT_LNAME_FNAME_SNAME_STARTDATE);
    }
}
