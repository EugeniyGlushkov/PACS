package ru.alvisid.pacs.model;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * A schedule for specific department.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "dep_schedules", uniqueConstraints =
@UniqueConstraint(columnNames = "dep_id", name = "depsched_unique_depid_idx"))
@Check(constraints = "start_work < start_lunch\n" +
        "AND start_lunch < end_lunch\n" +
        "AND end_lunch < end_work")
public class DeptSchedule extends AbstractSchedule {
    /**
     * The specific department.
     * Must be non null.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Department department;

    /**
     * Gets the specific department.
     *
     * @return the specific department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the specified department.
     *
     * @param department the specified department.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Initializes a newly created <b>DeptSchedule</b> object with null department value and null fiels of the superclass.
     *
     * @see DeptSchedule#DeptSchedule(LocalTime, LocalTime, LocalTime, LocalTime, Department)
     * @see DeptSchedule#DeptSchedule(Integer, LocalTime, LocalTime, LocalTime, LocalTime, Department)
     */
    public DeptSchedule() {
    }

    /**
     * Constructs a <b>DeptSchedule</b> object with specified start work time, end work time
     * start lunch time, end lunch time
     * department values and null id value.
     *
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule
     * @param endLunchTime   the end lunch time of the schedule.
     * @param department     the department who has the schedule.
     * @see DeptSchedule#DeptSchedule()
     * @see DeptSchedule#DeptSchedule(Integer, LocalTime, LocalTime, LocalTime, LocalTime, Department)
     */
    public DeptSchedule(LocalTime startWorkTime, LocalTime endWorkTime,
                        LocalTime startLunchTime, LocalTime endLunchTime,
                        Department department) {
        super(startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.department = department;
    }

    /**
     * Constructs a <b>DeptSchedule</b> object with specified id,
     * start work time, end work time
     * start lunch time, end lunch time
     * and department values.
     *
     * @param id             the specifiec id.
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule
     * @param endLunchTime   the end lunch time of the schedule.
     * @param department     the department who has the schedule.
     * @see DeptSchedule#DeptSchedule()
     * @see DeptSchedule#DeptSchedule(LocalTime, LocalTime, LocalTime, LocalTime, Department)
     */
    public DeptSchedule(Integer id,
                        LocalTime startWorkTime, LocalTime endWorkTime,
                        LocalTime startLunchTime, LocalTime endLunchTime,
                        Department department) {
        super(id, startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.department = department;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>DeptSchedule</b> object that contains the same {@code department} value as this object,
     * and superclass is equals the specified object.
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        DeptSchedule that = (DeptSchedule) o;

        return department.equals(that.department);
    }

    /**
     * Returns a String object representing this <b>DeptSchedule</b>'s object.
     *
     * @return the String object representing this <b>DeptSchedule</b>'s object.
     */
    @Override
    public String toString() {
        return "DeptSchedule{" +
                "id=" + id +
                ", department=" + department +
                ", startWorkTime=" + startWorkTime +
                ", endWorkTime=" + endWorkTime +
                ", startLunchTime=" + startLunchTime +
                ", endLunchTime=" + endLunchTime +
                '}';
    }
}
