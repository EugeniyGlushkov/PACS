package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность хранит список времени входа/выхода
 * */
@Entity
@Table(name = "visitor")
public class Visitor extends User {
    @Column(name = "inside")
    private boolean isInside;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EnterTime> enterTimes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ExitTime> exitTime;

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean isInside) {
        this.isInside = isInside;
    }

    public List<EnterTime> getEnterTimes() {
        return enterTimes;
    }

    public void setEnterTimes(List<EnterTime> enterTimes) {
        this.enterTimes = enterTimes;
    }

    public List<ExitTime> getExitTime() {
        return exitTime;
    }

    public void setExitTime(List<ExitTime> exitTime) {
        this.exitTime = exitTime;
    }
}
