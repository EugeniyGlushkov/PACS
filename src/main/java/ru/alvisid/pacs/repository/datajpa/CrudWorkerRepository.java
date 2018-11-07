package ru.alvisid.pacs.repository.datajpa;

import org.acs.domain.model.Worker;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JpaRepository interface for worker.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudWorkerRepository extends JpaRepository<Worker, Long> {
    /**
     * Saves a given worker.
     *
     * @param worker a worker to save.
     * @return the saved worker.
     */
    @Override
    @Transactional
    Worker save(Worker worker);

    /**
     * Deletes a worker by given id.
     *
     * @param id id of the worker that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Worker w WHERE w.id =: id")
    int delete(@Param("id") long id);

    /**
     * Returnes a worker by given id.
     *
     * @param aLong id of the worker to return.
     * @return a worker by given id.
     */
    @Override
    Worker findOne(Long aLong);

    /**
     * Returnes all workers sorted with given sort.
     *
     * @param sort sort fo workers list.
     * @return list of all workers sorted with given sort.
     */
    @Override
    List<Worker> findAll(Sort sort);

    /**
     * Returnes all workers by department id sorted with given sort.
     *
     * @param sort sort fo workers list.
     * @return list of all workers by department id sorted with given sort.
     */
    @Query("SELECT w FROM Worker w WHERE w.department.id =: depId")
    List<Worker> findAllByDepId(@Param("depId") long depId, Sort sort);
}
