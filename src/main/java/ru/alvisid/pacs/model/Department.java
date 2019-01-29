package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * A department with a specifiec name.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "departments", uniqueConstraints =
@UniqueConstraint(columnNames = "name", name = "departments_unique_name_idx"))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department extends AbstractEntity {
    /**
     * The name of the department.
     * Min value is 1 character, max value is 255 characters.
     * Must be non null, unique and has least one non space symbol.
     */
    @NotBlank
    @Size(min = 2, max = 255)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * The department's chief.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chief")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Employee chief;

    /**
     * List of the outputs: week days when the department don't work constantly.
     */
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "weekends", joinColumns = @JoinColumn(name = "dep_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"dep_id", "weekday_id"}, name = "depid_weekdayid_idx"))
    @Column(name = "weekday_id")
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderBy("ASC")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List <WeekDay> weekEnds;

    /**
     * The department's schedule
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.REMOVE, orphanRemoval = true)
    //@NotNull
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
     * Returns the department's chief.
     *
     * @return the department's chief.
     */
    public Employee getChief() {
        return chief;
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
     * Sets the department's chief.
     *
     * @param chief the department's chief.
     */
    public void setChief(Employee chief) {
        this.chief = chief;
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
     * @see Department#Department(String, Employee, String)
     * @see Department#Department(Integer, String, Employee, String)
     * @see Department#Department(Integer, String, Employee, String, List, DeptSchedule)
     * @see Department#Department(Department)
     */
    public Department() {
    }

    /**
     * Constructs a <b>Department</b> object with specified description, name
     * chief and null id, weekends, department's schedule values.
     *
     * @param name        the department's name.
     * @param chief       the department's chief.
     * @param description the departmen's description.
     * @see Department#Department()
     * @see Department#Department(Integer, String, Employee, String)
     * @see Department#Department(Integer, String, Employee, String, List, DeptSchedule)
     * @see Department#Department(Department)
     */
    public Department(String name, Employee chief, String description) {
        this(null, name, chief, description);
    }

    /**
     * Constructs a <b>Department</b> object with specified id, description, name, chief values
     * and null weekends, department's schedule values.
     *
     * @param id          the specific identifier.
     * @param name        the department's name.
     * @param chief       the department's chief.
     * @param description the department's description.
     * @see Department#Department()
     * @see Department#Department(String, Employee, String)
     * @see Department#Department(Integer, String, Employee, String, List, DeptSchedule)
     * @see Department#Department(Department)
     */
    public Department(Integer id, String name, Employee chief, String description) {
        this(id, name, chief, description, null, null);
    }

    /**
     * Constructs a <b>Department</b> object with specified id, description, name,
     * chief, department's weekends and schedule values.
     *
     * @param id           the specific identifier.
     * @param name         the department's name.
     * @param chief        the department's chief.
     * @param description  the department's description.
     * @param weekEnds     the weekends.
     * @param deptSchedule the department's schedule.
     * @see Department#Department()
     * @see Department#Department(String, Employee, String)
     * @see Department#Department(Integer, String, Employee, String)
     * @see Department#Department(Department)
     */
    public Department(Integer id, String name, Employee chief, String description, List <WeekDay> weekEnds, DeptSchedule deptSchedule) {
        super(id, description);
        this.name = name;
        this.chief = chief;
        this.weekEnds = weekEnds;
        this.deptSchedule = deptSchedule;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param department the specified object to copying.
     * @see Department#Department()
     * @see Department#Department(String, Employee, String)
     * @see Department#Department(Integer, String, Employee, String)
     * @see Department#Department(Integer, String, Employee, String, List, DeptSchedule)
     */
    public Department(Department department) {
        this(department.getId(),
                department.getName(),
                department.getChief(),
                department.getDescription(),
                Objects.isNull(department.getWeekEnds()) ? null : new ArrayList <>(department.getWeekEnds()),
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
                ", chief='" + chief + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
