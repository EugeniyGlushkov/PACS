package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.ActionType;
import ru.alvisid.pacs.repository.ActionTypeRepository;
import ru.alvisid.pacs.repository.datajpa.CrudActionTypeRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the ActionTypeRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaActionTypeRepositoryImpl implements ActionTypeRepository {
    /**
     * Sort by type field of the action type's.
     */
    private static final Sort SORT_TYPE = new Sort(Sort.Direction.ASC, "type");

    /**
     * An interface for action type which extends JpaRepository.
     */
    private final CrudActionTypeRepository crudRepository;

    /**
     * Constructs a new DataJpaActionTypeRepositoryImpl with the specified CrudActionTypeRepository.
     *
     * @param crudRepository the specified CrudActionTypeRepository.
     */
    @Autowired
    public DataJpaActionTypeRepositoryImpl(CrudActionTypeRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given action type.
     * Returns null if there aren't updating value in the data base.
     *
     * @param actionType an action's type to save.
     * @return the saved action's type.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public ActionType save(ActionType actionType) {
        if (actionType.isNew() || !Objects.isNull(crudRepository.findById(actionType.getId()))) {
            return crudRepository.save(actionType);
        }

        return null;
    }

    /**
     * Deletes an action's type by given id.
     *
     * @param id the specifiec id of the deleted action's type.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns an action's type by given id.
     *
     * @param id the specifiec id of action's type to get.
     * @return an action's type by the given id,
     * null - if there aren't action's type with cpecifiec id in the DB.
     */
    @Override
    public ActionType get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all action's types sorted with specified sort.
     * List is sorted by type field of the action's type.
     *
     * @return list of all action's types.
     */
    @Override
    public List <ActionType> getAll() {
        return crudRepository.findAll(SORT_TYPE);
    }
}
