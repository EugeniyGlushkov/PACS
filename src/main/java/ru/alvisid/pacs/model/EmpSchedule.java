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
 * A schedule for specific employee.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "emp_schedules", uniqueConstraints =
@UniqueConstraint(columnNames = "emp_id", name = "empsched_unique_empid_idx"))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmpSchedule extends AbstractSchedule {
    /**
     * The specific employee.
     * Must be non null.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    /**
     * Returns the specific employee.
     *
     * @return the specific employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the specified employee.
     *
     * @param employee the specified employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Util method. Returns new {@code EmpSchedule} with specified employee,
     * initialized start work time, end work time, start lunch time and lunch time from
     * specified schedule and id-null.
     *
     * @param employee the specified employee.
     * @param schedule the specified schedule.
     * @return the new {@code EmpSchedule} with specified employee,
     * initialized start work time, end work time, start lunch time, end lunch time from
     * specified schedule and id-null.
     */
    public static EmpSchedule valueOf(Employee employee, AbstractSchedule schedule) {
        return new EmpSchedule(employee,
                schedule.getStartWorkTime(),
                schedule.getEndWorkTime(),
                schedule.getStartLunchTime(),
                schedule.getEndLunchTime());
    }

    /**
     * Initializes a newly created <b>EmpSchedule</b> object with null employee value
     * and null fields of the superclass.
     *
     * @see EmpSchedule#EmpSchedule(LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(Integer, Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(EmpSchedule)
     */
    public EmpSchedule() {
    }

    /**
     * Constructs a <b>EmpSchedule</b> object with specified start work time, end work time
     * start lunch time, end lunch time values,
     * null employee and id values.
     *
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule
     * @param endLunchTime   the end lunch time of the schedule.
     * @see EmpSchedule#EmpSchedule()
     * @see EmpSchedule#EmpSchedule(Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(Integer, Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(EmpSchedule)
     */
    public EmpSchedule(LocalTime startWorkTime, LocalTime endWorkTime,
                       LocalTime startLunchTime, LocalTime endLunchTime) {
        this(null, null, startWorkTime, endWorkTime, startLunchTime, endLunchTime);
    }

    /**
     * Constructs a <b>EmpSchedule</b> object with specified start work time, end work time
     * start lunch time, end lunch time
     * employee values and null id value.
     *
     * @param employee       the employee who has the schedule.
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule
     * @param endLunchTime   the end lunch time of the schedule.
     * @see EmpSchedule#EmpSchedule()
     * @see EmpSchedule#EmpSchedule(LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(Integer, Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(EmpSchedule)
     */
    public EmpSchedule(Employee employee,
                       LocalTime startWorkTime, LocalTime endWorkTime,
                       LocalTime startLunchTime, LocalTime endLunchTime) {
        this(null, employee, startWorkTime, endWorkTime, startLunchTime, endLunchTime);
    }

    /**
     * Constructs a <b>EmpSchedule</b> object with specified id,
     * start work time, end work time
     * start lunch time, end lunch time
     * and employee values.
     *
     * @param id             the specific id.
     * @param employee       the employee who has the schedule.
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule
     * @param endLunchTime   the end lunch time of the schedule.
     * @see EmpSchedule#EmpSchedule()
     * @see EmpSchedule#EmpSchedule(LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(EmpSchedule)
     */
    public EmpSchedule(Integer id,
                       Employee employee,
                       LocalTime startWorkTime, LocalTime endWorkTime,
                       LocalTime startLunchTime, LocalTime endLunchTime) {
        super(id, startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.employee = employee;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param schedule the specified object to copying.
     * @see EmpSchedule#EmpSchedule()
     * @see EmpSchedule#EmpSchedule(LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     * @see EmpSchedule#EmpSchedule(Integer, Employee, LocalTime, LocalTime, LocalTime, LocalTime)
     */
    public EmpSchedule(EmpSchedule schedule) {
        this(schedule.getId(),
                schedule.getEmployee(),
                schedule.getStartWorkTime(),
                schedule.getEndWorkTime(),
                schedule.getStartLunchTime(),
                schedule.getEndLunchTime());
    }

    /**
     * Returns a String object representing this <b>EmpSchedule</b> object.
     *
     * @return the String object representing this <b>EmpSchedule</b> object.
     */
    @Override
    public String toString() {
        return "EmpSchedule{" +
                "id=" + id +
                ", employee=" + employee +
                ", startWorkTime=" + startWorkTime +
                ", endWorkTime=" + endWorkTime +
                ", startLunchTime=" + startLunchTime +
                ", endLunchTime=" + endLunchTime +
                '}';
    }
}
