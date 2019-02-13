package ru.alvisid.pacs.service;

import ru.alvisid.pacs.model.DayOff;
import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * The specific functional for the service of the 's day off.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface DayOffService extends TypicalService <DayOff> {
    /**
     * Creates and saves a given department's day off in the data base
     * with inserted department.
     *
     * @param dayOff the department's day off to create.
     * @param deptId the department's id to insert.
     * @return the created object.
     */
    DayOff create(DayOff dayOff, int deptId);

    /**
     * Updates an existing in the data base department's day off
     * with inserted department.
     *
     * @param dayOff the department's day off to create.
     * @param deptId the department's id to insert.
     * @throws NotFoundException if there aren't updated object in the data base.
     */
    void update(DayOff dayOff, int deptId) throws NotFoundException;

    /**
     * Returns the list with all objects by a specified <b>department's id</b>.
     *
     * @param deptId the specified <b>department's id</b>.
     * @return the list with all objects by a specified <b>department's id</b>.
     */
    public List <DayOff> getAllByDeptId(int deptId);

    /**
     * Returns the list with all objects by a specified <b>date</b>.
     *
     * @param date the specified <b>date</b>.
     * @return the list with all objects by a specified <b>date</b>.
     */
    public List <DayOff> getAllByDate(LocalDate date);
}
