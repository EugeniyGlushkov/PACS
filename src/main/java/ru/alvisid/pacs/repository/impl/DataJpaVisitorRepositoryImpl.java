package ru.alvisid.pacs.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.pacs.model.Visitor;
import ru.alvisid.pacs.repository.VisitorRepository;
import ru.alvisid.pacs.repository.datajpa.CrudVisitorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

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
    private static final Sort SORT_ENTERTIME_LNAME_FNAME_SNAME =
            new Sort(Sort.Direction.DESC, "enterTime", "lastName", "firstName", "secondName");

    /**
     * An interface for visitor repository which extends JpaRepository.
     */
    private final CrudVisitorRepository crudRepository;

    /**
     * Constructs a new DataJpaVisitorRepositoryImpl with the specified CrudVisitorRepository.
     *
     * @param crudRepository the specified CrudVisitorRepository.
     */
    @Autowired
    public DataJpaVisitorRepositoryImpl(CrudVisitorRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given visitor.
     * Returns null if there aren't updating value in the data base.
     *
     * @param visitor a visitor to save.
     * @return the saved visitor.
     * null - if there aren't updating value in the data base.
     */
    @Override
    public Visitor save(Visitor visitor) {
        if (visitor.isNew() || !Objects.isNull(get(visitor.getId()))) {
            return crudRepository.save(visitor);
        }

        return null;
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
     * @see DataJpaVisitorRepositoryImpl#SORT_ENTERTIME_LNAME_FNAME_SNAME
     */
    @Override
    public List <Visitor> getAll() {
        return crudRepository.findAll(SORT_ENTERTIME_LNAME_FNAME_SNAME);
    }

    /**
     * Returns a visitor by given temporary number.
     *
     * @param tempNum the specified temporary number.
     * @return the visitor by given temporary number.
     */
    @Override
    public Visitor getByTempNum(String tempNum) {
        return crudRepository.getByTempNum(tempNum);
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
    public List<Visitor> getAllByEnterTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return crudRepository.getAllByEnterTimeBetween(startTime, endTime, SORT_ENTERTIME_LNAME_FNAME_SNAME);
    }
}
