package ru.alvisid.pacs.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.alvisid.pacs.model.abstractions.AbstractPerson;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * A person who works at certain department.
 */
@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = "card_num", name = "employees_unique_card_num_idx"),
        @UniqueConstraint(columnNames = "email", name = "employees_unique_emale_idx")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee extends AbstractPerson {
    /**
     * The department where the person works.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id")
    private Department department;

    /**
     * The position of the person.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
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
     * The roles of the employee.
     */
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "emp_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"emp_id", "role"}, name = "employee_roles_con"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @Size(max = 255)
    @BatchSize(size = 200)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set <Role> roles;

    /**
     * The point permits of the employee.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @OrderBy("pointAction.controlPoint.serialCode, pointAction.actionType.type ASC")
    private List <PointPermit> pointPermits;

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
     * Returns the roles of the employee.
     *
     * @return the roles of the employee.
     */
    public Set <Role> getRoles() {
        return roles;
    }

    /**
     * Returns the point permits of the employee.
     *
     * @return the point permits of the employee.
     */
    public List <PointPermit> getPointPermits() {
        return pointPermits;
    }

    /**
     * Sets the specified value to the {@code department} field.
     *
     * @param department the specified value to the {@code department} field.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Sets the specified value to the {@code position} field.
     *
     * @param position the specified value to the {@code position} field.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Sets the specified value to the {@code cardNum} field.
     *
     * @param cardNum the specified value of the cardNum.
     */
    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * Sets the specified value to the {@code email} field.
     *
     * @param email the specified value of the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the specified value to the {@code roles} field.
     *
     * @param roles the specified value to the {@code roles} field.
     */
    public void setRoles(Set <Role> roles) {
        this.roles = roles;
    }

    /**
     * Sets the specified value to the {@code pointPermits} field.
     *
     * @param pointPermits the specified value to the {@code pointPermits} field.
     */
    public void setPointPermits(List <PointPermit> pointPermits) {
        this.pointPermits = pointPermits;
    }

    /**
     * Initializes a newly created <b>Employee</b> object with null fields values
     * and null fields of the superclass.
     *
     * @see Employee#Employee(String, String, String, Department, Position, Integer, String)
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String)
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String, Set)
     * @see Employee#Employee(Employee)
     */
    public Employee() {
    }

    /**
     * Constructs a <b>Department</b> object with specified
     * {@code lastName}, {@code firstName}, {@code secondName},
     * {@code department}, {@code position}, {@code cardNum}, {@code email} values
     * and null {@code id}, {@code roles} values. value.
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
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String, Set)
     * @see Employee#Employee(Employee)
     */
    public Employee(String lastName, String firstName, String secondName,
                    Department department, Position position, Integer cardNum, String email) {
        this(null, lastName, firstName, secondName,
                department, position, cardNum, email);
    }

    /**
     * Constructs a <b>Department</b> object with specified
     * {@code id}, {@code lastName}, {@code firstName}, {@code secondName},
     * {@code department}, {@code position}, {@code cardNum}, {@code email}
     * and null {@code roles} values.
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
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String, Set)
     * @see Employee#Employee(Employee)
     */
    public Employee(Integer id, String lastName, String firstName, String secondName,
                    Department department, Position position, Integer cardNum, String email) {
        this(id, lastName, firstName, secondName, department, position, cardNum, email, null);
    }

    /**
     * Constructs a <b>Department</b> object with specified
     * {@code id}, {@code lastName}, {@code firstName}, {@code secondName},
     * {@code department}, {@code position}, {@code cardNum}, {@code email},
     * {@code roles} values.
     *
     * @param id         the specifiec id.
     * @param lastName   the employee's last name.
     * @param firstName  the employee's first name.
     * @param secondName the employee's second name.
     * @param department the employee's department.
     * @param position   the employee's position.
     * @param cardNum    the employee's card's number.
     * @param email      the employee's email.
     * @param roles      the employee's roles.
     * @see Employee#Employee()
     * @see Employee#Employee(String, String, String, Department, Position, Integer, String)
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String)
     * @see Employee#Employee(Employee)
     */
    public Employee(Integer id, String lastName, String firstName, String secondName,
                    Department department, Position position, Integer cardNum, String email,
                    Set <Role> roles) {
        super(id, lastName, firstName, secondName);
        this.department = department;
        this.position = position;
        this.cardNum = cardNum;
        this.email = email;
        this.roles = roles;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param employee the specified object to copying.
     * @see Employee#Employee()
     * @see Employee#Employee(String, String, String, Department, Position, Integer, String)
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String)
     * @see Employee#Employee(Integer, String, String, String, Department, Position, Integer, String, Set)
     */
    public Employee(Employee employee) {
        this(employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getSecondName(),
                employee.getDepartment(),
                employee.getPosition(),
                employee.getCardNum(),
                employee.getEmail(),
                employee.getRoles());
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
                ", department (id=" + department.getId() + ", name='" + department.getName() + "\')" +
                ", position=" + position +
                ", cardNum=" + cardNum +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
