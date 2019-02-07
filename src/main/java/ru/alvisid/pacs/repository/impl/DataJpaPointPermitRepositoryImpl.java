package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.PointPermit;
import ru.alvisid.pacs.repository.PointPermitRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEmployeeRepository;
import ru.alvisid.pacs.repository.datajpa.CrudPointActionRepository;
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
            "pointAction.actionType");

    /**
     * An interface for point permit repository which extends JpaRepository.
     */
    private final CrudPointPermitRepository crudRepository;

    /**
     * An interface for employee repository which extends JpaRepository.
     */
    private final CrudEmployeeRepository crudEmployeeRepository;

    /**
     * An interface for point action repository which extends JpaRepository.
     */
    private final CrudPointActionRepository crudPointActionRepository;

    /**
     * Constructs a new DataJpaPointPermitRepositoryImpl with the specified CrudPointPermitRepository,
     * CrudEmployeeRepository and CrudPointActionRepository.
     *
     * @param crudRepository            the specified <em>CrudPointPermitRepository</em>.
     * @param crudEmployeeRepository    the specified <em>CrudEmployeeRepository</em>.
     * @param crudPointActionRepository the specified <em>CrudPointActionRepository</em>.
     */
    @Autowired
    public DataJpaPointPermitRepositoryImpl(CrudPointPermitRepository crudRepository,
                                            CrudEmployeeRepository crudEmployeeRepository,
                                            CrudPointActionRepository crudPointActionRepository) {
        this.crudRepository = crudRepository;
        this.crudEmployeeRepository = crudEmployeeRepository;
        this.crudPointActionRepository = crudPointActionRepository;
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
            return crudRepository.save(pointPermit);
        }

        return null;
    }

    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param pointPermit   the object to save or update.
     * @param empId         the employee's id, the employee will be inserted to the
     *                      saved object's {@code employee} field.
     * @param pointActionId the point action's id, the point action will be inserted to the
     *                      saved object's {@code pointAction} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public PointPermit save(PointPermit pointPermit, int empId, int pointActionId) {
        pointPermit.setEmployee(crudEmployeeRepository.getOne(empId));
        pointPermit.setPointAction(crudPointActionRepository.getOne(pointActionId));
        return save(pointPermit);
    }

    /**
     * Deletes a point permit by given id.
     *
     * @param id the specific id of the deleted point permit.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a point permit by given id.
     *
     * @param id the specific id of point permit to get.
     * @return a point permit by the given id,
     * null - if there aren't point permit with specific id in the DB.
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
     * @see CrudPointPermitRepository#findAllByEmployeeId(int, Sort)
     */
    @Override
    public List <PointPermit> getAllByEmpId(int empId) {
        return crudRepository.findAllByEmployeeId(empId, SORT_SERCODE_LNAME_FNAME_SNAME_TYPE);
    }

    /**
     * Returns all point permits by control point's id.
     * List is sorted by control point's serial code, employee's last name, first name, second name
     * and action's type of the point action.
     *
     * @param ctrlPointId the control point's id.
     * @return Returns all point permits by control point's id.
     * @see DataJpaPointPermitRepositoryImpl#getAll()
     * @see DataJpaPointPermitRepositoryImpl#getAllByEmpId(int)
     * @see CrudPointPermitRepository#findAllByControlPointId(int, Sort)
     */
    @Override
    public List <PointPermit> getAllByControlPointId(int ctrlPointId) {
        return crudRepository.findAllByControlPointId(ctrlPointId, SORT_SERCODE_LNAME_FNAME_SNAME_TYPE);
    }

    /**
     * Returns the list of point permits by employee's id and control point's id.
     *
     * @param empId       the employee's id.
     * @param ctrlPointId the control point's id.
     * @return the list of point permits by employee's id and control point's id.
     */
    @Override
    public List <PointPermit> getAllByEmpIdAndCtrlPointId(int empId, int ctrlPointId) {
        return crudRepository.getAllByEmpIdAndCtrlPointId(empId, ctrlPointId);
    }
}
