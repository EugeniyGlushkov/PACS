package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.ActionType;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for action's type.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudActionTypeRepository extends JpaRepository <ActionType, Integer> {
    /**
     * Saves or updates a given action's type.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param actionType an action's type to save.
     * @return the saved action's type.
     */
    @Transactional
    @Override
    ActionType save(ActionType actionType);

    /**
     * Deletes an action's type by given id.
     *
     * @param id the id of the action's type that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM ActionType at WHERE at.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with action's type by given id inside.
     *
     * @param integer id of the action's type to return.
     * @return a container with action's type by given id inside.
     */
    @Override
    Optional <ActionType> findById(Integer integer);

    /**
     * Returns all action's types sorted with a given sort.
     *
     * @param sort sort for action's types list.
     * @return list of all action's types.
     */
    @Override
    List <ActionType> findAll(Sort sort);
}
