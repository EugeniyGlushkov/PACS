package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.AbsenceReason;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for absence reason.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudAbsenceReasonRepository extends JpaRepository<AbsenceReason, Integer> {
    /**
     * Saves or updates a given absence reason.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param reason an absence reason to save.
     * @return the saved absence reason.
     */
    @Transactional
    @Override
    AbsenceReason save(AbsenceReason reason);

    /**
     * Deletes a absence reason by given id.
     *
     * @param id id of the absence reason that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM AbsenceReason ar WHERE ar.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with absence reason by given id inside.
     *
     * @param integer id of the absence reason to return.
     * @return a container with absence reason by given id inside.
     */
    @Override
    Optional<AbsenceReason> findById(Integer integer);

    /**
     * Returns all absence reasons sorted with the given sort.
     *
     * @param sort sort for absence reasons list.
     * @return list of all absence reasons.
     */
    @Override
    List<AbsenceReason> findAll(Sort sort);
}
