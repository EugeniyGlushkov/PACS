package ru.alvisid.pacs.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * An action which able to be done at the current control point.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "point_actions", uniqueConstraints =
@UniqueConstraint(columnNames = {"controlpoint_id", "acttype_id"}, name = "conpoint_acttype_idx"))
public class PointAction extends AbstractId {
    /**
     * The control point where the action able to be done.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controlpoint_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ControlPoint controlPoint;

    /**
     * The action which able to be done at the control point.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acttype_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ActionType actionType;

    /**
     * Returns the control point value.
     *
     * @return the control point value.
     */
    public ControlPoint getControlPoint() {
        return controlPoint;
    }

    /**
     * Returns the action value.
     *
     * @return the action value.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Sets specified control point.
     *
     * @param controlPoint the specified control point.
     */
    public void setControlPoint(ControlPoint controlPoint) {
        this.controlPoint = controlPoint;
    }

    /**
     * Sets the specified action.
     *
     * @param actionType the specified action type.
     */
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    /**
     * Initializes a newly created object with null id, controlPoint and actionType.
     *
     * @see PointAction#PointAction(ControlPoint, ActionType)
     * @see PointAction#PointAction(Integer, ControlPoint, ActionType)
     */
    public PointAction() {
    }

    /**
     * Constructs a new <b>PointAction</b> object and sets the controlPoint and actionType.
     *
     * @param controlPoint the specified control point.
     * @param actionType   the specified action type.
     * @see PointAction#PointAction()
     * @see PointAction#PointAction(Integer, ControlPoint, ActionType)
     */
    public PointAction(ControlPoint controlPoint, ActionType actionType) {
        this(null, controlPoint, actionType);
    }

    /**
     * Constructs a new <b>PointAction</b> object and sets the id, controlPoint and actionType.
     *
     * @param id           the specifiec id.
     * @param controlPoint the specified control point.
     * @param actionType   the specified action type.
     * @see PointAction#PointAction()
     * @see PointAction#PointAction(ControlPoint, ActionType)
     */
    public PointAction(Integer id, ControlPoint controlPoint, ActionType actionType) {
        super(id);
        this.controlPoint = controlPoint;
        this.actionType = actionType;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>PointAction</b> object
     * that contains the controlPoint and actionType values as this object
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

        PointAction that = (PointAction) o;

        if (!controlPoint.equals(that.controlPoint)) {
            return false;
        }

        return actionType.equals(that.actionType);
    }

    /**
     * Returns a String object representing this <b>PointAction</b> object.
     *
     * @return the String object representing this <b>PointAction</b> object.
     */
    @Override
    public String toString() {
        return "PointAction{" +
                "id=" + id +
                ", controlPoint=" + controlPoint +
                ", actionType=" + actionType +
                '}';
    }
}
