package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDate;
import java.util.List;

/**
 * The specific functional for the visitor's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface VisitorService extends TypicalService <Visitor> {
    /**
     * Returns a visitors list which contains visitors with specified visit's date.
     *
     * @param localDate the visit's date.
     * @return the visitors list which contains visitors with specified visit's date.
     */
    List <Visitor> getAllByVisitDate(LocalDate localDate);
}
