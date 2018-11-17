package ru.alvisid.pacs.model;

import org.hibernate.annotations.Check;
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
public class EmpSchedule extends AbstractSchedule {
    /**
     * The specific employee.
     * Must be non null.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    /**
     * Gets the specific employee.
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
     * Initializes a newly created <b>EmpSchedule</b> object with null employee value
     * and null fields of the superclass.
     *
     * @see EmpSchedule#EmpSchedule(LocalTime, LocalTime, LocalTime, LocalTime, Employee)
     * @see EmpSchedule#EmpSchedule(Integer, LocalTime, LocalTime, LocalTime, LocalTime, Employee)
     */
    public EmpSchedule() {
    }

    /**
     * Constructs a <b>EmpSchedule</b> object with specified start work time, end work time
     * start lunch time, end lunch time
     * employee values and null id value.
     *
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule
     * @param endLunchTime   the end lunch time of the schedule.
     * @param employee       the employee who has the schedule.
     */
    public EmpSchedule(LocalTime startWorkTime, LocalTime endWorkTime,
                       LocalTime startLunchTime, LocalTime endLunchTime,
                       Employee employee) {
        super(startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.employee = employee;
    }

    /**
     * Constructs a <b>EmpSchedule</b> object with specified id,
     * start work time, end work time
     * start lunch time, end lunch time
     * and employee values.
     *
     * @param id             the specifiec id.
     * @param startWorkTime  the start work time of the schedule.
     * @param endWorkTime    the end work time of the schedule.
     * @param startLunchTime the start lunch time of the schedule
     * @param endLunchTime   the end lunch time of the schedule.
     * @param employee       the employee who has the schedule.
     */
    public EmpSchedule(Integer id,
                       LocalTime startWorkTime, LocalTime endWorkTime,
                       LocalTime startLunchTime, LocalTime endLunchTime,
                       Employee employee) {
        super(id, startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.employee = employee;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>EmpSchedule</b> object that contains the same {@code employee} value as this object,
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

        EmpSchedule that = (EmpSchedule) o;

        return employee.equals(that.employee);
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
