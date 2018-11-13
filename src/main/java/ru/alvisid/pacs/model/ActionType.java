package ru.alvisid.pacs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "action_types")
public class ActionType extends AbstractEntity {
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "action", nullable = false, unique = true)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActionType() {
    }

    public ActionType(String description, String type) {
        super(description);
        this.type = type;
    }

    public ActionType(Integer id, String description, String type) {
        super(id, description);
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        ActionType that = (ActionType) o;

        return type.equals(that.type);
    }

    @Override
    public String toString() {
        return "ActionType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
