package ru.alvisid.pacs.model;

import java.util.Objects;

public abstract class AbstractEntity {
    protected Integer id;
    protected String description;

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
}
