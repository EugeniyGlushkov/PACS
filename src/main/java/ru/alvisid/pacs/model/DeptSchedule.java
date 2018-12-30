package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractSchedule;

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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
     * Returns the specific department.
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
     * @see DeptSchedule#DeptSchedule(DeptSchedule)
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
     * @see DeptSchedule#DeptSchedule(DeptSchedule)
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
     * @see DeptSchedule#DeptSchedule(DeptSchedule)
     */
    public DeptSchedule(Integer id,
                        LocalTime startWorkTime, LocalTime endWorkTime,
                        LocalTime startLunchTime, LocalTime endLunchTime,
                        Department department) {
        super(id, startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.department = department;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param schedule the specified object to copying.
     * @see DeptSchedule#DeptSchedule()
     * @see DeptSchedule#DeptSchedule(LocalTime, LocalTime, LocalTime, LocalTime, Department)
     * @see DeptSchedule#DeptSchedule(Integer, LocalTime, LocalTime, LocalTime, LocalTime, Department)
     */
    public DeptSchedule(DeptSchedule schedule) {
        this(schedule.getId(),
                schedule.getStartWorkTime(),
                schedule.getEndWorkTime(),
                schedule.getStartLunchTime(),
                schedule.getEndLunchTime(),
                schedule.getDepartment());
    }

    /**
     * Returns a String object representing this <b>DeptSchedule</b> object.
     *
     * @return the String object representing this <b>DeptSchedule</b> object.
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
