package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Сущность хранит дату, время начала/конца работы.
 * Используется Worker' ом.
 * */
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "start_duty")
    private Timestamp startOfDuty;
    @Column(name = "end_duty")
    private Timestamp endOfDuty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getStartOfDuty() {
        return startOfDuty;
    }

    public void setStartOfDuty(Timestamp startOfDuty) {
        this.startOfDuty = startOfDuty;
    }

    public Timestamp getEndOfDuty() {
        return endOfDuty;
    }

    public void setEndOfDuty(Timestamp endOfDuty) {
        this.endOfDuty = endOfDuty;
    }
}
