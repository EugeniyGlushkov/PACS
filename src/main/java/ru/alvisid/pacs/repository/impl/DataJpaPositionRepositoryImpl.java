package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Position;
import ru.alvisid.pacs.repository.PositionRepository;
import ru.alvisid.pacs.repository.datajpa.CrudPositionRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the PositionRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaPositionRepositoryImpl implements PositionRepository {
    /**
     * Sort by position.
     */
    private static final Sort SORT_POSITION = new Sort(Sort.Direction.ASC, "position");

    /**
     * An interface for position repository which extends JpaRepository.
     */
    private final CrudPositionRepository crudRepository;

    /**
     * Constructs a new DataJpaPositionRepositoryImpl with the specified CrudPositionRepository.
     *
     * @param crudRepository the specified CrudPositionRepository.
     */
    @Autowired
    public DataJpaPositionRepositoryImpl(CrudPositionRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given position.
     * Returns null if there aren't updating value in the data base.
     *
     * @param position a position to save.
     * @return the saved position.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Position save(Position position) {
        if (position.isNew() || !Objects.isNull(get(position.getId()))){
            return crudRepository.save(position);
        }

        return null;
    }

    /**
     * Deletes a position by given id.
     *
     * @param id the specifiec id of the deleted position.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a position by given id.
     *
     * @param id the specifiec id of position to get.
     * @return a position by the given id,
     * null - if there aren't position with cpecifiec id in the DB.
     */
    @Override
    public Position get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all positions sorted with specified sort.
     * List is sorted by position.
     *
     * @return list of all positions.
     */
    @Override
    public List<Position> getAll() {
        return crudRepository.findAll(SORT_POSITION);
    }
}
