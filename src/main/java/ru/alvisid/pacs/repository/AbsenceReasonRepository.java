package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.AbsenceReason;

import java.util.List;

/**
 * The interface with a generalized functional for absence reason's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface AbsenceReasonRepository {
    /**
     * Saves or updates a given absence reason.
     *
     * @param reason the absence reason to save or update.
     * @return a saved or update absence reason,
     * null - if there aren't updated absence reason in the repository.
     */
    AbsenceReason save(AbsenceReason reason);

    /**
     * Deletes the absence reason with specifiec id.
     *
     * @param id the specifiec id of a deleted absence reason.
     * @return true - if an absence reason with the specifiec id is deleted,
     * false - if there aren't the absence reason with the cpecifiec id in the DB.
     */
    boolean delete(int id);

    /**
     * Returns a absence reason with the cpecifiec id.
     *
     * @param id the specifiec id of absence reason to get.
     * @return a absence reason with the cpecifiec id,
     * null - if there aren't absence reason with the cpecifiec id in the DB.
     */
    AbsenceReason get(int id);

    /**
     * Returns the list with all absence reasons.
     *
     * @return the list with all absence reasons.
     */
    List<AbsenceReason> getAll();
}
