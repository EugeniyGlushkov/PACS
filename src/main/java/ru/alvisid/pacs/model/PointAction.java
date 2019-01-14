package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PointAction extends AbstractId {
    /**
     * The control point where the action able to be done.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "controlpoint_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ControlPoint controlPoint;

    /**
     * The action which able to be done at the control point.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "acttype_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
     * @see PointAction#PointAction(PointAction)
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
     * @see PointAction#PointAction(PointAction)
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
     * @see PointAction#PointAction(PointAction)
     */
    public PointAction(Integer id, ControlPoint controlPoint, ActionType actionType) {
        super(id);
        this.controlPoint = controlPoint;
        this.actionType = actionType;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param pointAction the specified object to copying.
     * @see PointAction#PointAction()
     * @see PointAction#PointAction(ControlPoint, ActionType)
     * @see PointAction#PointAction(Integer, ControlPoint, ActionType)
     */
    public PointAction(PointAction pointAction) {
        this(pointAction.getId(),
                pointAction.getControlPoint(),
                pointAction.getActionType());
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
