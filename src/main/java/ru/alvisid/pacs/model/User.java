package ru.alvisid.pacs.model;

import javax.persistence.*;

/**
 * Суперкласс для Worker, Visitor, VirtualUser
 * */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "fname")
    private String firstName;
    @Column(name = "mname")
    private String middleName;
    @Column(name = "lname")
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
