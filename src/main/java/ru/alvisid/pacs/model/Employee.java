package ru.alvisid.pacs.model;

import ru.alvisid.pacs.model.abstractions.AbstractPerson;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * A person who works at certain department.
 */
@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = "card_num", name = "employees_unique_card_num_idx"),
        @UniqueConstraint(columnNames = "email", name = "employees_unique_emale_idx")
})
public class Employee extends AbstractPerson {
    /**
     * The department where the person works.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id", nullable = false)
    private Department department;

    /**
     * The position of the person.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pos_id", nullable = false)
    private Position position;

    /**
     * The card's number of the person.
     */
    @NotNull
    @Column(name = "card_num", nullable = false, unique = true)
    private Integer cardNum;

    /**
     * The person's email.
     */
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    @Size(max = 100)
    @Email
    private String email;

    /**
     * The person's own shedule.
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
    private EmpSchedule schedule;

    /**
     * The roles of the employee.
     */
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "emp_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"emp_id", "role"}, name = "employee_roles_con"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @Size(max = 255)
    private Set <Role> roles;

    /**
     * Returns the employee's department.
     *
     * @return the employee's department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Returns the employee's position.
     *
     * @return the employee's position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the employee's personal card's number.
     *
     * @return the employee's personal card's number.
     */
    public Integer getCardNum() {
        return cardNum;
    }

    /**
     * Returns the employee's email.
     *
     * @return the employee's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the employee's schedule.
     *
     * @return the employee's schedule.
     */
    public EmpSchedule getSchedule() {
        return schedule;
    }

    /**
     * Sets the specified value to the {@code department} field.
     *
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Sets the specified value to the
     *
     * @param position the specified value of the position.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Sets the spesified value to the {@code cardNum} field.
     *
     * @param cardNum the specified value of the cardNum.
     */
    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * Sets the spesified value to the {@code email} field.
     *
     * @param email the specified value of the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the spesified value to the {@code schedule} field.
     *
     * @param schedule the specified value of the schedule.
     */
    public void setSchedule(EmpSchedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Initializes a newly created <b>Employee</b> object with null fields values
     * and null fields of the superclass.
     *
     * @see Employee#Employee(String, String, String, Department, Position, Integer, String)
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String)
     */
    public Employee() {
    }

    /**
     * Constructs a <b>Department</b> object with specified
     * {@code lastName}, {@code firstName}, {@code secondName},
     * {@code department}, {@code position}, {@code cardNum}, {@code email} values
     * and null {@code id} value.
     *
     * @param lastName   the employee's last name.
     * @param firstName  the employee's first name.
     * @param secondName the employee's second name.
     * @param department the employee's department.
     * @param position   the employee's position.
     * @param cardNum    the employee's card's number.
     * @param email      the employee's email.
     * @see Employee#Employee()
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String)
     */
    public Employee(String lastName, String firstName, String secondName,
                    Department department, Position position, Integer cardNum, String email) {
        this(null, lastName, firstName, secondName,
                department, position, cardNum, email);
    }

    /**
     * Constructs a <b>Department</b> object with specified
     * {@code id}, {@code lastName}, {@code firstName}, {@code secondName},
     * {@code department}, {@code position}, {@code cardNum}, {@code email} values.
     *
     * @param id         the specifiec id.
     * @param lastName   the employee's last name.
     * @param firstName  the employee's first name.
     * @param secondName the employee's second name.
     * @param department the employee's department.
     * @param position   the employee's position.
     * @param cardNum    the employee's card's number.
     * @param email      the employee's email.
     * @see Employee#Employee()
     * @see Employee#Employee(String, String, String, Department, Position, Integer, String)
     */
    public Employee(Integer id, String lastName, String firstName, String secondName,
                    Department department, Position position, Integer cardNum, String email) {
        super(id, lastName, firstName, secondName);
        this.department = department;
        this.position = position;
        this.cardNum = cardNum;
        this.email = email;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>Employee</b> object that contains the same {@code department},
     * {@code position}, {@code cardNum}, {@code email} values as this object,
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

        Employee employee = (Employee) o;

        if (!department.equals(employee.department)) {
            return false;
        }

        if (!position.equals(employee.position)) {
            return false;
        }

        if (!cardNum.equals(employee.cardNum)) {
            return false;
        }

        return email.equals(employee.email);
    }

    /**
     * Returns a String object representing this <b>Employee</b> object.
     *
     * @return the String object representing this <b>Employee</b> object.
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", cardNum=" + cardNum +
                ", email='" + email + '\'' +
                '}';
    }
}
