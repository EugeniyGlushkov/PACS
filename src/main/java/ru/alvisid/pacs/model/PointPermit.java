package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Presents certain permitted action on the certain control point for certain employee.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "point_permits", uniqueConstraints =
@UniqueConstraint(columnNames = {"pointact_id", "emp_id"}, name = "permits_copoint_emp_con"))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PointPermit extends AbstractId {
    /**
     * The employee to which the certain permitted action corresponds.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    /**
     * The certain permitted action at the certain control point.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pointact_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointAction pointAction;

    /**
     * Returns the certain point action.
     *
     * @return the certain point action.
     */
    public PointAction getPointAction() {
        return pointAction;
    }

    /**
     * Returns the employee.
     *
     * @return the employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the specified point action.
     *
     * @param pointAction the specified point action.
     */
    public void setPointAction(PointAction pointAction) {
        this.pointAction = pointAction;
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
     * Initializes a newly created object with null id, pointAction and employee.
     *
     * @see PointPermit#PointPermit(PointAction, Employee)
     * @see PointPermit#PointPermit(Integer, PointAction, Employee)
     * @see PointPermit#PointPermit(PointPermit)
     */
    public PointPermit() {
    }

    /**
     * Constructs a new <b>PointPermit</b> object and sets the pointAction and employee values.
     *
     * @param pointAction the specified point action.
     * @param employee    the specified employee.
     * @see PointPermit#PointPermit()
     * @see PointPermit#PointPermit(Integer, PointAction, Employee)
     * @see PointPermit#PointPermit(PointPermit)
     */
    public PointPermit(PointAction pointAction, Employee employee) {
        this(null, pointAction, employee);
    }

    /**
     * Constructs a new <b>PointPermit</b> object and sets the id, pointAction and employee values.
     *
     * @param id          the specified id.
     * @param pointAction the specified point action
     * @param employee    the specified employee.
     * @see PointPermit#PointPermit()
     * @see PointPermit#PointPermit(PointAction, Employee)
     * @see PointPermit#PointPermit(PointPermit)
     */
    public PointPermit(Integer id, PointAction pointAction, Employee employee) {
        super(id);
        this.pointAction = pointAction;
        this.employee = employee;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param pointPermit the specified object to copying.
     * @see PointPermit#PointPermit()
     * @see PointPermit#PointPermit(PointAction, Employee)
     * @see PointPermit#PointPermit(Integer, PointAction, Employee)
     */
    public PointPermit(PointPermit pointPermit) {
        this(pointPermit.getId(),
                pointPermit.getPointAction(),
                pointPermit.getEmployee());
    }

    /**
     * Returns a String object representing this <b>PointPermit</b> object.
     *
     * @return the String object representing this <b>PointPermit</b> object.
     */
    @Override
    public String toString() {
        return "PointPermit{" +
                "id=" + id +
                ", pointAction=" + pointAction +
                ", employee=" + employee +
                '}';
    }
}
