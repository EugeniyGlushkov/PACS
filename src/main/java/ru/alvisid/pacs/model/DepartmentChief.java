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
 */
@Entity
@Table(name = "department_chiefs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DepartmentChief extends AbstractId {
    /**
     * A current department.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    /**
     * A chief of the department.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

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
    public Employee getEmployee() {
        return employee;
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
     * @param employee the chief of the department.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Initializes a newly created object with null id, department and employee.
     *
     * @see DepartmentChief#DepartmentChief(Department, Employee)
     * @see DepartmentChief#DepartmentChief(Integer, Department, Employee)
     * @see DepartmentChief#DepartmentChief(DepartmentChief)
     */
    public DepartmentChief() {
    }

    /**
     * Constructs a new <b>DepartmentChief</b> object and sets the department and employee values.
     *
     * @param department the specified department.
     * @param employee   the specified employee.
     * @see DepartmentChief#DepartmentChief()
     * @see DepartmentChief#DepartmentChief(Integer, Department, Employee)
     * @see DepartmentChief#DepartmentChief(DepartmentChief)
     */
    public DepartmentChief(Department department, Employee employee) {
        this(null, department, employee);
    }

    /**
     * Constructs a new <b>DepartmentChief</b> object and sets the id, department and employee values.
     *
     * @param id         the specified id.
     * @param department the specified department.
     * @param employee   the specified employee.
     * @see DepartmentChief#DepartmentChief()
     * @see DepartmentChief#DepartmentChief(Department, Employee)
     * @see DepartmentChief#DepartmentChief(DepartmentChief)
     */
    public DepartmentChief(Integer id, Department department, Employee employee) {
        super(id);
        this.department = department;
        this.employee = employee;
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
                deptChief.getEmployee());
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
                ", employee=" + employee +
                '}';
    }
}
