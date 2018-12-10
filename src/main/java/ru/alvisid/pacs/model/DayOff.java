package ru.alvisid.pacs.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * A day when certain department don't work.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "days_off", uniqueConstraints =
@UniqueConstraint(columnNames = {"dep_id", "date"}, name = "depid_daysoff_idx"))
public class DayOff extends AbstractId {

    /**
     * The department to which the day off.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    /**
     * A date of the day off.
     */
    @NotNull
    @Column(name = "date", nullable = false)
    LocalDate dateOff;

    /**
     * Returns the current department.
     *
     * @return the current department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Returns the date of the day off.
     *
     * @return the date of the day off.
     */
    public LocalDate getDateOff() {
        return dateOff;
    }

    /**
     * Sets the current department.
     *
     * @param department the current department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Sets the date of the day off.
     *
     * @param dateOff the date of the day off.
     */
    public void setDateOff(LocalDate dateOff) {
        this.dateOff = dateOff;
    }

    /**
     * Initializes a newly created object with null id, department and date off.
     *
     * @see DayOff#DayOff(Department, LocalDate)
     * @see DayOff#DayOff(Integer, Department, LocalDate)
     * @see DayOff#DayOff(DayOff)
     */
    public DayOff() {
    }

    /**
     * Constructs a new <b>DayOff</b> object and sets the department and date off.
     *
     * @param department the current department.
     * @param dateOff    the date of the day off.
     * @see DayOff#DayOff()
     * @see DayOff#DayOff(Integer, Department, LocalDate)
     * @see DayOff#DayOff(DayOff)
     */
    public DayOff(Department department, LocalDate dateOff) {
        this(null, department, dateOff);
    }

    /**
     * Constructs a new <b>DayOff</b> object and sets the id, department and date off.
     *
     * @param id         the specifiec id.
     * @param department the current department.
     * @param dateOff    the date of the day off.
     * @see DayOff#DayOff()
     * @see DayOff#DayOff(Department, LocalDate)
     * @see DayOff#DayOff(DayOff)
     */
    public DayOff(Integer id, Department department, LocalDate dateOff) {
        super(id);
        this.department = department;
        this.dateOff = dateOff;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param dayOff the specified object to copying.
     * @see DayOff#DayOff()
     * @see DayOff#DayOff(Department, LocalDate)
     * @see DayOff#DayOff(Integer, Department, LocalDate)
     */
    public DayOff(DayOff dayOff) {
        this(dayOff.getId(),
                dayOff.getDepartment(),
                dayOff.getDateOff());
    }

    /**
     * Returns a String object representing this <b>DayOff</b> object.
     *
     * @return the String object representing this <b>DayOff</b> object.
     */
    @Override
    public String toString() {
        return "DayOff{" +
                "id=" + id +
                ", department=" + department +
                ", dateOff=" + dateOff +
                '}';
    }
}
