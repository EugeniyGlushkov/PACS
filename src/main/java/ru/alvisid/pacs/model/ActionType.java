package ru.alvisid.pacs.model;

import ru.alvisid.pacs.model.abstractions.AbstractEntity;

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
    @Column(name = "type", nullable = false, unique = true)
    private String type;

    /**
     * Returns the {@code type} value.
     *
     * @return the {@code type} value.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the {@code type} value.
     *
     * @param type the $@code type} value.
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
     * @see ActionType#ActionType(ActionType)
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
     * @see ActionType#ActionType(ActionType)
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
     * @see ActionType#ActionType(ActionType)
     */
    public ActionType(Integer id, String description, String type) {
        super(id, description);
        this.type = type;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param actionType the specified object to copying.
     * @see ActionType#ActionType()
     * @see ActionType#ActionType(String, String)
     * @see ActionType#ActionType(Integer, String, String)
     */
    public ActionType(ActionType actionType) {
        this(actionType.getId(),
                actionType.getDescription(),
                actionType.getType());
    }

    /**
     * Returns a String object representing this <b>ActionType</b> object.
     *
     * @return the String object representing this <b>ActionType</b> object.
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
