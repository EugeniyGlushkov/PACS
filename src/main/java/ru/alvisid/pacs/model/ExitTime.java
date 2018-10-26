package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Сущность хранит время выхода
 * */
@Entity
@Table(name = "exittime")
public class ExitTime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "exit")
    private Timestamp exitTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public void setExitTime(Timestamp exitTime) {
        this.exitTime = exitTime;
    }
}
