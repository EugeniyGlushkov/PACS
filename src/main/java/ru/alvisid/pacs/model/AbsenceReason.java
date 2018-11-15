package ru.alvisid.pacs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A reason of an employee's absence at workplace.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
@Entity
@Table(name = "absence_reasons")
public class AbsenceReason extends AbstractEntity {
    /**
     * A text title of the reason.
     */
    @NotBlank
    @Size(min = 2, max = 255)
    @Column(name = "reason", nullable = false, unique = true)
    private String reason;

    /**
     * Returnes the absence reason.
     *
     * @return the absence reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the absence reason.
     *
     * @param reason the absence reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Initializes a newly created <b>AbsenceReason</b> object with null ${@code reason} value
     * and null fiels of the superclass.
     *
     * @see AbsenceReason#AbsenceReason(String, String)
     * @see AbsenceReason#AbsenceReason(Integer, String, String)
     */
    public AbsenceReason() {
    }

    /**
     * Constructs a <b>AbsenceReason</b> object with specified description, reason and null id values.
     *
     * @param description the action type's description.
     * @param reason      the reason value.
     * @see AbsenceReason#AbsenceReason()
     * @see AbsenceReason#AbsenceReason(Integer, String, String)
     */
    public AbsenceReason(String description, String reason) {
        this(null, description, reason);
    }

    /**
     * Constructs a <b>AbsenceReason</b> object with specified id, description and reason values.
     *
     * @param id          the specifiec identifier.
     * @param description the action type's description.
     * @param reason      the reason value.
     */
    public AbsenceReason(Integer id, String description, String reason) {
        super(id, description);
        this.reason = reason;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>AbsenceReason</b> object that contains the same ${@code reason} value as this object,
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

        AbsenceReason that = (AbsenceReason) o;

        return reason.equals(that.reason);
    }

    /**
     * Returns a String object representing this <b>AbsenceReason</b>'s object.
     *
     * @return the String object representing this <b>AbsenceReason</b>'s object.
     */
    @Override
    public String toString() {
        return "AbsenceReason{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
