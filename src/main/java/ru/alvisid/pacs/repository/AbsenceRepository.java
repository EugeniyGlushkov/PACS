package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Absence;

import java.util.List;

/**
 * The generalized functional for absence's repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface AbsenceRepository {
    /**
     * Saves or updates a given absence.
     *
     * @param absence the absence to save or update.
     * @return a saved or update absence,
     * null - if there aren't updated absence in the repository.
     */
    Absence save(Absence absence);

    /**
     * Deletes the absence with specifiec id.
     *
     * @param id the specifiec id of a deleted absence.
     * @return true - if an absence with the specifiec id is deleted,
     * false - if there aren't the absence with the cpecifiec id in the DB.
     */
    boolean delete(int id);

    /**
     * Returns a absence with the cpecifiec id.
     *
     * @param id the specifiec id of absence to get.
     * @return an absence with the cpecifiec id,
     * null - if there aren't absence with the cpecifiec id in the DB.
     */
    Absence get(int id);

    /**
     * Returns the list with all absences.
     *
     * @return the list with all absences.
     */
    List<Absence> getAll();

    /**
     * Returns the list with all absences by employee's id.
     *
     * @param id the employee's id.
     * @return the list with all absences by employee's id.
     */
    List<Absence> getAllByEmplId(int id);

    /**
     * Returns the list with all absences by id of the employee's department.
     *
     * @param id the department's id.
     * @return the list with all absences by id of the employee's department.
     */
    List<Absence> getAllByEmplDeptId(int id);
}
