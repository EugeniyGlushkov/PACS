package ru.alvisid.pacs.repository.datajpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Visitor;
import ru.alvisid.pacs.repository.VisitorRepository;
import ru.alvisid.pacs.repository.datajpa.CrudVisitorRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DataJpa implementation of the VisitorRepository.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Repository
public class DataJpaVisitorRepositoryImpl implements VisitorRepository {
    /**
     * Sort by last name, first name, second name.
     */
    private static final Sort SORT_LNAME_FNAME_SNAME =
            new Sort(Sort.Direction.ASC, "lastName", "firstName", "secondName");

    /**
     * An interface for visitor which extends JpaRepository.
     */
    @Autowired
    CrudVisitorRepository crudRepository;

    /**
     * Saves a given visitor.
     *
     * @param visitor a visitor to save.
     * @return the saved visitor.
     */
    @Override
    public Visitor save(Visitor visitor) {
        return crudRepository.save(visitor);
    }

    /**
     * Deletes a visitor by given id.
     *
     * @param id id of the visitor that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a visitor by given id.
     *
     * @param id id of the visitor to return.
     * @return a visitor by given id.
     */
    @Override
    public Visitor get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all visitors sorted with cpecifiec sort.
     *
     * @return list of all visitors.
     * @see DataJpaVisitorRepositoryImpl#SORT_LNAME_FNAME_SNAME
     */
    @Override
    public List<Visitor> getAll() {
        return crudRepository.findAll(SORT_LNAME_FNAME_SNAME);
    }

    /**
     * Returns a visitors list which contains visitors
     * with enter time in a specified time interval.
     *
     * @param startTime the start of the time interval.
     * @param endTime   the end of the time interval.
     * @return the visitors list which contains visitors with enter time in a specified time interval.
     */
    @Override
    public List <Visitor> getAllByEnterTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return crudRepository.getAllByEnterTimeBetween(startTime, endTime, SORT_LNAME_FNAME_SNAME);
    }
}
