package ru.alvisid.pacs.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Presents certain permitted action on the certain control point for certain employee.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "point_permits", uniqueConstraints =
@UniqueConstraint(columnNames = "pointact_id, emp_id", name = "permits_copoint_emp_con"))
public class PointPermit extends AbstractId {
    /**
     * The certain permitted action at the certain control point.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pointact_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointAction pointAction;

    /**
     * The employee to which the certain permitted action corresponds.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    /**
     * Returnes the certain point action.
     *
     * @return the certain point action.
     */
    public PointAction getPointAction() {
        return pointAction;
    }

    /**
     * Returnes the employee.
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
     * Sets the spesified employee.
     *
     * @param employee the spesified employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Initializes a newly created object with null id, pointAction and employee.
     *
     * @see PointPermit#PointPermit(PointAction, Employee)
     * @see PointPermit#PointPermit(Integer, PointAction, Employee)
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
     */
    public PointPermit(PointAction pointAction, Employee employee) {
        this(null, pointAction, employee);
    }

    /**
     * Constructs a new <b>PointPermit</b> object and sets the id, pointAction and employee values.
     *
     * @param id          the specifiec id.
     * @param pointAction the specified point action
     * @param employee    the specified employee.
     * @see PointPermit#PointPermit()
     * @see PointPermit#PointPermit(PointAction, Employee)
     */
    public PointPermit(Integer id, PointAction pointAction, Employee employee) {
        super(id);
        this.pointAction = pointAction;
        this.employee = employee;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>PointPermit</b> object
     * that contains the pointAction and employee values as this object
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

        PointPermit that = (PointPermit) o;

        if (!pointAction.equals(that.pointAction)) {
            return false;
        }

        return employee.equals(that.employee);
    }

    /**
     * Returns a String object representing this <b>PointPermit</b>'s object.
     *
     * @return the String object representing this <b>PointPermit</b>'s object.
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
