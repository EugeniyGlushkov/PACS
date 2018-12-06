package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.repository.PointPermitRepository;
import ru.alvisid.pacs.repository.datajpa.CrudPointPermitRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the PointPermitRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaPointPermitRepositoryImpl implements PointPermitRepository {
    /**
     * Sort by control point's serial code, employee's last name, first name, second name
     * and action's type of the point action.
     */
    private static final Sort SORT_SERCODE_LNAME_FNAME_SNAME_TYPE = new Sort(Sort.Direction.ASC,
            "pointAction.controlPoint.serialCode",
            "employee.lastName",
            "employee.firstName",
            "employee.secondName",
            "startAbsenceDate",
            "pointAction.actionType.type");

    /**
     * An interface for point permit repository which extends JpaRepository.
     */
    private final CrudPointPermitRepository crudRepository;

    /**
     * Constructs a new DataJpaPointPermitRepositoryImpl with the specified CrudPointPermitRepository.
     *
     * @param crudRepository the specified CrudPointPermitRepository.
     */
    @Autowired
    public DataJpaPointPermitRepositoryImpl(CrudPointPermitRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given point permit.
     * Returns null if there aren't updating value in the data base.
     *
     * @param pointPermit a point permit to save.
     * @return the saved point permit.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public PointPermit save(PointPermit pointPermit) {
        if (pointPermit.isNew() || !Objects.isNull(get(pointPermit.getId()))) {
            return save(pointPermit);
        }

        return null;
    }

    /**
     * Deletes a point permit by given id.
     *
     * @param id the specifiec id of the deleted point permit.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a point permit by given id.
     *
     * @param id the specifiec id of point permit to get.
     * @return a point permit by the given id,
     * null - if there aren't point permit with cpecifiec id in the DB.
     */
    @Override
    public PointPermit get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all point permits sorted with specified sort.
     * List is sorted by control point's serial code, employee's last name, first name, second name
     * and action's type of the point action.
     *
     * @return list of all point permits.
     * @see DataJpaPointPermitRepositoryImpl#getAllByEmpId(int)
     * @see DataJpaPointPermitRepositoryImpl#getAllByControlPointId(int)
     */
    @Override
    public List <PointPermit> getAll() {
        return crudRepository.findAll(SORT_SERCODE_LNAME_FNAME_SNAME_TYPE);
    }

    /**
     * Returns the list with all point permits by employee's id.
     * List is sorted by {@code findAllByEmployeeId()} method's query sort.
     *
     * @param empId the employee's id.
     * @return the list with all point permits by employee's id.
     * @see DataJpaPointPermitRepositoryImpl#getAll()
     * @see DataJpaPointPermitRepositoryImpl#getAllByControlPointId(int)
     * @see CrudPointPermitRepository#findAllByEmployeeId(int)
     */
    @Override
    public List <PointPermit> getAllByEmpId(int empId) {
        return crudRepository.findAllByEmployeeId(empId);
    }

    /**
     * Returns all point permits by control point's id.
     * List is sorted by control point's serial code, employee's last name, first name, second name
     * and action's type of the point action.
     *
     * @param cPointId the control point's id.
     * @return Returns all point permits by control point's id.
     * @see DataJpaPointPermitRepositoryImpl#getAll()
     * @see DataJpaPointPermitRepositoryImpl#getAllByEmpId(int)
     */
    @Override
    public List <PointPermit> getAllByControlPointId(int cPointId) {
        return crudRepository.findAllByControlPointId(cPointId, SORT_SERCODE_LNAME_FNAME_SNAME_TYPE);
    }
}