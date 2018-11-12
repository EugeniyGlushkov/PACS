package ru.alvisid.pacs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A specifiec position.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "positions")
public class Position extends AbstractEntity {
    /**
     * The text identifier of the position.
     * Min value is 2 characters, max value is 100 characters.
     * Must be non null, unique and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "position", nullable = false, unique = true)
    private String position;

    /**
     * Returnes the position string value.
     *
     * @return the position string value.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position string value.
     *
     * @param position the specifiec position string value.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Initializes a newly created <b>Position</b> object with null position value and null fiels of the superclass.
     *
     * @see Position#Position(String, String)
     * @see Position#Position(Integer, String, String)
     */
    public Position() {
        super();
    }

    /**
     * Constructs a <b>Position</b> object with specified desciption, position and null id value.
     *
     * @param description the position's description.
     * @param position    the text identifier of the position.
     * @see Position#Position()
     * @see Position#Position(Integer, String, String)
     */
    public Position(String description, String position) {
        super(description);
        this.position = position;
    }

    /**
     * Constructs a <b>Position</b> object with specified id, desciption and position values.
     *
     * @param id          the specifiec identifier.
     * @param description the position's description.
     * @param position    the text identifier of the position.
     * @see Position#Position()
     * @see Position#Position(String, String)
     */
    public Position(Integer id, String description, String position) {
        super(id, description);
        this.position = position;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>Position</b> object that contains the same position value as this object,
     * and superclass is equals the specified object.
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
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

        Position position = (Position) o;

        return position.equals(position.position);
    }

    /**
     * Returns a String object representing this <b>Position</b>'s object.
     *
     * @return the String object representing this <b>Position</b>'s object.
     */
    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
