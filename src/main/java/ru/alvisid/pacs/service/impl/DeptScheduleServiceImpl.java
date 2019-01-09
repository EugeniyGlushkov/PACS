package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.repository.impl.DataJpaDeptScheduleRepositoryImpl;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;
import ru.alvisid.pacs.service.DeptScheduleService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.Objects;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

/**
 * Implementation of the {@code DeptScheduleService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see DeptScheduleService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class DeptScheduleServiceImpl
        extends AbstractCachedService<DataJpaDeptScheduleRepositoryImpl, DeptSchedule> implements DeptScheduleService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "deptSchedules";

    /**
     * Creates and saves a given department schedule in the data base
     * with inserted department.
     * Evicts a cache that is got by cache resolver.
     *
     * @param deptSchedule the department schedule to create.
     * @param deptId       the department's id to insert.
     * @return the created object.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public DeptSchedule create(DeptSchedule deptSchedule, int deptId) {
        Assert.notNull(deptSchedule, deptSchedule.getClass().getSimpleName() + " must not be null");
        return repository.save(deptSchedule, deptId);
    }

    /**
     * Updates an existing in the data base department schedule
     * with inserted department.
     * Evicts a cache that is got by cache resolver.
     *
     * @param deptSchedule the department schedule to update.
     * @param deptId       the department's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(DeptSchedule deptSchedule, int deptId) throws NotFoundException {
        Assert.notNull(deptSchedule, deptSchedule.getClass().getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(deptSchedule, deptId), deptSchedule.getId());
    }

    /**
     * Returns a department schedule by given department id.
     *
     * @param deptId the department id.
     * @return the department schedule by given department id.
     * null if there are no department schedule with the deptId.
     */
    @Override
    public DeptSchedule getByDeptId(int deptId) {
        DeptSchedule deptScheduleById = repository.getByDeptId(deptId);

        if (Objects.isNull(deptScheduleById)) {
            throw new NotFoundException("There are no department's schedule with department id: " + deptId + " in the DB!");
        }

        return deptScheduleById;
    }

    /**
     * Constructs new {@code DeptScheduleServiceImpl} and set a specified department schedule's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified department's repository implementation.
     */
    @Autowired
    public DeptScheduleServiceImpl(DataJpaDeptScheduleRepositoryImpl repository) {
        super(repository);
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
