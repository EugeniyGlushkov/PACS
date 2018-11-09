package ru.alvisid.pacs.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
     * Returnes the department's name.
     *
     * @return the department's name.
     */
    public String getName() {
        return name;
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
     * Initializes a newly created <b>Department</b> object with null name value and null fiels of the superclass.
     *
     * @see Department#Department(String, String)
     * @see Department#Department(Integer, String, String)
     */
    public Department() {
        super();
    }

    /**
     * Constructs a <b>Department</b> object with specified desciption, name and null id value.
     *
     * @param description the departmen's description.
     * @param name        the department's name.
     * @see Department#Department()
     * @see Department#Department(Integer, String, String)
     */
    public Department(String description, String name) {
        super(description);
        this.name = name;
    }

    /**
     * Constructs a <b>Department</b> object with specified id, desciption and name values.
     *
     * @param id          the specifiec identifier.
     * @param description the departmen's description.
     * @param name        the department's name.
     * @see Department#Department()
     * @see Department#Department(String, String)
     */
    public Department(Integer id, String description, String name) {
        super(id, description);
        this.name = name;
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is an <b>Department</b> object that contains the same name value as this object,
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

        Department that = (Department) o;

        return name.equals(that.name);
    }

    /**
     * Returns a String object representing this <b>Department</b>'s object.
     *
     * @return the String object representing this <b>Department</b>'s object.
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
