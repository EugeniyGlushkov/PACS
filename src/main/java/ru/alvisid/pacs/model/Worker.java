package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность хранит информацию о департаменте, должность, расписание работы,
 * список времени входа/выхода.
 * */
@Entity
@Table(name = "worker")
public class Worker extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    @Column(name = "position")
    private String position;
    @Column(name = "inside")
    private boolean isInside;
    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;
    @OneToOne(cascade = CascadeType.ALL)
    private List<EnterTime> enterTimes;
    @OneToOne(cascade = CascadeType.ALL)
    private List<ExitTime> exitTime;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean isInside) {
        this.isInside = isInside;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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
