package ru.alvisid.pacs.repository.datajpa.impl;

import org.acs.domain.model.Worker;
import org.acs.domain.repository.WorkerRepository;
import org.acs.domain.repository.datajpa.CrudWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DataJpa implementation of the WorkerRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaWorkerRepositoryImpl implements WorkerRepository {
    /**
     * Sort by first name, middle name, last name.
     */
    private static final Sort SORT_FNAME_MNAME_LNAME = new Sort(Sort.Direction.ASC, "firstName", "middleName", "lastName");

    /**
     * An interface for worker which extends JpaRepository.
     */
    @Autowired
    CrudWorkerRepository crudRepository;

    /**
     * Saves a given worker.
     *
     * @param worker a worker to save.
     * @return the saved worker.
     */
    @Override
    public Worker save(Worker worker) {
        return crudRepository.save(worker);
    }

    /**
     * Deletes a worker by given id.
     *
     * @param id id of the worker that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(long id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a worker by given id.
     *
     * @param id id of the worker to return.
     * @return a worker by given id.
     */
    @Override
    public Worker get(long id) {
        return crudRepository.findOne(id);
    }

    /**
     * Returns all workers sorted with cpecifiec sort.
     *
     * @return list of all workers.
     * @see DataJpaVisitorRepositoryImpl#SORT_FNAME_MNAME_LNAME
     */
    @Override
    public List<Worker> getAll() {
        return crudRepository.findAll(SORT_FNAME_MNAME_LNAME);
    }

    /**
     * Returns all workers by department id sorted with cpecifiec sort.
     *
     * @param depId the department id.
     * @return list of all workers by department id.
     * @see DataJpaVisitorRepositoryImpl#SORT_FNAME_MNAME_LNAME
     */
    @Override
    public List<Worker> getAllByDepId(long depId) {
        return crudRepository.findAllByDepId(depId, SORT_FNAME_MNAME_LNAME);
    }
}
