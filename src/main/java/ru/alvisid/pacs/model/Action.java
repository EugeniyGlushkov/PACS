package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractId;

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
@Table(name = "actions", uniqueConstraints =
@UniqueConstraint(columnNames = {"emp_id", "time"}, name = "act_emp_time_con"))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Action extends AbstractId {
    /**
     * The employee which did the action.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    /**
     * The point's action which was done.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pointact_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointAction pointAction;

    /**
     * The time  when the action was done.
     */
    @NotNull
    @Column(name = "time", nullable = false)
    private LocalDateTime actionTime;

    /**
     * Returns the employee.
     *
     * @return the employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Returns the point's action.
     *
     * @return the point's action.
     */
    public PointAction getPointAction() {
        return pointAction;
    }

    /**
     * Returns the time when the action was done.
     *
     * @return the time when the action was done.
     */
    public LocalDateTime getActionTime() {
        return actionTime;
    }

    /**
     * Sets the specified employee.
     *
     * @param employee the specified employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Sets the specified point's action.
     *
     * @param pointAction the point's action.
     */
    public void setPointAction(PointAction pointAction) {
        this.pointAction = pointAction;
    }

    /**
     * Sets the specified time when the action was done.
     *
     * @param actionTime the specified taime when the action was done.
     */
    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    /**
     * Initializes a newly created <b>Action</b> object
     * with null {@code employee}, {@code pointAction}, {@code actionTime} values
     * and null fiels of the superclass.
     *
     * @see Action#Action(Employee, PointAction, LocalDateTime)
     * @see Action#Action(Integer, Employee, PointAction, LocalDateTime)
     * @see Action#Action(Action)
     */
    public Action() {
    }

    /**
     * Constructs a <b>Action</b> object with specified employee, pointAction, actionTime
     * and null id values.
     *
     * @param employee    the specified employee.
     * @param pointAction the specified point's action.
     * @param actionTime  the specified time when the action was done.
     * @see Action#Action()
     * @see Action#Action(Integer, Employee, PointAction, LocalDateTime)
     * @see Action#Action(Action)
     */
    public Action(Employee employee, PointAction pointAction, LocalDateTime actionTime) {
        this(null, employee, pointAction, actionTime);
    }

    /**
     * Constructs a <b>Action</b> object with specified id, employee, pointAction and actionTime values.
     *
     * @param id          the specifiec identifier.
     * @param employee    the specified employee.
     * @param pointAction the specified point's action.
     * @param actionTime  the specified time when the action was done.
     * @see Action#Action()
     * @see Action#Action(Employee, PointAction, LocalDateTime)
     * @see Action#Action(Action)
     */
    public Action(Integer id, Employee employee, PointAction pointAction, LocalDateTime actionTime) {
        super(id);
        this.employee = employee;
        this.pointAction = pointAction;
        this.actionTime = actionTime;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param action the specified object to copying.
     * @see Action#Action()
     * @see Action#Action(Employee, PointAction, LocalDateTime)
     * @see Action#Action(Integer, Employee, PointAction, LocalDateTime)
     */
    public Action(Action action) {
        this(action.getId(),
                action.getEmployee(),
                action.getPointAction(),
                action.getActionTime());
    }

    /**
     * Returns a String object representing this <b>Action</b> object.
     *
     * @return the String object representing this <b>Action</b> object.
     */
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
