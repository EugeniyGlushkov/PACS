package ru.alvisid.pacs.model;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractHasEmpEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * The employee's absence which presents interval and reason of the absence.
 */
@Entity
@Table(name = "absences", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"emp_id", "start_absence"}, name = "abs_empid_start_idx"),
        @UniqueConstraint(columnNames = {"emp_id", "end_absence"}, name = "abs_empid_end_idx")
})
@Check(constraints = "start_absence < end_absence")
public class Absence extends AbstractHasEmpEntity {
    /**
     * The reason of the absence.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reason_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private AbsenceReason absenceReason;

    /**
     * A start date of the absence.
     */
    @NotNull
    @Column(name = "start_absence", nullable = false)
    private LocalDate startAbsenceDate;

    /**
     * An end date of the absence.
     */
    @NotNull
    @Column(name = "end_absence", nullable = false)
    private LocalDate endAbsenceDate;

    /**
     * Returns the reason of the absence.
     *
     * @return the reason of the absence.
     */
    public AbsenceReason getAbsenceReason() {
        return absenceReason;
    }

    /**
     * Returns the start date of the absence.
     *
     * @return the start date of the absence.
     */
    public LocalDate getStartAbsenceDate() {
        return startAbsenceDate;
    }

    /**
     * Returns the end date of the absence.
     *
     * @return the end date of the absence.
     */
    public LocalDate getEndAbsenceDate() {
        return endAbsenceDate;
    }

    /**
     * Sets specified value to {@code absenceReason} field.
     *
     * @param absenceReason the new value of the absenceReason.
     */
    public void setAbsenceReason(AbsenceReason absenceReason) {
        this.absenceReason = absenceReason;
    }

    /**
     * Sets specified value to @{@code startAbsenceDate} field.
     *
     * @param startAbsenceDate the new value of the startAbsenceDate.
     */
    public void setStartAbsenceDate(LocalDate startAbsenceDate) {
        this.startAbsenceDate = startAbsenceDate;
    }

    /**
     * Sets specified value to {@code endAbsenceDate} field
     *
     * @param endAbsenceDate the new value of the endAbsenceDate.
     */
    public void setEndAbsenceDate(LocalDate endAbsenceDate) {
        this.endAbsenceDate = endAbsenceDate;
    }

    /**
     * Initializes a newly created <b>Absence</b> object with null fields
     * and null fields of the superclass.
     *
     * @see Absence#Absence(String, Employee, AbsenceReason, LocalDate, LocalDate)
     * @see Absence#Absence(Integer, String, Employee, AbsenceReason, LocalDate, LocalDate)
     */
    public Absence() {
    }

    /**
     * Constructs an <b>Absence</b> object with specified description, employee,
     * absenceReason, startAbsenceDate, endAbsenceDate values
     * and null id value.
     *
     * @param description      the description of the entity.
     * @param employee         the specified parameter of the employee.
     * @param absenceReason    the value of the absenceReason.
     * @param startAbsenceDate the value of the startAbsenceDate.
     * @param endAbsenceDate   the value of the endAbsenceDate.
     * @see Absence#Absence()
     * @see Absence#Absence(Integer, String, Employee, AbsenceReason, LocalDate, LocalDate)
     */
    public Absence(String description,
                   Employee employee,
                   AbsenceReason absenceReason,
                   LocalDate startAbsenceDate,
                   LocalDate endAbsenceDate) {
        this(null,
                description,
                employee,
                absenceReason,
                startAbsenceDate,
                endAbsenceDate);
    }

    /**
     * Constructs an <b>Absence</b> object with specified id, description, employee,
     * absenceReason, startAbsenceDate and endAbsenceDate values.
     *
     * @param id               the specifiec id.
     * @param description      the description of the entity.
     * @param employee         the specified parameter of the employee.
     * @param absenceReason    the value of the absenceReason.
     * @param startAbsenceDate the value of the startAbsenceDate.
     * @param endAbsenceDate   the value of the endAbsenceDate.
     * @see Absence#Absence()
     * @see Absence#Absence(String, Employee, AbsenceReason, LocalDate, LocalDate)
     */
    public Absence(Integer id,
                   String description,
                   Employee employee,
                   AbsenceReason absenceReason,
                   LocalDate startAbsenceDate,
                   LocalDate endAbsenceDate) {
        super(id, description, employee);
        this.absenceReason = absenceReason;
        this.startAbsenceDate = startAbsenceDate;
        this.endAbsenceDate = endAbsenceDate;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>Absence</b> object that contains the same
     * {@code absenceReason}, {@code startAbsenceDate}, {@code endAbsenceDate} value as this object,
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

        if (!super.equals(o)) return false;

        Absence absence = (Absence) o;

        if (!absenceReason.equals(absence.absenceReason)) {
            return false;
        }

        if (!startAbsenceDate.equals(absence.startAbsenceDate)) {
            return false;
        }

        return endAbsenceDate.equals(absence.endAbsenceDate);
    }

    /**
     * Returns a String object representing this <b>Absence</b> object.
     *
     * @return the String object representing this <b>Absence</b> object.
     */
    @Override
    public String toString() {
        return "Absence{" +
                "id=" + id +
                ", employee=" + employee +
                ", absenceReason=" + absenceReason +
                ", startAbsenceDate=" + startAbsenceDate +
                ", endAbsenceDate=" + endAbsenceDate +
                ", description='" + description + '\'' +
                '}';
    }
}
