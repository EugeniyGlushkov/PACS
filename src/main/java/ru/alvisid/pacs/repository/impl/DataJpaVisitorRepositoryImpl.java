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
    private static final Sort SORT_LNAME_FNAME_SNAME =
            new Sort(Sort.Direction.ASC, "lastName", "firstName", "secondName");

    /**
     * An interface for visitor which extends JpaRepository.
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
        if (visitor.isNew() || !Objects.isNull(crudRepository.findById(visitor.getId()))) {
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
     * @see DataJpaVisitorRepositoryImpl#SORT_LNAME_FNAME_SNAME
     */
    @Override
    public List <Visitor> getAll() {
        return crudRepository.findAll(SORT_LNAME_FNAME_SNAME);
    }

    /**
     * Returns a visitors list which contains visitors with specified visit's date.
     *
     * @param localDate the visit's date.
     * @return the visitors list which contains visitors with specified visit's date.
     */
    @Override
    public List <Visitor> getAllByVisitDate(LocalDate localDate) {
        LocalDateTime startTime = LocalDateTime.of(localDate, LocalTime.of(0, 0));
        LocalDateTime endTime = startTime.plusDays(1);

        return crudRepository.getAllByEnterTimeBetween(startTime, endTime, SORT_LNAME_FNAME_SNAME);
    }
}
