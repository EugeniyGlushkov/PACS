package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * An employee's chief.
 */
@Entity
@Table(name = "chiefs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chief extends AbstractId {
    /**
     * An employee.
     */
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    /**
     * An employee's chief.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chief_id", nullable = false)
    private Employee chief;

    /**
     * Returns the employee.
     *
     * @return the employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Returns the employee's chief.
     *
     * @return the employee's chief.
     */
    public Employee getChief() {
        return chief;
    }

    /**
     * Sets the employee.
     *
     * @param employee the employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Sets the employee's chief.
     *
     * @param chief the employee's chief.
     */
    public void setChief(Employee chief) {
        this.chief = chief;
    }

    /**
     * Initializes a newly created object with null id, employee and chief.
     *
     * @see Chief#Chief(Employee, Employee)
     * @see Chief#Chief(Integer, Employee, Employee)
     * @see Chief#Chief(Chief)
     */
    public Chief() {
    }

    /**
     * Constructs a new <b>Chief</b> object and sets the employee and chief values.
     *
     * @param employee the specified employee.
     * @param chief    the specified employee's chief.
     * @see Chief#Chief()
     * @see Chief#Chief(Integer, Employee, Employee)
     * @see Chief#Chief(Chief)
     */
    public Chief(Employee employee, Employee chief) {
        this(null, employee, chief);
    }

    /**
     * Constructs a new <b>Chief</b> object and sets the id, employee and chief values.
     *
     * @param id       the specified id.
     * @param employee the specified employee.
     * @param chief    the specified employee's chief.
     * @see Chief#Chief()
     * @see Chief#Chief(Employee, Employee)
     * @see Chief#Chief(Chief)
     */
    public Chief(Integer id, Employee employee, Employee chief) {
        super(id);
        this.employee = employee;
        this.chief = chief;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param deptChief the specified object to copying.
     * @see Chief#Chief()
     * @see Chief#Chief(Employee, Employee)
     * @see Chief#Chief(Integer, Employee, Employee)
     */
    public Chief(Chief deptChief) {
        this(deptChief.getId(),
                deptChief.getChief(),
                deptChief.getEmployee());
    }

    /**
     * Returns a String object representing this <b>Chief</b> object.
     *
     * @return the String object representing this <b>Chief</b> object.
     */
    @Override
    public String toString() {
        return "Chief{" +
                "id=" + id +
                ", employee=" + employee +
                ", chief=" + chief +
                '}';
    }
}
