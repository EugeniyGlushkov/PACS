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

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondtName() {
        return secondtName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondtName(String secondtName) {
        this.secondtName = secondtName;
    }

    public boolean isNew() {
        return Objects.isNull(id);
    }

    public AbstractPerson() {
    }

    public AbstractPerson(String lastName, String firstName, String secondtName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondtName = secondtName;
    }

    public AbstractPerson(Integer id, String lastName, String firstName, String secondtName) {
        this(lastName, firstName, secondtName);
        this.id = id;
    }

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

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("Person %s (%s, '%s %s %s')",
                getClass().getName(), id, lastName, firstName, secondtName);
    }
}
