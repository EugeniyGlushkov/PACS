package ru.alvisid.pacs.repository;


import ru.alvisid.pacs.model.Visitor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The generalized functional for visitor's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface VisitorRepository extends TypicalRepository<Visitor> {
    /**
     * Returns a visitor by given temporary number.
     *
     * @param tempNum the specified temporary number.
     * @return the visitor by given temporary number.
     */
    Visitor getByTempNum(String tempNum);

    /**
     * Returns a visitors list which contains visitors
     * with enter time in a specified time interval.
     *
     * @param startTime the start of the time interval.
     * @param endTime   the end of the time interval.
     * @return the visitors list which contains visitors with enter time in a specified time interval.
     */
    List<Visitor> getAllByEnterTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
