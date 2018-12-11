package ru.alvisid.pacs.model.abstractions;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Abstraction for every entities which have id.
 * Id values must be generated different sequence generators.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractId implements HasId {
    /**
     * The cpecifiec identifier for each entity in a database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    /**
     * Returns the specifiec id.
     *
     * @return the specifiec id.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the specifiec id.
     *
     * @param id the specifiec id.
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns {@code true} if id is null.
     *
     * @return {@code true} if id is null.
     */
    public boolean isNew() {
        return Objects.isNull(id);
    }

    /**
     * Initializes a newly created object with null id value.
     *
     * @see AbstractId#AbstractId(Integer)
     */
    public AbstractId() {
    }

    /**
     * Constructs a new <b>AbstractId</b> object and sets id.
     *
     * @param id the specifiec id.
     */
    public AbstractId(Integer id) {
        this.id = id;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is the same class as this object,
     * and that contains the same id value as this object.
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }

        AbstractId that = (AbstractId) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return the hash code for this object.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns a String object representing this object.
     *
     * @return the String object representing this object.
     */
    @Override
    public String toString() {
        return String.format("Entity %s (%s)",
                getClass().getName(), id);
    }
}
