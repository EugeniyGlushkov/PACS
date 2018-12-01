package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.alvisid.pacs.model.AbsenceReason;
import ru.alvisid.pacs.repository.AbsenceReasonRepository;
import ru.alvisid.pacs.repository.datajpa.CrudAbsenceReasonRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the AbsenceReasonRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DataJpaAbsenceReasonRepository implements AbsenceReasonRepository {
    /**
     * Sort by absence's reason.
     */
    private static final Sort SORT_REASON = new Sort(Sort.Direction.ASC, "reason");

    /**
     * An interface for absence's reason which extends JpaRepository.
     */
    @Autowired
    CrudAbsenceReasonRepository crudRepository;

    /**
     * Saves or updates a given absence's reason.
     * Returns null if there aren't updating value in the data base.
     *
     * @param reason an absence's reason to save.
     * @return the saved absence's reason.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public AbsenceReason save(AbsenceReason reason) {
        if (reason.isNew() || !Objects.isNull(crudRepository.findById(reason.getId()))) {
            return crudRepository.save(reason);
        }

        return null;
    }

    /**
     * Deletes an absence's reason by given id.
     *
     * @param id the specifiec id of the deleted absence's reason.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns an absence's reason by given id.
     *
     * @param id the specifiec id of absence's reason to get.
     * @return an absence's reason by the given id,
     * null - if there aren't absence's reason with cpecifiec id in the DB.
     */
    @Override
    public AbsenceReason get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all absence's reasons sorted with specified sort.
     * List is sorted by absence's reason.
     *
     * @return list of all absence's reasons.
     */
    @Override
    public List<AbsenceReason> getAll() {
        return crudRepository.findAll(SORT_REASON);
    }
}
