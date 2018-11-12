package ru.alvisid.pacs.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Abstraction for person wich have id, last name, first name and second name.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractPerson {
    /**
     * Sequence's start value.
     */
    public static final int START_SEQ = 10000;

    /**
     * The cpecifiec identifier for each person in a database.
     * One sequence produces id fo all persons.
     */
    @Id
    @SequenceGenerator(name = "PERS_SEQ", sequenceName = "PERS_SEQ", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERS_SEQ")
    protected Integer id;

    /**
     * The last name of the person.
     * Min value is 2 characters, max value is 100 characters.
     * Must be non null and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "last_name", nullable = false)
    protected String lastName;

    /**
     * The first name of the person.
     * Min value is 2 characters, max value is 100 characters.
     * Must be non null and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "first_name", nullable = false)
    protected String firstName;

    /**
     * The second name of the person.
     * Min value is 2 characters, max value is 100 characters.
     * Must be non null and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "second_name", nullable = false)
    protected String secondtName;

    /**
     * Gets the specifiec id.
     *
     * @return the specifiec id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the person's last name.
     *
     * @return the person's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the person's first name.
     *
     * @return the person's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the person's second name.
     *
     * @return the person's second name.
     */
    public String getSecondtName() {
        return secondtName;
    }

    /**
     * Sets the specifiec id.
     *
     * @param id the specifiec id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Sets the person's last name.
     *
     * @param lastName the person's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the person's first name.
     *
     * @param firstName the person's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the person's second name.
     *
     * @param secondtName the person's second name.
     */
    public void setSecondtName(String secondtName) {
        this.secondtName = secondtName;
    }

    /**
     * Returnes {@code true} if id is null.
     *
     * @return {@code true} if id is null.
     */
    public boolean isNew() {
        return Objects.isNull(id);
    }

    /**
     * Initializes a newly created object with null fields.
     *
     * @see AbstractPerson#AbstractPerson(String, String, String)
     * @see AbstractPerson#AbstractPerson(Integer, String, String, String)
     */
    public AbstractPerson() {
    }

    /**
     * Constructs a <b>AbstractPerson</b> object whith null-id
     * and sets last name, first name, second name.
     *
     * @param lastName    the person's last name.
     * @param firstName   the person's first name.
     * @param secondtName the person's second name.
     */
    public AbstractPerson(String lastName, String firstName, String secondtName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondtName = secondtName;
    }

    /**
     * Constructs a <b>AbstractPerson</b> object and
     * sets id, last name, first name, second name.
     *
     * @param id          the specifiec id.
     * @param lastName    the person's last name.
     * @param firstName   the person's first name.
     * @param secondtName the person's second name.
     */
    public AbstractPerson(Integer id, String lastName, String firstName, String secondtName) {
        this(lastName, firstName, secondtName);
        this.id = id;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>AbstractPerson</b>'s heir
     * that contains the same id, last name, first name
     * and second name values as this object.
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof AbstractPerson) {
            AbstractPerson that = (AbstractPerson) o;

            if (id != null ? !id.equals(that.id) : that.id != null) {
                return false;
            }
            if (!lastName.equals(that.lastName)) {
                return false;
            }
            if (!firstName.equals(that.firstName)) {
                return false;
            }

            return secondtName.equals(that.secondtName);
        }

        return false;
    }

    /**
     * Returns a hash code for this Entity.
     *
     * @return the hash code for this Entity.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns a String object representing this entity's object.
     *
     * @return the String object representing this entity's object.
     */
    @Override
    public String toString() {
        return String.format("Person %s (%s, '%s %s %s')",
                getClass().getName(), id, lastName, firstName, secondtName);
    }
}
