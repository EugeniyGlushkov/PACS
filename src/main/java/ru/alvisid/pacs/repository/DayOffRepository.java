package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.DayOff;

/**
 * The generalized functional for day's off repository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DayOffRepository extends TypicalRepository<DayOff> {
    /**
     * Saves or updates a given object with inserted parameter.
     *
     * @param dayOff the object to save or update.
     * @param deptId department's id, the department will be inserted to the object's
     *               {@code department} field.
     * @return a saved or update object,
     * null - if there aren't updated object in the data base.
     */
    DayOff save(DayOff dayOff, int deptId);
}
