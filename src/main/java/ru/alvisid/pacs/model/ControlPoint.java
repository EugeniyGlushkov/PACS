package ru.alvisid.pacs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A control point which represents phisical access point.
 * Persons who have espesial rights can do specifiec operation
 * with the point.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "control_points")
public class ControlPoint extends AbstractEntity {
    /**
     * An identifier of the control point.
     * Can has letter or numeric characters
     * Min value is 2 characters, max value is 50 characters.
     * Must be non null, unique and has least one non space symbol.
     */
    @NotBlank
    @Size(max = 50)
    @Column(name = "serial_id", nullable = false, unique = true)
    private String serialCode;

    /**
     * Returnes the identifier of the control point.
     *
     * @return the identifier of the control point.
     */
    public String getSerialCode() {
        return serialCode;
    }

    /**
     * Sets the identifier of the control point.
     *
     * @param serialCode the identifier of the control point.
     */
    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    /**
     * Initializes a newly created <b>ControlPoint</b> object with null position value
     * and null fiels of the superclass.
     *
     * @see ControlPoint#ControlPoint(String, String)
     * @see ControlPoint#ControlPoint(Integer, String, String)
     */
    public ControlPoint() {
    }

    /**
     * Constructs a <b>ControlPoint</b> object with specified description, serialCode and null id values.
     *
     * @param description the control point's description.
     * @param serialCode  the identifier of the control point.
     * @see ControlPoint#ControlPoint()
     * @see ControlPoint#ControlPoint(Integer, String, String)
     */
    public ControlPoint(String description, String serialCode) {
        super(description);
        this.serialCode = serialCode;
    }

    /**
     * Constructs a <b>ControlPoint</b> object with specified id, description and serialCode values.
     *
     * @param id          the specifiec identifier.
     * @param description the control point's description.
     * @param serialCode  the identifier of the control point.
     * @see ControlPoint#ControlPoint()
     * @see ControlPoint#ControlPoint(String, String)
     */
    public ControlPoint(Integer id, String description, String serialCode) {
        super(id, description);
        this.serialCode = serialCode;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>ControlPoint</b> object that contains the same serialCode value as this object,
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

        ControlPoint that = (ControlPoint) o;

        return serialCode.equals(that.serialCode);
    }

    /**
     * Returns a String object representing this <b>ControlPoint</b>'s object.
     *
     * @return the String object representing this <b>ControlPoint</b>'s object.
     */
    @Override
    public String toString() {
        return "ControlPoint{" +
                "id=" + id +
                ", serialCode='" + serialCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
