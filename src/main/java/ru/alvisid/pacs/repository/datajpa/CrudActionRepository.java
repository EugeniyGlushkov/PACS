package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Action;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for action.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudActionRepository extends JpaRepository<Action, Integer> {
    /**
     * Saves or updates a given action.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param action an action to save.
     * @return the saved action.
     */
    @Transactional
    @Override
    Action save(Action action);

    /**
     * Deletes an action by given id.
     *
     * @param id id of the action that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Action a WHERE a.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with action by given id inside.
     *
     * @param integer id of the action to return.
     * @return a container with action by given id inside.
     */
    @Override
    Optional<Action> findById(Integer integer);

    /**
     * Returns all actions sorted with a given sort.
     *
     * @param sort sort for actions list.
     * @return list of all actions.
     */
    @Override
    List<Action> findAll(Sort sort);

    /**
     * Returns the list with all actions by employee's id and sorted by specified sort.
     *
     * @param empId the employee's id.
     * @param sort  the specified list's sort.
     * @return the list with all actions by employee's id and sorted by specified sort.
     */
    @Query("SELECT a FROM Action a WHERE a.employee.id=:empId")
    List<Action> findAllByEmployeeId(@Param("empId") int empId, Sort sort);

    /**
     * Returns the list of the all actions in the specified time interval.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @param sort  the specified list's sort.
     * @return the list of the all actions in the specified time interval.
     */
    @Query("SELECT a FROM Action a WHERE a.actionTime>=:start AND a.actionTime<=:end")
    List<Action> findAllBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, Sort sort);
}
