package ru.alvisid.pacs.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Edit extends AbstractHasEmpEntity {
    /**
     * Type of the edit.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private EditType editType;

    /**
     * Time when the edit was done.
     */
    @NotNull
    @Column(name = "edit_date", nullable = false)
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
     * @see Edit#Edit(Edit)
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
     * @see Edit#Edit(Edit)
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
     * @see Edit#Edit(Edit)
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
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param edit the specified object to copying.
     * @see Edit#Edit()
     * @see Edit#Edit(String, Employee, EditType, LocalDateTime)
     * @see Edit#Edit(Integer, String, Employee, EditType, LocalDateTime)
     */
    public Edit(Edit edit) {
        this(edit.getId(),
                edit.getDescription(),
                edit.getEmployee(),
                edit.getEditType(),
                edit.getEditDateTime());
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
