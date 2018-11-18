package ru.alvisid.pacs.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.pacs.model.abstractions.AbstractHasEmpEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * An edit which done by current employee at current time.
 */
@Entity
@Table(name = "edits", uniqueConstraints =
@UniqueConstraint(columnNames = {"emp_id", "edit_date"}, name = "edits_emp_date_idx"))
public class Edit extends AbstractHasEmpEntity {
    /**
     * Type of the edit.
     */
    @NotNull
    @JoinColumn(name = "type_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EditType editType;

    /**
     * Time when the edit was done.
     */
    @NotNull
    @Column(name = "edit_date", nullable = false)
    @Immutable
    private LocalDateTime editDateTime;

    /**
     * Returns the type of the edit.
     *
     * @return the type of the edit.
     */
    public EditType getEditType() {
        return editType;
    }

    /**
     * Returns the time when the edit was done.
     *
     * @return the time when the edit was done.
     */
    public LocalDateTime getEditDateTime() {
        return editDateTime;
    }

    /**
     * Sets the specified value of the edit type.
     *
     * @param editType the specified value of the edit type.
     */
    public void setEditType(EditType editType) {
        this.editType = editType;
    }

    /**
     * Sets the specified value of the time when the edit was done.
     *
     * @param editDateTime the specified value of the time when the edit was done.
     */
    public void setEditDateTime(LocalDateTime editDateTime) {
        this.editDateTime = editDateTime;
    }

    /**
     * Initializes a newly created <b>Edit</b> object with null fields
     * and null fields of the superclass.
     *
     * @see Edit#Edit(String, Employee, EditType, LocalDateTime)
     * @see Edit#Edit(Integer, String, Employee, EditType, LocalDateTime)
     */
    public Edit() {
    }

    /**
     * Constructs an <b>Edit</b> object with specified
     * description, employee, editType, editDateTime values
     * and null id value.
     *
     * @param description  the description of the entity.
     * @param employee     the specified parameter of the employee.
     * @param editType     the specified value of the edit type.
     * @param editDateTime the specified value of the time when the edit was done.
     * @see Edit#Edit()
     * @see Edit#Edit(Integer, String, Employee, EditType, LocalDateTime)
     */
    public Edit(String description,
                Employee employee,
                EditType editType,
                LocalDateTime editDateTime) {
        this(null,
                description,
                employee,
                editType,
                editDateTime);
    }

    /**
     * Constructs an <b>Edit</b> object with specified
     * id, description, employee, editType and editDateTime values.
     *
     * @param id           the specifiec id.
     * @param description  the description of the entity.
     * @param employee     the specified parameter of the employee.
     * @param editType     the specified value of the edit type.
     * @param editDateTime the specified value of the time when the edit was done.
     * @see Edit#Edit()
     * @see Edit#Edit(String, Employee, EditType, LocalDateTime)
     */
    public Edit(Integer id,
                String description,
                Employee employee,
                EditType editType,
                LocalDateTime editDateTime) {
        super(id, description, employee);
        this.editType = editType;
        this.editDateTime = editDateTime;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>Edit</b> object that contains the same
     * {@code editType}, {@code editDateTime} values as this object,
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

        Edit edit = (Edit) o;

        if (editType != edit.editType) {
            return false;
        }

        return editDateTime.equals(edit.editDateTime);
    }

    /**
     * Returns a String object representing this <b>Edit</b> object.
     *
     * @return the String object representing this <b>Edit</b> object.
     */
    @Override
    public String toString() {
        return "Edit{" +
                "id=" + id +
                ", editType=" + editType +
                ", employee=" + employee +
                ", editDateTime=" + editDateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
