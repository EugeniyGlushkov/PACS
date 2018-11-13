package ru.alvisid.pacs.model;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "dep_schedules", uniqueConstraints =
@UniqueConstraint(columnNames = "dep_id", name = "depsched_unique_depid_idx"))
@Check(constraints = "start_work < start_lunch\n" +
        "AND start_lunch < end_lunch\n" +
        "AND end_lunch < end_work")
public class DeptSchedule extends AbstractSchedule{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public DeptSchedule() {
        super();
    }

    public DeptSchedule(LocalTime startWorkTime, LocalTime endWorkTime,
                        LocalTime startLunchTime, LocalTime endLunchTime,
                        Department department) {
        super(startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.department = department;
    }

    public DeptSchedule(Integer id,
                        LocalTime startWorkTime, LocalTime endWorkTime,
                        LocalTime startLunchTime, LocalTime endLunchTime,
                        Department department) {
        super(id, startWorkTime, endWorkTime, startLunchTime, endLunchTime);
        this.department = department;
    }

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
