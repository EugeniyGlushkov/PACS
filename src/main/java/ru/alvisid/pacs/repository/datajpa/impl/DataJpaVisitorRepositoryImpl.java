package ru.alvisid.pacs.repository.datajpa.impl;

import org.acs.domain.model.Visitor;
import org.acs.domain.repository.VisitorRepository;
import org.acs.domain.repository.datajpa.CrudVisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DataJpa implementation of the VisitorRepository.
 */
@Repository
public class DataJpaVisitorRepositoryImpl implements VisitorRepository {
    /**
     * Sort by first name, middle name, last name.
     */
    private static final Sort SORT_FNAME_MNAME_LNAME = new Sort(Sort.Direction.ASC, "firstName", "middleName", "lastName");

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
    public boolean delete(long id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returnes a visitor by given id.
     *
     * @param id id of the visitor to return.
     * @return a visitor by given id.
     */
    @Override
    public Visitor get(long id) {
        return crudRepository.findOne(id);
    }

    /**
     * Returnes all visitors sorted with cpecifiec sort.
     *
     * @return list of all visitors.
     * @see DataJpaVisitorRepositoryImpl#SORT_FNAME_MNAME_LNAME
     */
    @Override
    public List<Visitor> getAll() {
        return crudRepository.findAll(SORT_FNAME_MNAME_LNAME);
    }
}
