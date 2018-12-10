package ru.alvisid.pacs.model;

import ru.alvisid.pacs.model.abstractions.AbstractEntity;

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
     * Returns the position string value.
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
     * Initializes a newly created <b>Position</b> object with null position value
     * and null fiels of the superclass.
     *
     * @see Position#Position(String, String)
     * @see Position#Position(Integer, String, String)
     * @see Position#Position(Position)
     */
    public Position() {
    }

    /**
     * Constructs a <b>Position</b> object with specified desciption, position and null id values.
     *
     * @param description the position's description.
     * @param position    the text identifier of the position.
     * @see Position#Position()
     * @see Position#Position(Integer, String, String)
     * @see Position#Position(Position)
     */
    public Position(String description, String position) {
        this(null, description, position);
    }

    /**
     * Constructs a <b>Position</b> object with specified id, description and position values.
     *
     * @param id          the specifiec identifier.
     * @param description the position's description.
     * @param position    the text identifier of the position.
     * @see Position#Position()
     * @see Position#Position(String, String)
     * @see Position#Position(Position)
     */
    public Position(Integer id, String description, String position) {
        super(id, description);
        this.position = position;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param position the specified object to copying.
     * @see Position#Position()
     * @see Position#Position(String, String)
     * @see Position#Position(Integer, String, String)
     */
    public Position(Position position) {
        this(position.getId(),
                position.getDescription(),
                position.getPosition());
    }

    /**
     * Returns a String object representing this <b>Position</b> object.
     *
     * @return the String object representing this <b>Position</b> object.
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
