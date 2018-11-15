package ru.alvisid.pacs.model;

public class PointAction extends AbstractId{
    private ControlPoint controlPoint;
    private ActionType actionType;

    public ControlPoint getControlPoint() {
        return controlPoint;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setControlPoint(ControlPoint controlPoint) {
        this.controlPoint = controlPoint;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public PointAction() {
    }

    public PointAction(ControlPoint controlPoint, ActionType actionType) {
        this(null, controlPoint, actionType);
    }

    public PointAction(Integer id, ControlPoint controlPoint, ActionType actionType) {
        super(id);
        this.controlPoint = controlPoint;
        this.actionType = actionType;
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

        PointAction that = (PointAction) o;

        if (!controlPoint.equals(that.controlPoint)) {
            return false;
        }

        return actionType.equals(that.actionType);
    }

    @Override
    public String toString() {
        return "PointAction{" +
                "id=" + id +
                ", controlPoint=" + controlPoint +
                ", actionType=" + actionType +
                '}';
    }
}
