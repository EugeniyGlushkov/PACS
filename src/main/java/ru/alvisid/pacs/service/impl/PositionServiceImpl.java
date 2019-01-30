package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.Position;
import ru.alvisid.pacs.repository.PositionRepository;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.EmployeeService;
import ru.alvisid.pacs.service.PositionService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

/**
 * Implementation of the {@code PositionService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see PositionService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class PositionServiceImpl
        extends AbstractCachedService <PositionRepository, Position> implements PositionService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "positions";

    /**
     * Employee service's realization.
     */
    private EmployeeService employeeService;

    /**
     * Deletes the object by specified id.
     *
     * @param id the specified id of a deleted object.
     * @throws NotFoundException if the entity with the specified id isn't found.
     * @throws IllegalStateException if there are some employees with the position.
     */
    @Override
    public void delete(int id) throws NotFoundException, IllegalStateException {
        if (!employeeService.getAllByPositionId(id).isEmpty()) {
            throw new IllegalStateException("Can not delete position with id=" + id + ", because there are some employees with the position.");
        }

        super.delete(id);
    }

    /**
     * Constructs new {@code PositionServiceImpl} and set a specified position's repository implementation
     * to the superclass's repository field, set a specified EmployeeService implementation.
     *
     * @param repository      the specified position's repository implementation.
     * @param employeeService the specified EmployeeService implementation.
     */
    @Autowired
    public PositionServiceImpl(PositionRepository repository, EmployeeService employeeService) {
        super(repository);
        this.employeeService = employeeService;
    }

    /**
     * Returns a cache alias for this service.
     *
     * @return the cache alias for this service.
     */
    @Override
    public String getCacheAlias() {
        return CACHE_ALIAS;
    }
}
