package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность хранит имя департамента, в котором работает Worker, а так же
 * список рабочих в департаменте.
 * */
@Entity
@Table(name = "dept")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Worker> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Worker> getUsers() {
        return users;
    }

    public void setUsers(List<Worker> users) {
        this.users = users;
    }
}
