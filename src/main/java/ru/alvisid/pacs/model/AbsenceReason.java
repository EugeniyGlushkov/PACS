package ru.alvisid.pacs.model;

import ru.alvisid.pacs.model.abstractions.AbstractEntity;

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
     * Returns the absence reason.
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
     * Initializes a newly created <b>AbsenceReason</b> object with null {@code reason} value
     * and null fiels of the superclass.
     *
     * @see AbsenceReason#AbsenceReason(String, String)
     * @see AbsenceReason#AbsenceReason(Integer, String, String)
     * @see AbsenceReason#AbsenceReason(AbsenceReason)
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
     * @see AbsenceReason#AbsenceReason(AbsenceReason)
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
     * @see AbsenceReason#AbsenceReason()
     * @see AbsenceReason#AbsenceReason(String, String)
     * @see AbsenceReason#AbsenceReason(AbsenceReason)
     */
    public AbsenceReason(Integer id, String description, String reason) {
        super(id, description);
        this.reason = reason;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param absenceReason the specified object to copying.
     * @see AbsenceReason#AbsenceReason()
     * @see AbsenceReason#AbsenceReason(String, String)
     * @see AbsenceReason#AbsenceReason(Integer, String, String)
     */
    public AbsenceReason(AbsenceReason absenceReason) {
        this(absenceReason.getId(),
                absenceReason.getDescription(),
                absenceReason.getReason());
    }

    /**
     * Returns a String object representing this <b>AbsenceReason</b> object.
     *
     * @return the String object representing this <b>AbsenceReason</b> object.
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
