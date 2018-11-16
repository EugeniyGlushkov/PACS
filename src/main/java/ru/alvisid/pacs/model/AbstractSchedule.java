package ru.alvisid.pacs.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Abstraction for shedules with start work, end work, start lunch and end lunch times.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractSchedule {
    /**
     * Sequence's start value.
     */
    public static final int START_SEQ = 10000;

    /**
     * The cpecifiec identifier for each schedule in a database.
     * One sequence produces id fo all schedules.
     */
    @Id
    @SequenceGenerator(name = "SCHEDULES_SEQ", sequenceName = "SCHEDULES_SEQ",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULES_SEQ")
    protected Integer id;

    /**
     * Start work time.
     * Must be non null.
     */
    @Column(name = "start_work", nullable = false)
    @NotNull
    protected LocalTime startWorkTime;

    /**
     * End work time.
     * Must be non null.
     */
    @Column(name = "end_work", nullable = false)
    @NotNull
    protected LocalTime endWorkTime;

    /**
     * Start lunch time.
     * Must be non null.
     */
    @Column(name = "start_lunch", nullable = false)
    @NotNull
    protected LocalTime startLunchTime;

    /**
     * End lunch time.
     * Must be non null.
     */
    @Column(name = "end_lunch", nullable = false)
    @NotNull
    protected LocalTime endLunchTime;

    /**
     * Gets the specifiec id.
     *
     * @return the specifiec id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the start work time of the schedule.
     *
     * @return the start work time of the schedule.
     */
    public LocalTime getStartWorkTime() {
        return startWorkTime;
    }

    /**
     * Gets the end work time of the schedule.
     *
     * @return the end work time of the schedule.
     */
    public LocalTime getEndWorkTime() {
        return endWorkTime;
    }

    /**
     * Gets the start lunch time of the schedule.
     *
     * @return the start lunch time of the schedule.
     */
    public LocalTime getStartLunchTime() {
        return startLunchTime;
    }

    /**
     * Gets the end lunch time of the schedule.
     *
     * @return the end lunch time of the schedule.
     */
    public LocalTime getEndLunchTime() {
        return endLunchTime;
    }

    /**
     * Sets the specifiec id.
     *
     * @param id the specifiec id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Sets the start work time of the schedule.
     *
     * @param startWorkTime the start work time of the schedule.
     */
    public void setStartWorkTime(LocalTime startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    /**
     * Sets the end work time of the schedule.
     *
     * @param endWorkTime the end work time of the schedule.
     */
    public void setEndWorkTime(LocalTime endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    /**
     * Sets the start lunch time of the schedule.
     *
     * @param startLunchTime the start lunch time of the schedule.
     */
    public void setStartLunchTime(LocalTime startLunchTime) {
        this.startLunchTime = startLunchTime;
    }

    /**
     * Sets the end lunch time of the schedule.
     *
     * @param endLunchTime the end lunch time of the schedule.
     */
    public void setEndLunchTime(LocalTime endLunchTime) {
        this.endLunchTime = endLunchTime;
    }

    /**
     * Returns {@code true} if id is null.
     *
     * @return {@code true} if id is null.
     */
    public boolean isNew() {
        return Objects.isNull(id);
    }

    /**
     * Initializes a newly created object with null fields.
     *
     * @see AbstractSchedule#AbstractSchedule(LocalTime, LocalTime, LocalTime, LocalTime)
     * @see AbstractSchedule#AbstractSchedule(Integer, LocalTime, LocalTime, LocalTime, LocalTime)
     */
    public AbstractSchedule() {
    }

    /**
     * Constructs <b>AbstractSchedule</b> object with null-id,
     * and initializes fields with start work time, end work time,
     * start lunch time and end lunch time.
     *
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule.
     * @param endLunchTime   the end lunch time of the schedule.
     * @see AbstractSchedule#AbstractSchedule()
     * @see AbstractSchedule#AbstractSchedule(Integer, LocalTime, LocalTime, LocalTime, LocalTime)
     */
    public AbstractSchedule(LocalTime startWorkTime, LocalTime endWorkTime,
                            LocalTime startLunchTime, LocalTime endLunchTime) {
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.startLunchTime = startLunchTime;
        this.endLunchTime = endLunchTime;
    }

    /**
     * Constructs <b>AbstractSchedule</b> object
     * and initializes fields with id, start work time, end work time,
     * start lunch time and end lunch time.
     *
     * @param id             the specifiec id.
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule.
     * @param endLunchTime   the end lunch time of the schedule.
     * @see AbstractSchedule#AbstractSchedule()
     * @see AbstractSchedule#AbstractSchedule(LocalTime, LocalTime, LocalTime, LocalTime)
     */
    public AbstractSchedule(Integer id,
                            LocalTime startWorkTime, LocalTime endWorkTime,
                            LocalTime startLunchTime, LocalTime endLunchTime) {
        this.id = id;
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.startLunchTime = startLunchTime;
        this.endLunchTime = endLunchTime;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>AbstractSchedule</b>'s heir
     * that contains the same id, start work time, end work time,
     * start lunch time and end lunch time values as this object.
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
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

    /**
     * Returns a hash code for this object.
     *
     * @return the hash code for this object.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns a String object representing this object.
     *
     * @return the String object representing this object.
     */
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
