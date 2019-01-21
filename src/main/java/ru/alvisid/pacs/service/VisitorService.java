package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.Visitor;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The specific functional for the visitor's service.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface VisitorService extends TypicalService<Visitor> {
    /**
     * Returns a visitor by given temporary number.
     *
     * @param tempNum the specified temporary number.
     * @return the visitor by given temporary number.
     * @throws NotFoundException if the visitor with the specified temporary number isn't found.
     */
    Visitor getByTempNum(String tempNum) throws NotFoundException;

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
