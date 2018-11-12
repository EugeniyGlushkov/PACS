package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractSchedule {
    public static final int START_SEQ = 10000;

    @Id
    @SequenceGenerator(name = "SCHEDULES_SEQ", sequenceName = "SCHEDULES_SEQ",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULES_SEQ")
    protected Integer id;
    protected LocalTime startWorkTime;
    protected LocalTime endWorkTime;
    protected LocalTime startLunchTime;
    protected LocalTime endLunchTime;

    public Integer getId() {
        return id;
    }

    public LocalTime getStartWorkTime() {
        return startWorkTime;
    }

    public LocalTime getEndWorkTime() {
        return endWorkTime;
    }

    public LocalTime getStartLunchTime() {
        return startLunchTime;
    }

    public LocalTime getEndLunchTime() {
        return endLunchTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStartWorkTime(LocalTime startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public void setEndWorkTime(LocalTime endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public void setStartLunchTime(LocalTime startLunchTime) {
        this.startLunchTime = startLunchTime;
    }

    public void setEndLunchTime(LocalTime endLunchTime) {
        this.endLunchTime = endLunchTime;
    }

    /**
     * Returnes {@code true} if id is null.
     *
     * @return {@code true} if id is null.
     */
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public AbstractSchedule() {
    }

    public AbstractSchedule(LocalTime startWorkTime, LocalTime endWorkTime,
                            LocalTime startLunchTime, LocalTime endLunchTime) {
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.startLunchTime = startLunchTime;
        this.endLunchTime = endLunchTime;
    }

    public AbstractSchedule(Integer id,
                            LocalTime startWorkTime, LocalTime endWorkTime,
                            LocalTime startLunchTime, LocalTime endLunchTime) {
        this.id = id;
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.startLunchTime = startLunchTime;
        this.endLunchTime = endLunchTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof AbstractPerson) {
            AbstractSchedule that = (AbstractSchedule) o;

            if (id != null ? !id.equals(that.id) : that.id != null) {
                return false;
            }

            if (!startWorkTime.equals(that.startWorkTime)) {
                return false;
            }

            if (!endWorkTime.equals(that.endWorkTime)) {
                return false;
            }

            if (!startLunchTime.equals(that.startLunchTime)) {
                return false;
            }

            return endLunchTime.equals(that.endLunchTime);
        }

        return false;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("Schedule %s (%s, '%s, %s, %s, %s'",
                getClass().getName(),
                id,
                startWorkTime,
                startLunchTime,
                endLunchTime,
                endWorkTime);
    }
}
