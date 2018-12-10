package ru.alvisid.pacs.model;

import org.hibernate.Hibernate;
import ru.alvisid.pacs.model.abstractions.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * A department with a specifiec name.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "departments", uniqueConstraints =
@UniqueConstraint(columnNames = "name", name = "departments_unique_name_idx"))
public class Department extends AbstractEntity {
    /**
     * The name of the department.
     * Min value is 1 character, max value is 255 characters.
     * Must be non null, unique and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * List of the outputs: week days when the department don't work constantly.
     */
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "weekends", joinColumns = @JoinColumn(name = "dep_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"dep_id", "weekday_id"}, name = "depid_weekdayid_idx"))
    @Column(name = "weekday_id")
    @ElementCollection(fetch = FetchType.LAZY)
    private List <WeekDay> weekEnds;

    /**
     * The department's schedule
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @NotNull
    private DeptSchedule deptSchedule;

    /**
     * Returns the department's name.
     *
     * @return the department's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of the departmet's weekends.
     *
     * @return the list of the departmet's weekends.
     */
    public List <WeekDay> getWeekEnds() {
        return weekEnds;
    }

    /**
     * Returns the department's schedule;
     *
     * @return the department's schedule;
     */
    public DeptSchedule getDeptSchedule() {
        return deptSchedule;
    }

    /**
     * Sets the department's name.
     *
     * @param name the department's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the list of the departmet's weekends.
     *
     * @param weekEnds the list of the departmet's weekends.
     */
    public void setWeekEnds(List <WeekDay> weekEnds) {
        this.weekEnds = weekEnds;
    }

    /**
     * Sets the department's schedule.
     *
     * @param deptSchedule the department's schedule.
     */
    public void setDeptSchedule(DeptSchedule deptSchedule) {
        this.deptSchedule = deptSchedule;
    }

    /**
     * Initializes a newly created <b>Department</b> object with null name,
     * weekends, department's schedule values and null fields of the superclass.
     *
     * @see Department#Department(String, String)
     * @see Department#Department(Integer, String, String)
     * @see Department#Department(Integer, String, String, List, DeptSchedule)
     * @see Department#Department(Department)
     */
    public Department() {
    }

    /**
     * Constructs a <b>Department</b> object with specified description, name
     * and null id, weekends, department's schedule values.
     *
     * @param description the departmen's description.
     * @param name        the department's name.
     * @see Department#Department()
     * @see Department#Department(Integer, String, String)
     * @see Department#Department(Integer, String, String, List, DeptSchedule)
     * @see Department#Department(Department)
     */
    public Department(String description, String name) {
        super(description);
        this.name = name;
    }

    /**
     * Constructs a <b>Department</b> object with specified id, description, name values
     * and null weekends, department's schedule values.
     *
     * @param id          the specifiec identifier.
     * @param description the department's description.
     * @param name        the department's name.
     * @see Department#Department()
     * @see Department#Department(String, String)
     * @see Department#Department(Integer, String, String, List, DeptSchedule)
     * @see Department#Department(Department)
     */
    public Department(Integer id, String description, String name) {
        this(id, description, name, null, null);
    }

    /**
     * Constructs a <b>Department</b> object with specified id, description, name,
     * weekends department's and schedule values.
     *
     * @param id           the specifiec identifier.
     * @param description  the department's description.
     * @param name         the department's name.
     * @param weekEnds     the weekends.
     * @param deptSchedule the department's schedule.
     * @see Department#Department()
     * @see Department#Department(String, String)
     * @see Department#Department(Integer, String, String)
     * @see Department#Department(Department)
     */
    public Department(Integer id, String description, String name, List <WeekDay> weekEnds, DeptSchedule deptSchedule) {
        super(id, description);
        this.name = name;
        this.weekEnds = weekEnds;
        this.deptSchedule = deptSchedule;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param department the specified object to copying.
     * @see Department#Department()
     * @see Department#Department(String, String)
     * @see Department#Department(Integer, String, String)
     * @see Department#Department(Integer, String, String, List, DeptSchedule)
     */
    public Department(Department department) {
        this(department.getId(),
                department.getDescription(),
                department.getName(),
                department.getWeekEnds(),
                department.getDeptSchedule());
    }

    /**
     * Returns a String object representing this <b>Department</b> object.
     *
     * @return the String object representing this <b>Department</b> object.
     */
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
