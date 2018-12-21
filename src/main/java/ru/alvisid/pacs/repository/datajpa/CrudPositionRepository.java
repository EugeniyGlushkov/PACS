package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Position;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for position.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Transactional(readOnly = true)
public interface CrudPositionRepository extends JpaRepository <Position, Integer> {
    /**
     * Saves or updates a given position.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param position a position to save.
     * @return the saved position.
     */
    @Transactional
    @Override
    Position save(Position position);

    /**
     * Deletes a position by given id.
     *
     * @param id id of the position that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Position p WHERE p.id=:id")
    int delete(@Param("id") int id);

    /**
     * Returns a container with a position by given id inside.
     *
     * @param integer id of the position to return.
     * @return a container with a position by given id inside.
     */
    @Override
    Optional<Position> findById(Integer integer);

    /**
     * Returns all positions sorted with a given sort.
     *
     * @param sort sort for positions list.
     * @return list of all positions.
     */
    @Override
    List<Position> findAll(Sort sort);
}
