package ru.alvisid.pacs.repository;


import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDate;
import java.util.List;

/**
 * The generalized functional for visitor's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface VisitorRepository extends TypicalRepository<Visitor> {
    /**
     * Returns a visitors list which contains visitors with specified visit's date.
     *
     * @param localDate the visit's date.
     * @return the visitors list which contains visitors with specified visit's date.
     */
    List<Visitor> getAllByVisitDate(LocalDate localDate);
}
