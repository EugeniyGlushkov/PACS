package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for visitor.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudVisitorRepository extends JpaRepository <Visitor, Integer> {
    /**
     * Saves a given visitor.
     *
     * @param visitor a visitor to save.
     * @return the saved visitor.
     */
    @Override
    @Transactional
    Visitor save(Visitor visitor);

    /**
     * Deletes a visitor by given id.
     *
     * @param id id of the visitor that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Visitor v WHERE v.id =: id")
    int delete(@Param("id") long id);

    /**
     * Returns a visitor by given id.
     *
     * @param integer id of the visitor to return.
     * @return a visitor by given id.
     */
    @Override
    Optional <Visitor> findById(Integer integer);

    /**
     * Returns all visitors sorted with a given sort.
     *
     * @param sort the sort for visitors list.
     * @return the list of all visitors.
     */
    @Override
    List <Visitor> findAll(Sort sort);

    /**
     * Returns a visitors list which contains visitors
     * with enter time in a specified time interval sorted with a given sort.
     *
     * @param startTime the start of the time interval.
     * @param endTime   the end of the time interval.
     * @param sort      the sort for visitors list.
     * @return the visitors list which contains visitors with enter time in a specified time interval.
     */
    @Query("SELECT v FROM Visitor v WHERE v.enterTime>=:startTime AND v.enterTime<=:endTime")
    List <Visitor> getAllByEnterTimeBetween(@Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime,
                                            Sort sort);
}
