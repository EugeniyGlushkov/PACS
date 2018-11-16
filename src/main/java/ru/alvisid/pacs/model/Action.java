package ru.alvisid.pacs.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * An action which done by the employee in a certain point at a certain time.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "actions", uniqueConstraints =
@UniqueConstraint(columnNames = "emp_id, time", name = "act_emp_time_con"))
public class Action extends AbstractId {
    /**
     * The employee which did the action.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    /**
     * The point action wich did
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointact_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointAction pointAction;

    /**
     * The time when the action was done.
     */
    @NotNull
    @Column(name = "time", nullable = false)
    private LocalDateTime actionTime;

    /**
     *
     *
     * @return
     */
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
