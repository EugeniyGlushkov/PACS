package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.Visitor;
import ru.alvisid.pacs.repository.impl.DataJpaVisitorRepositoryImpl;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.VisitorService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFound;

/**
 * Implementation of the {@code DepartmentService} interface.
 * Extends <b>AbstractService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see VisitorService
 * @see AbstractService
 */
@Service
public class VisitorServiceImpl
        extends AbstractService<DataJpaVisitorRepositoryImpl, Visitor> implements VisitorService {
    /**
     * Constructs new {@code VisitorServiceImpl} and set a specified visitor's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified visitor's repository implementation.
     */
    @Autowired
    public VisitorServiceImpl(DataJpaVisitorRepositoryImpl repository) {
        super(repository);
    }

    /**
     * Returns a visitor by given temporary number.
     *
     * @param tempNum the specified temporary number.
     * @return the visitor by given temporary number.
     * @throws NotFoundException if the visitor with the specified temporary number isn't found.
     */
    @Override
    public Visitor getByTempNum(String tempNum) throws NotFoundException {
        Assert.notNull(tempNum, "temporary number must not be null");
        return checkNotFound(repository.getByTempNum(tempNum), "temporary number=" + tempNum);
    }

    /**
     * Returns a visitors list which contains visitors
     * with enter time in a specified time interval.
     *
     * @param startTime the start of the time interval.
     * @param endTime   the end of the time interval.
     * @return the visitors list which contains visitors with enter time in a specified time interval.
     */
    @Override
    public List<Visitor> getAllByEnterTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Param start time=" + startTime +
                    " must be before than param end time=" + endTime);
        }

        return repository.getAllByEnterTimeBetween(startTime, endTime);
    }
}
