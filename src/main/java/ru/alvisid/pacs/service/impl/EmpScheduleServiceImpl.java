package ru.alvisid.pacs.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.Assert;
import ru.alvisid.pacs.model.EmpSchedule;
import ru.alvisid.pacs.repository.EmpScheduleRepository;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.EmpScheduleService;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.Objects;

import static ru.alvisid.pacs.util.ValidationUtil.checkNotFoundWithId;

public class EmpScheduleServiceImpl
        extends AbstractCachedService <EmpScheduleRepository, EmpSchedule> implements EmpScheduleService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "empSchedules";

    /**
     * Creates and saves a given employee schedule in the data base
     * with inserted employee.
     *
     * @param empSchedule the employee schedule to create.
     * @param empId the employee's id to insert.
     * @return the created object.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public EmpSchedule create(EmpSchedule empSchedule, int empId) {
        Assert.notNull(empSchedule, empSchedule.getClass().getSimpleName() + " must not be null");
        return repository.save(empSchedule, empId);
    }

    /**
     * Updates an existing in the data base employee schedule
     * with inserted employee.
     *
     * @param empSchedule the employee schedule to update.
     * @param empId the employee's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    @Override
    @CacheEvict(cacheResolver = "cacheResolver", allEntries = true)
    public void update(EmpSchedule empSchedule, int empId) throws NotFoundException {
        Assert.notNull(empSchedule, empSchedule.getClass().getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(empSchedule, empId), empSchedule.getId());
    }

    @Override
    public EmpSchedule getByEmpId(int empId) {
        EmpSchedule empSchedule = repository.getByEmpId(empId);

        if (Objects.nonNull(empSchedule)) {
            return empSchedule;
        }

        return null;
    }
}
