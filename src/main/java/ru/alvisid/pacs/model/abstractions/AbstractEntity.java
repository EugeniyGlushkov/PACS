package ru.alvisid.pacs.model.abstractions;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Abstraction for entities wich have id and description.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractId {
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
     * Returns the description of the entity.
     *
     * @return the description of the entity.
     */
    public String getDescription() {
        return description;
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
     * Initializes a newly created object with null id and description values.
     *
     * @see AbstractEntity#AbstractEntity(String)
     * @see AbstractEntity#AbstractEntity(Integer, String)
     */
    public AbstractEntity() {
    }

    /**
     * Constructs a new <b>AbstractEntity</b> object and sets description. Id will be null.
     *
     * @param description the description of the entity.
     * @see AbstractEntity#AbstractEntity()
     * @see AbstractEntity#AbstractEntity(Integer, String)
     */
    public AbstractEntity(String description) {
        this(null, description);
    }

    /**
     * Constructs a new <b>AbstractEntity</b> object and sets description, id.
     *
     * @param id          the specifiec id.
     * @param description the description of the entity.
     * @see AbstractEntity#AbstractEntity()
     * @see AbstractEntity#AbstractEntity(String)
     */
    public AbstractEntity(Integer id, String description) {
        super(id);
        this.description = description;
    }

    /**
     * Returns a String object representing this object.
     *
     * @return the String object representing this object.
     */
    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')",
                getClass().getName(), id, description);
    }
}
