package ru.alvisid.pacs.repository.datajpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.repository.EmployeeRepository;
import ru.alvisid.pacs.repository.datajpa.CrudEmployeeRepository;

import java.util.List;

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
     * An interface for employee which extends JpaRepository.
     */
    @Autowired
    CrudEmployeeRepository crudRepository;

    /**
     * Saves a given employee.
     *
     * @param employee a employee to save.
     * @return the saved employee.
     */
    @Override
    public Employee save(Employee employee) {
        return crudRepository.save(employee);
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
     * @see DataJpaVisitorRepositoryImpl#SORT_FNAME_MNAME_LNAME
     */
    @Override
    public List <Employee> getAll() {
        return crudRepository.findAll(SORT_LNAME_FNAME_SNAME);
    }

    /**
     * Returns all employees by department id sorted with cpecifiec sort.
     *
     * @param deptId the department id.
     * @return list of all employees by department id.
     * @see DataJpaVisitorRepositoryImpl#SORT_FNAME_MNAME_LNAME
     */
    @Override
    public List <Employee> getAllByDeptId(int deptId) {
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
