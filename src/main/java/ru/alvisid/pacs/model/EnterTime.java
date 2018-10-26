package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Сущность хранит время входа
 * */
@Entity
@Table(name = "entertime")
public class EnterTime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "enter")
    private Timestamp enterTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Timestamp enterTime) {
        this.enterTime = enterTime;
    }
}
