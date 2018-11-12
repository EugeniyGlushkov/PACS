package ru.alvisid.pacs.model;

import java.time.LocalTime;

public class DeptSchedule extends AbstractSchedule{
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
