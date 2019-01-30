package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A department's chief.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "department_chiefs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DepartmentChief extends AbstractId {
    /**
     * A current department.
     */
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    /**
     * A chief of the department.
     */
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee chief;

    /**
     * Returns the current department.
     *
     * @return the current department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Returns the chief of the department.
     *
     * @return the chief of the department.
     */
    public Employee getChief() {
        return chief;
    }

    /**
     * Sets the specified department
     *
     * @param department the specified department.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Sets the chief of the department.
     *
     * @param chief the chief of the department.
     */
    public void setChief(Employee chief) {
        this.chief = chief;
    }

    /**
     * Initializes a newly created object with null id, department and chief.
     *
     * @see DepartmentChief#DepartmentChief(Department, Employee)
     * @see DepartmentChief#DepartmentChief(Integer, Department, Employee)
     * @see DepartmentChief#DepartmentChief(DepartmentChief)
     */
    public DepartmentChief() {
    }

    /**
     * Constructs a new <b>DepartmentChief</b> object and sets the department and chief values.
     *
     * @param department the specified department.
     * @param chief      the specified employee.
     * @see DepartmentChief#DepartmentChief()
     * @see DepartmentChief#DepartmentChief(Integer, Department, Employee)
     * @see DepartmentChief#DepartmentChief(DepartmentChief)
     */
    public DepartmentChief(Department department, Employee chief) {
        this(null, department, chief);
    }

    /**
     * Constructs a new <b>DepartmentChief</b> object and sets the id, department and chief values.
     *
     * @param id         the specified id.
     * @param department the specified department.
     * @param chief      the specified employee.
     * @see DepartmentChief#DepartmentChief()
     * @see DepartmentChief#DepartmentChief(Department, Employee)
     * @see DepartmentChief#DepartmentChief(DepartmentChief)
     */
    public DepartmentChief(Integer id, Department department, Employee chief) {
        super(id);
        this.department = department;
        this.chief = chief;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param deptChief the specified object to copying.
     * @see DepartmentChief#DepartmentChief()
     * @see DepartmentChief#DepartmentChief(Department, Employee)
     * @see DepartmentChief#DepartmentChief(Integer, Department, Employee)
     */
    public DepartmentChief(DepartmentChief deptChief) {
        this(deptChief.getId(),
                deptChief.getDepartment(),
                deptChief.getChief());
    }

    /**
     * Returns a String object representing this <b>DepartmentChief</b> object.
     *
     * @return the String object representing this <b>DepartmentChief</b> object.
     */
    @Override
    public String toString() {
        return "DepartmentChief{" +
                "id=" + id +
                ", department=" + department +
                ", chief=" + chief +
                '}';
    }
}
