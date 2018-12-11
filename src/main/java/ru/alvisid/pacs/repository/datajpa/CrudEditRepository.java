package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Edit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for edit.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudEditRepository extends JpaRepository <Edit, Integer> {
    /**
     * Saves or updates a given edit.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param edit an edit to save.
     * @return the saved edit.
     */
    @Transactional
    @Override
    Edit save(Edit edit);

    /**
     * Deletes an edit by given id.
     *
     * @param id id of the edit that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Edit e WHERE e.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with edit by given id inside.
     *
     * @param integer id of the edit to return.
     * @return a container with edit by given id inside.
     */
    @Override
    Optional <Edit> findById(Integer integer);

    /**
     * Returns all edits sorted with a given sort.
     *
     * @param sort sort for edits list.
     * @return list of all edits.
     */
    @Override
    List <Edit> findAll(Sort sort);

    /**
     * Returns the list with all edits by employee's id and sorted by specified sort.
     *
     * @param empId the employee's id.
     * @param sort  the specified list's sort.
     * @return the list with all edits by employee's id and sorted by specified sort.
     */
    @Query("SELECT e FROM Edit e WHERE e.employee.id=:empId")
    List <Edit> findAllByEmployeeId(@Param("empId") int empId, Sort sort);

    /**
     * Returns the list of the all edits in the specified time interval.
     *
     * @param start the start of the time interval.
     * @param end   the end of the time interval.
     * @param sort  the specified list's sort.
     * @return the list of the all edits in the specified time interval.
     */
    @Query("SELECT e FROM Edit e WHERE e.editDateTime BETWEEN :start AND :end")
    List <Edit> findAllBetween(@Param("start") LocalDateTime start,
                               @Param("end") LocalDateTime end,
                               Sort sort);
}
