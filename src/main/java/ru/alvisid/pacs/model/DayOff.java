package ru.alvisid.pacs.model;

import org.hibernate.Hibernate;

import java.time.LocalDate;

/**
 * A day when certain department don't work.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DayOff {
    /**
     * The cpecifiec identifier of the day off.
     */
    private Integer id;

    /**
     * The department.
     */
    private Department department;

    /**
     * A date of the day off.
     */
    LocalDate dateOff;

    /**
     * Gets the specifiec id.
     *
     * @return the specifiec id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the current department.
     *
     * @return the current department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Gets the date of the day off.
     *
     * @return the date of the day off.
     */
    public LocalDate getDateOff() {
        return dateOff;
    }

    /**
     * Sets the specifiec id.
     *
     * @param id the specified identifier.
     */
    public void setId(Integer id) {
        this.id = id;
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
     */
    public DayOff() {
    }

    /**
     * Constructs entity and sets the department and date off.
     *
     * @param department the current department.
     * @param dateOff    the date of the day off.
     * @see DayOff#DayOff()
     * @see DayOff#DayOff(Integer, Department, LocalDate)
     */
    public DayOff(Department department, LocalDate dateOff) {
        this.department = department;
        this.dateOff = dateOff;
    }

    /**
     * Constructs entity and sets the id, department and date off.
     *
     * @param id         the specifiec id.
     * @param department the current department.
     * @param dateOff    the date of the day off.
     * @see DayOff#DayOff()
     * @see DayOff#DayOff(Department, LocalDate)
     */
    public DayOff(Integer id, Department department, LocalDate dateOff) {
        this.id = id;
        this.department = department;
        this.dateOff = dateOff;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>DayOff</b> object
     * that contains the same id, department and date off values as this object.
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

        DayOff dayOff = (DayOff) o;

        if (id != null ? !id.equals(dayOff.id) : dayOff.id != null) {
            return false;
        }

        if (!department.equals(dayOff.department)) {
            return false;
        }

        return dateOff.equals(dayOff.dateOff);
    }

    /**
     * Returns a hash code for this Entity.
     *
     * @return the hash code for this Entity.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns a String object representing this <b>DayOff</b>'s object.
     *
     * @return the String object representing this <b>DayOff</b>'s object.
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
