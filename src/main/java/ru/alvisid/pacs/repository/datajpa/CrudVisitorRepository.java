package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Visitor;

import java.util.List;

/**
 * JpaRepository interface for visitor.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface CrudVisitorRepository extends JpaRepository<Visitor, Long> {
    /**
     * Saves a given visitor.
     *
     * @param visitor a visitor to save.
     * @return the saved visitor.
     */
    @Override
    @Transactional
    Visitor save(Visitor visitor);

    /**
     * Deletes a visitor by given id.
     *
     * @param id id of the visitor that must be deleted.
     * @return amount of the deleted entities.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Visitor v WHERE v.id =: id")
    int delete(@Param("id") long id);

    /**
     * Returns a visitor by given id.
     *
     * @param aLong id of the visitor to return.
     * @return a visitor by given id.
     */
    @Override
    Visitor findOne(Long aLong);

    /**
     * Returns all visitors sorted with given sort.
     *
     * @param sort sort fo visitors list.
     * @return list of all visitors.
     */
    @Override
    List<Visitor> findAll(Sort sort);
}
