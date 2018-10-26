package ru.alvisid.pacs.repository;


import org.acs.domain.model.Worker;

import java.util.List;

/**
 * The interface with a generalized functional for worker's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface WorkerRepository {
    /**
     * Saves or updates a given worker.
     *
     * @param worker worker to save or update.
     * @return a saved or update worker,
     * null - if there aren't updated worker in the repository.
     */
    Worker save(Worker worker);

    /**
     * Deletes the worker with specifiec id.
     *
     * @param id the specifiec id of a deleted worker.
     * @return true - if a worker with the specifiec id are deleted,
     * false - if there aren't the worker with the cpecifiec id in the DB.
     */
    boolean delete(long id);

    /**
     * Returnes a worker with the cpecifiec id.
     *
     * @param id the specifiec id of worker to get.
     * @return a worker with the cpecifiec id,
     * null - if there aren't worker with cpecifiec id  in the DB.
     */
    Worker get(long id);

    /**
     * Returnes list with all workers.
     *
     * @return list with all workers.
     */
    List<Worker> getAll();

    /**
     * Returnes list with all workers by deparmetnt's id.
     *
     * @return list with all workers by deparmetnt's id.
     */
    List<Worker> getAllByDepId(long depId);
}
