package ru.alvisid.pacs.model;

import ru.alvisid.pacs.model.abstractions.AbstractEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ControlPoint extends AbstractEntity {
    /**
     * An identifier of the control point.
     * Can has letter or numeric characters
     * Min value is 2 characters, max value is 50 characters.
     * Must be non null, unique and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "serial_id", nullable = false, unique = true)
    private String serialCode;

    /**
     * Returns the identifier of the control point.
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
     * @see ControlPoint#ControlPoint(ControlPoint)
     */
    public ControlPoint() {
    }

    /**
     * Constructs a <b>ControlPoint</b> object with specified description, serialCode and null id values.
     *
     * @param serialCode  the identifier of the control point.
     * @param description the control point's description.
     * @see ControlPoint#ControlPoint()
     * @see ControlPoint#ControlPoint(Integer, String, String)
     * @see ControlPoint#ControlPoint(ControlPoint)
     */
    public ControlPoint(String serialCode, String description) {
        this(null, serialCode, description);
    }

    /**
     * Constructs a <b>ControlPoint</b> object with specified id, description and serialCode values.
     *
     * @param id          the specifiec identifier.
     * @param serialCode  the identifier of the control point.
     * @param description the control point's description.
     * @see ControlPoint#ControlPoint()
     * @see ControlPoint#ControlPoint(String, String)
     * @see ControlPoint#ControlPoint(ControlPoint)
     */
    public ControlPoint(Integer id, String serialCode, String description) {
        super(id, description);
        this.serialCode = serialCode;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param controlPoint the specified object to copying.
     * @see ControlPoint#ControlPoint()
     * @see ControlPoint#ControlPoint(String, String)
     * @see ControlPoint#ControlPoint(Integer, String, String)
     */
    public ControlPoint(ControlPoint controlPoint) {
        this(controlPoint.getId(),
                controlPoint.getSerialCode(),
                controlPoint.getDescription());
    }

    /**
     * Returns a String object representing this <b>ControlPoint</b> object.
     *
     * @return the String object representing this <b>ControlPoint</b> object.
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
