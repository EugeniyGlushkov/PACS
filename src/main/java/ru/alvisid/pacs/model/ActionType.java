package ru.alvisid.pacs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A type of an action which able to fulfilled at some control point.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see ControlPoint
 */
@Entity
@Table(name = "action_types")
public class ActionType extends AbstractEntity {
    /**
     * Title of the action.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "action", nullable = false, unique = true)
    private String type;

    /**
     * Returnes the ${@code type} value.
     *
     * @return the ${@code type} value.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the ${@code type} value.
     *
     * @param type the ${@code type} value.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Initializes a newly created <b>ActionType</b> object with null type value
     * and null fiels of the superclass.
     *
     * @see ActionType#ActionType(String, String)
     * @see ActionType#ActionType(Integer, String, String)
     */
    public ActionType() {
    }

    /**
     * Constructs a <b>ActionType</b> object with specified description, type and null id values.
     *
     * @param description the action type's description.
     * @param type        the identifier of the action type.
     * @see ActionType#ActionType()
     * @see ActionType#ActionType(Integer, String, String)
     */
    public ActionType(String description, String type) {
        this(null, description, type);
    }

    /**
     * Constructs a <b>ActionType</b> object with specified id, description and type values.
     *
     * @param id          the specifiec identifier.
     * @param description the action type's description.
     * @param type        the identifier of the action type.
     * @see ActionType#ActionType()
     * @see ActionType#ActionType(String, String)
     */
    public ActionType(Integer id, String description, String type) {
        super(id, description);
        this.type = type;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>ActionType</b> object that contains the same type value as this object,
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

        ActionType that = (ActionType) o;

        return type.equals(that.type);
    }

    /**
     * Returns a String object representing this <b>ActionType</b>'s object.
     *
     * @return the String object representing this <b>ActionType</b>'s object.
     */
    @Override
    public String toString() {
        return "ActionType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
