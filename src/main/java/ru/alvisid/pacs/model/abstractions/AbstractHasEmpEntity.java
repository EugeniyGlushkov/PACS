package ru.alvisid.pacs.model.abstractions;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Abstraction for class which has employee, description
 * and id (generated by individual sequence generator) values.
 */
@MappedSuperclass
public abstract class AbstractHasEmpEntity extends AbstractEntity {
    /**
     * The employee value which corresponds to the entity.
     */
    @NotNull
    @JoinColumn(name = "emp_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Employee employee;

    /**
     * Returns the current employee value.
     *
     * @return the current employee value.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets a specified parameter to the employee field.
     *
     * @param employee the pecified parameter to the employee field.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Initializes a newly created object with null employee value
     * and null superclass's fields.
     *
     * @see AbstractHasEmpEntity#AbstractHasEmpEntity(String, Employee)
     * @see AbstractHasEmpEntity#AbstractHasEmpEntity(Integer, String, Employee)
     */
    public AbstractHasEmpEntity() {
    }

    /**
     * Constructs a new <b>AbstractHasEmpEntity</b> object
     * and sets description, employee values. Id will be null.
     *
     * @param description the description of the entity.
     * @param employee    the pecified parameter of the employee.
     * @see AbstractHasEmpEntity#AbstractHasEmpEntity()
     * @see AbstractHasEmpEntity#AbstractHasEmpEntity(Integer, String, Employee)
     */
    public AbstractHasEmpEntity(String description, Employee employee) {
        this(null, description, employee);
    }

    /**
     * Constructs a new <b>AbstractHasEmpEntity</b> object
     * and sets id, description and employee values.
     *
     * @param id          the specifiec id.
     * @param description the description of the entity.
     * @param employee    the specified parameter of the employee.
     * @see AbstractHasEmpEntity#AbstractHasEmpEntity()
     * @see AbstractHasEmpEntity#AbstractHasEmpEntity(String, Employee)
     */
    public AbstractHasEmpEntity(Integer id, String description, Employee employee) {
        super(id, description);
        this.employee = employee;
    }

    /**
     * Returns a String object representing this object.
     *
     * @return the String object representing this object.
     */
    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s, %s')",
                getClass().getName(), id, description, employee);
    }
}
