package ru.alvisid.pacs.repository;


import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface with a generalized functional for visitor's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface VisitorRepository {
    /**
     * Saves or updates a given visitor.
     *
     * @param visitor visitor to save or update.
     * @return a saved or update visitor,
     * null - if there aren't updated visitor in the repository.
     */
    Visitor save(Visitor visitor);

    /**
     * Deletes the visitor with specifiec id.
     *
     * @param id the specifiec id of a deleted visitor.
     * @return true - if a visitor with the specifiec id are deleted,
     * false - if there aren't the visitor with the cpecifiec id in the DB.
     */
    boolean delete(long id);

    /**
     * Returns a visitor with the cpecifiec id.
     *
     * @param id the specified id of visitor to get.
     * @return a visitor with the cpecifiec id,
     * null - if there aren't visitor with cpecifiec id  in the DB.
     */
    Visitor get(long id);

    /**
     * Returns list with all visitors.
     *
     * @return list with all visitors.
     */
    List <Visitor> getAll();

    /**
     * Returns a visitors list by last name, first name and second name.
     *
     * @param lastName   the last name.
     * @param firstName  the first name.
     * @param secondName the second name.
     * @return the visitors list by last name, first name and second name.
     */
    List <Visitor> getAllByName(String lastName, String firstName, String secondName);

    /**
     * Returns a visitors list which contains visitors
     * with enter time in a specified time interval.
     *
     * @param startTime the start of the time interval.
     * @param endTime the end of the time interval.
     * @return the isitors list which contains visitors with enter time in a specified time interval.
     */
    List <Visitor> getAllByEnterTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
