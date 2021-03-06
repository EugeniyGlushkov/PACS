package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.DayOff;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for day off.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudDayOffRepository extends JpaRepository <DayOff, Integer> {
    /**
     * Saves or updates a given day off.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param dayOff a day off to save.
     * @return the saved day off.
     */
    @Transactional
    @Override
    DayOff save(DayOff dayOff);

    /**
     * Deletes a day off by given id.
     *
     * @param id id of the day off that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM DayOff do WHERE do.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with a day off by given id inside.
     *
     * @param integer id of the day off to return.
     * @return a container with a day off by given id inside.
     */
    @Override
    Optional <DayOff> findById(Integer integer);

    /**
     * Returns all days off sorted with a given sort.
     *
     * @param sort sort for days off list.
     * @return list of all days off.
     */
    @Override
    List <DayOff> findAll(Sort sort);

    /**
     * Returns the list with all objects by a specified <b>department's id</b>
     * sorted with a given sort.
     *
     * @param deptId the specified <b>department's id</b>.
     * @param sort sort for days off list.
     * @return the list with all objects by a specified <b>department's id</b>.
     */
    @Query("SELECT do FROM DayOff do WHERE do.department.id=:deptId")
    List <DayOff> findAllByDepartmentId(@Param("deptId") int deptId, Sort sort);

    /**
     * Returns the list with all objects by a specified <b>date</b>
     * sorted with a given sort.
     *
     * @param date the specified <b>date</b>.
     * @param sort sort for days off list.
     * @return the list with all objects by a specified <b>date</b>.
     */
    @Query("SELECT do FROM DayOff do WHERE do.dateOff=:date")
    List <DayOff> getAllByDate(@Param("date") LocalDate date, Sort sort);
}
