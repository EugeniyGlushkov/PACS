package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class DataJpaActionRepository implements ActionRepository {
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
    @Autowired
    CrudActionRepository crudRepository;

    /**
     * Saves or updates a given action.
     * Returns null if there aren't updating value in the data base.
     *
     * @param obj an action to save.
     * @return the saved action.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Action save(Action obj) {
        if (obj.isNew() || !Objects.isNull(crudRepository.findById(obj.getId()))) {
            return crudRepository.save(obj);
        }

        return null;
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Action get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Action> getAll() {
        return crudRepository.findAll(SORT_TIME_LNAME_FNAME_SNAME);
    }

    @Override
    public List<Action> getAllByEmplId(int id) {
        return crudRepository.findAllByEmployeeId(id, SORT_TIME);
    }

    @Override
    public List<Action> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return crudRepository.findAllBetween(start, end, SORT_TIME_LNAME_FNAME_SNAME);
    }
}
