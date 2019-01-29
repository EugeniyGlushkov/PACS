package ru.alvisid.pacs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.pacs.model.AbsenceReason;
import ru.alvisid.pacs.repository.AbsenceReasonRepository;
import ru.alvisid.pacs.service.AbsenceReasonService;
import ru.alvisid.pacs.service.AbstractCachedService;
import ru.alvisid.pacs.service.AbstractService;

/**
 * Implementation of the {@code AbsenceReasonService} interface.
 * Extends <b>AbstractService</b> and <b>AbstractCachedService</b> functionality.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbsenceReasonService
 * @see AbstractCachedService
 * @see AbstractService
 */
@Service
public class AbsenceReasonServiceImpl
        extends AbstractCachedService<AbsenceReasonRepository, AbsenceReason> implements AbsenceReasonService {
    /**
     * Cache alias for access to the ehcache.
     */
    private static final String CACHE_ALIAS = "absenceReasons";

    /**
     * Constructs new {@code AbsenceReasonServiceImpl} and set a specified absence reason's repository implementation
     * to the superclass's repository field.
     *
     * @param repository the specified position's repository implementation.
     */
    @Autowired
    public AbsenceReasonServiceImpl(AbsenceReasonRepository repository) {
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
