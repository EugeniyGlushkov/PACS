package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.repository.EmployeeRepository;
import ru.alvisid.pacs.repository.datajpa.CrudDepartmentRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEmployeeRepository;
import ru.alvisid.pacs.repository.datajpa.CrudPositionRepository;

import java.util.List;
import java.util.Objects;

import static ru.alvisid.pacs.util.ValidationUtil.*;

/**
 * DataJpa implementation of the EmployeeRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaEmployeeRepositoryImpl implements EmployeeRepository {
    /**
     * Sort by last name, first name, second name.
     */
    private static final Sort SORT_LNAME_FNAME_SNAME =
            new Sort(Sort.Direction.ASC, "lastName", "firstName", "secondName");

    /**
     * An interface for employee repository which extends JpaRepository.
     */
    private final CrudEmployeeRepository crudRepository;

    /**
     * An interface for department repository which extends JpaRepository.
     */
    private final CrudDepartmentRepository crudDepartmentRepository;

    /**
     * An interface for position repository which extends JpaRepository.
     */
    private final CrudPositionRepository crudPositionRepository;

    /**
     * Constructs a new DataJpaEmployeeRepositoryImpl with the specified CrudEmployeeRepository,
     * CrudDepartmentRepository and CrudPositionRepository.
     *
     * @param crudRepository           the specified <em>CrudEmployeeRepository</em>.
     * @param crudDepartmentRepository the specified <em>CrudDepartmentRepository</em>.
     * @param crudPositionRepository   the specified <em>CrudPositionRepository</em>.
     */
    @Autowired
    public DataJpaEmployeeRepositoryImpl(CrudEmployeeRepository crudRepository,
                                         CrudDepartmentRepository crudDepartmentRepository,
                                         CrudPositionRepository crudPositionRepository) {
        this.crudRepository = crudRepository;
        this.crudDepartmentRepository = crudDepartmentRepository;
        this.crudPositionRepository = crudPositionRepository;
    }

    /**
     * Saves or updates a given employee.
     * Returns null if there aren't updating value in the data base.
     *
     * @param employee an employee to save.
     * @return the saved or updated employee,
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Employee save(Employee employee) {
        if (employee.isNew() || !Objects.isNull(get(employee.getId()))) {
            return crudRepository.save(employee);
        }

        return null;
    }

    /**
     * Saves or updates a given object with inserted parameters.
     *
     * @param employee   the object to save or update.
     * @param deptId     department's id, the department will be inserted to the object's
     *                   {@code department} field.
     * @param positionId position's id, the position will be inserted to the object's
     *                   {@code position} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    @Override
    public Employee save(Employee employee, int deptId, int positionId) {
        employee.setDepartment(crudDepartmentRepository.getOne(deptId));
        employee.setPosition(crudPositionRepository.getOne(positionId));
        return save(employee);
    }

    /**
     * Deletes an employee by given id.
     *
     * @param id id of the employee that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns an employee by given id.
     *
     * @param id id of the employee to return.
     * @return the employee by given id.
     */
    @Override
    public Employee get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all employees sorted with cpecifiec sort.
     *
     * @return list of all employees.
     * @see DataJpaEmployeeRepositoryImpl#SORT_LNAME_FNAME_SNAME
     */
    @Override
    public List<Employee> getAll() {
        return crudRepository.findAll(SORT_LNAME_FNAME_SNAME);
    }

    /**
     * Returns all employees by department id sorted with specific sort.
     *
     * @param deptId the department id.
     * @return list of all employees by department id.
     * @see DataJpaEmployeeRepositoryImpl#SORT_LNAME_FNAME_SNAME
     */
    @Override
    public List<Employee> getAllByDeptId(int deptId) {
        return crudRepository.findAllByDeptId(deptId, SORT_LNAME_FNAME_SNAME);
    }

    /**
     * Returns an employee by given email.
     *
     * @param email the specified email.
     * @return the employee by given email.
     */
    @Override
    public Employee getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }
}
