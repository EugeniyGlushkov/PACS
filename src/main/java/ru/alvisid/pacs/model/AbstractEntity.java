package ru.alvisid.pacs.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Abstraction for entities wich have id and description.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity {
    /**
     * The cpecifiec identifier for each entity in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    /**
     * The description of the entity.
     * Min value is 2 character.
     * Must be non null and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 2)
    @Column(name = "description", nullable = false)
    protected String description;

    /**
     * Gets the specifiec id.
     *
     * @return the specifiec id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the description of the entity.
     *
     * @return the description of the entity.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the specifiec id.
     *
     * @param id the specifiec id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Sets the description of the entity.
     *
     * @param description the description of the entity.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returnes {@code true} if id is null.
     *
     * @return {@code true} if id is null.
     */
    public boolean isNew() {
        return Objects.isNull(id);
    }

    /**
     * Initializes a newly created object with null id and description values.
     *
     * @see AbstractEntity#AbstractEntity(String)
     * @see AbstractEntity#AbstractEntity(Integer, String)
     */
    public AbstractEntity() {
    }

    /**
     * Constructs entity and sets description. Id will be null.
     *
     * @param description the description of the entity.
     * @see AbstractEntity#AbstractEntity()
     * @see AbstractEntity#AbstractEntity(Integer, String)
     */
    public AbstractEntity(String description) {
        this.description = description;
    }

    /**
     * Constructs entity and sets description, id.
     *
     * @param id the specifiec id.
     * @param description the description of the entity.
     * @see AbstractEntity#AbstractEntity()
     * @see AbstractEntity#AbstractEntity(String)
     */
    public AbstractEntity(Integer id, String description) {
        this(description);
        this.id = id;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an AbstractEntity object or its heir
     * that contains the same id and description values as this object.
     *
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof AbstractEntity) {
            AbstractEntity that = (AbstractEntity) o;

            if (id != null ? !id.equals(that.id) : that.id != null) {
                return false;
            }

            return description.equals(that.description);
        }

        return false;
    }

    /**
     * Returns a hash code for this Entity.
     *
     * @return the hash code for this Entity.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns a String object representing this entity's value.
     *
     * @return the String object representing this entity's value.
     */
    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')",
                getClass().getName(), id, description);
    }
}
