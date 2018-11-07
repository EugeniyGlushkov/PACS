package ru.alvisid.pacs.model;

import java.util.Objects;

public abstract class AbstractPerson {
    public static final int START_SEQ = 10000;

    protected Integer id;
    protected String lastName;
    protected String firstName;
    protected String secondtName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPerson that = (AbstractPerson) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return secondtName != null ? secondtName.equals(that.secondtName) : that.secondtName == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
