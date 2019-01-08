package ru.alvisid.pacs.model.abstractions;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Check;

import javax.persistence.*;
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
@Check(constraints = "start_work < start_lunch" +
        " AND start_lunch < end_lunch" +
        " AND end_lunch < end_work")
public abstract class AbstractSchedule implements HasId {
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
     * If value is {@code null} then start work time is not regulated.
     */
    @Column(name = "start_work")
    protected LocalTime startWorkTime;

    /**
     * End work time.
     * If value is {@code null} then end work time is not regulated.
     */
    @Column(name = "end_work")
    protected LocalTime endWorkTime;

    /**
     * Start lunch time.
     * If value is {@code null} then start lunch time is not regulated.
     */
    @Column(name = "start_lunch")
    protected LocalTime startLunchTime;

    /**
     * End lunch time.
     * If value is {@code null} then end  lunch time is not regulated.
     */
    @Column(name = "end_lunch")
    protected LocalTime endLunchTime;

    /**
     * Returns the specifiec id.
     *
     * @return the specifiec id.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Returns the start work time of the schedule.
     *
     * @return the start work time of the schedule.
     */
    public LocalTime getStartWorkTime() {
        return startWorkTime;
    }

    /**
     * Returns the end work time of the schedule.
     *
     * @return the end work time of the schedule.
     */
    public LocalTime getEndWorkTime() {
        return endWorkTime;
    }

    /**
     * Returns the start lunch time of the schedule.
     *
     * @return the start lunch time of the schedule.
     */
    public LocalTime getStartLunchTime() {
        return startLunchTime;
    }

    /**
     * Returns the end lunch time of the schedule.
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
    @Override
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
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    /**
     * Initializes a newly created object with null fields.
     *
     * @see AbstractSchedule#AbstractSchedule(Integer, LocalTime, LocalTime, LocalTime, LocalTime)
     */
    public AbstractSchedule() {
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
     * and is the same class as this object,
     * and that contains the same id value as this object.
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }

        AbstractSchedule that = (AbstractSchedule) o;

        return Objects.isNull(id) ? Objects.isNull(that.id) : id.equals(that.id);
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
