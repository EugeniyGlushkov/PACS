package ru.alvisid.pacs.repository;


import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDate;
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
     * @param visitor the visitor to save or update.
     * @return a saved or update visitor,
     * null - if there aren't updated visitor in the repository.
     */
    Visitor save(Visitor visitor);

    /**
     * Deletes the visitor with specifiec id.
     *
     * @param id the specifiec id of a deleted visitor.
     * @return true - if a visitor with the specifiec id is deleted,
     * false - if there aren't the visitor with the cpecifiec id in the DB.
     */
    boolean delete(int id);

    /**
     * Returns a visitor with the cpecifiec id.
     *
     * @param id the specified id of visitor to get.
     * @return a visitor with the cpecifiec id,
     * null - if there aren't visitor with cpecifiec id  in the DB.
     */
    Visitor get(int id);

    /**
     * Returns the list with all visitors.
     *
     * @return the list with all visitors.
     */
    List<Visitor> getAll();

    /**
     * Returns a visitors list which contains visitors with specified visit's date.
     *
     * @param localDate the visit's date.
     * @return the visitors list which contains visitors with specified visit's date.
     */
    List<Visitor> getAllByVisitDate(LocalDate localDate);
}
