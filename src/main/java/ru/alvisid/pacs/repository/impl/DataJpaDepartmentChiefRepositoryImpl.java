package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.alvisid.pacs.model.DepartmentChief;
import ru.alvisid.pacs.repository.DepartmentChiefRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDepartmentChiefRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDepartmentRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEmployeeRepository;

import java.util.List;
import java.util.Objects;

public class DataJpaDepartmentChiefRepositoryImpl implements DepartmentChiefRepository {
    /**
     * Sort by department's id.
     */
    public static final Sort SORT_DEPARTMENT_ID = new Sort(Sort.Direction.ASC, "department.id");


    /**
     * An interface for department's chief repository which extends JpaRepository.
     */
    private final CrudDepartmentChiefRepository crudRepository;

    /**
     * An interface for department repository which extends JpaRepository.
     */
    private final CrudDepartmentRepository crudDepartmentRepository;

    /**
     * An interface for employee repository which extends JpaRepository.
     */
    private final CrudEmployeeRepository crudEmployeeRepository;

    /**
     * Constructs a new DataJpaDepartmentChiefRepositoryImpl with the specified CrudDepartmentChiefRepository,
     * CrudDepartmentRepository and CrudEmployeeRepository.
     *
     * @param crudRepository           the specified <em>CrudDepartmentChiefRepository</em>.
     * @param crudDepartmentRepository the specified <em>CrudDepartmentRepository</em>.
     * @param crudEmployeeRepository   the specified <em>CrudEmployeeRepository</em>.
     */
    @Autowired
    public DataJpaDepartmentChiefRepositoryImpl(CrudDepartmentChiefRepository crudRepository,
                                                CrudDepartmentRepository crudDepartmentRepository,
                                                CrudEmployeeRepository crudEmployeeRepository) {
        this.crudRepository = crudRepository;
        this.crudDepartmentRepository = crudDepartmentRepository;
        this.crudEmployeeRepository = crudEmployeeRepository;
    }

    /**
     * Saves or updates a given department's chief.
     * Returns null if there aren't updating value in the data base.
     *
     * @param deptChief a department's chief to save.
     * @return the saved department's chief.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public DepartmentChief save(DepartmentChief deptChief) {
        if (deptChief.isNew() || !Objects.isNull(get(deptChief.getId()))) {
            return save(deptChief);
        }

        return null;
    }

    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param deptChief the object to save or update.
     * @param deptId    the department's id, the department will be inserted to the
     *                  saved object's {@code department} field.
     * @param emplId    the employee's id, the employee will be inserted to the
     *                  saved object's {@code employee} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public DepartmentChief save(DepartmentChief deptChief, int deptId, int emplId) {
        deptChief.setDepartment(crudDepartmentRepository.getOne(deptId));
        deptChief.setEmployee(crudEmployeeRepository.getOne(emplId));
        return save(deptChief);
    }

    /**
     * Deletes a department's chief by given id.
     *
     * @param id the specific id of the deleted department's chief.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a department's chief by given id.
     *
     * @param id the specific id of department's chief to get.
     * @return a department's chief by the given id,
     * null - if there aren't department's chief with specific id in the DB.
     */
    @Override
    public DepartmentChief get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all department's chiefs sorted with specified sort.
     * List is sorted by department's id.
     *
     * @return list of all department's chiefs.
     */
    @Override
    public List <DepartmentChief> getAll() {
        return crudRepository.findAll(SORT_DEPARTMENT_ID);
    }

    /**
     * Returns a department's chief by a department's id.
     *
     * @param deptId the specified department's id.
     * @return the department's chief by a department's id.
     */
    @Override
    public DepartmentChief getByDeptId(int deptId) {
        return crudRepository.getByDepartmentId(deptId);
    }
}
