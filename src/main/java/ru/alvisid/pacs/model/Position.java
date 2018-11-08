package ru.alvisid.pacs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "positions")
public class Position extends AbstractEntity {
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "position", nullable = false, unique = true)
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Position() {
        super();
    }

    public Position(String description, String position) {
        super(description);
        this.position = position;
    }

    public Position(Integer id, String description, String position) {
        super(id, description);
        this.position = position;
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

        Position position1 = (Position) o;

        return position.equals(position1.position);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
