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
     * The cpecifiec identifier for each entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    /**
     * The description of the entity.
     */
    @NotBlank
    @Size(min = 2)
    @Column(name = "description", nullable = false)
    protected String description;

    /**
     * A getter for the cpecifiec id.
     *
     * @return the value of the cpecifiec id.
     */
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNew() {
        return Objects.isNull(id);
    }

    public AbstractEntity() {
    }

    public AbstractEntity(String description) {
        this.description = description;
    }

    public AbstractEntity(Integer id, String description) {
        this(description);
        this.id = id;
    }

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

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')",
                getClass().getName(), id, description);
    }
}
