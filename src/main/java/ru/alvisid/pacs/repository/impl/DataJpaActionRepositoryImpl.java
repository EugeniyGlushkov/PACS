package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.repository.ActionRepository;
import ru.alvisid.pacs.repository.datajpa.CrudActionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the ActionRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaActionRepositoryImpl implements ActionRepository {
    /**
     * Sort by action's time, employee's last name, first name and second name.
     */
    private static final Sort SORT_TIME_LNAME_FNAME_SNAME =
            new Sort(Sort.Direction.ASC,
                    "actionTime",
                    "employee.lastName",
                    "employee.firstName",
                    "employee.secondName");

    /**
     * Sort by action's time.
     */
    private static final Sort SORT_TIME =
            new Sort(Sort.Direction.ASC, "actionTime");

    /**
     * An interface for action which extends JpaRepository.
     */
    private final CrudActionRepository crudRepository;

    /**
     * Constructs a new DataJpaActionRepositoryImpl with the specified CrudActionRepository.
     *
     * @param crudRepository the specified CrudActionRepository.
     */
    @Autowired
    public DataJpaActionRepositoryImpl(CrudActionRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given action.
     * Returns null if there aren't updating value in the data base.
     *
     * @param action an action to save.
     * @return the saved action.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Action save(Action action) {
        if (action.isNew() || !Objects.isNull(get(action.getId()))) {
            return crudRepository.save(action);
        }

        return null;
    }

    /**
     * Deletes an action by given id.
     *
     * @param id the specified id of a deleted action.
     * @return {@code true} - the entity is deleted, {@code false} - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns an action by given id.
     *
     * @param id the specifiec id of the object to get.
     * @return an action by the given id,
     * null - if there aren't action with cpecifiec id in the DB.
     */
    @Override
    public Action get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all actions sorted with specified sort.
     * List is sorted by action's time, employee's last name, first name and second name.
     *
     * @return list of all actions
     * @see DataJpaActionRepositoryImpl#SORT_TIME
     * @see DataJpaActionRepositoryImpl#getAllByEmplId(int)
     * @see DataJpaActionRepositoryImpl#getAllBetween(LocalDateTime, LocalDateTime)
     */
    @Override
    public List<Action> getAll() {
        return crudRepository.findAll(SORT_TIME_LNAME_FNAME_SNAME);
    }

    /**
     * Returns all actions which are done by specifiec employee sorted with specified sort.
     * List is sorted by action's time.
     *
     * @param id the employee's id.
     * @return all actions which are done by specifiec employee sorted with specified sort.
     * @see DataJpaActionRepositoryImpl#SORT_TIME_LNAME_FNAME_SNAME
     * @see DataJpaActionRepositoryImpl#getAll()
     * @see DataJpaActionRepositoryImpl#getAllBetween(LocalDateTime, LocalDateTime)
     */
    @Override
    public List<Action> getAllByEmplId(int id) {
        return crudRepository.findAllByEmployeeId(id, SORT_TIME);
    }

    /**
     * Returns all actions in the specified time interval sorted with specified sort.
     * List is sorted by action's time, employee's last name, first name and second name.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @return all actions in the specified time interval sorted with specified sort.
     * @see DataJpaActionRepositoryImpl#SORT_TIME
     * @see DataJpaActionRepositoryImpl#getAll()
     * @see DataJpaActionRepositoryImpl#getAllByEmplId(int)
     */
    @Override
    public List<Action> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return crudRepository.findAllBetween(start, end, SORT_TIME_LNAME_FNAME_SNAME);
    }
}
