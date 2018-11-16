package ru.alvisid.pacs.model;

import java.time.LocalDateTime;

public class Action extends AbstractId {
    private Employee employee;
    private PointAction pointAction;
    private LocalDateTime actionTime;

    public Employee getEmployee() {
        return employee;
    }

    public PointAction getPointAction() {
        return pointAction;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setPointAction(PointAction pointAction) {
        this.pointAction = pointAction;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public Action() {
    }

    public Action(Employee employee, PointAction pointAction, LocalDateTime actionTime) {
        this(null, employee, pointAction, actionTime);
    }

    public Action(Integer id, Employee employee, PointAction pointAction, LocalDateTime actionTime) {
        super(id);
        this.employee = employee;
        this.pointAction = pointAction;
        this.actionTime = actionTime;
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

        Action action = (Action) o;

        if (!employee.equals(action.employee)) {
            return false;
        }

        if (!pointAction.equals(action.pointAction)) {
            return false;
        }

        return actionTime.equals(action.actionTime);
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", employee=" + employee +
                ", pointAction=" + pointAction +
                ", actionTime=" + actionTime +
                '}';
    }
}
