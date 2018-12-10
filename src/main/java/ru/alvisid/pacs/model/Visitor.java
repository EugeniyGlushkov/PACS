package ru.alvisid.pacs.model;

import org.hibernate.annotations.Check;
import ru.alvisid.pacs.model.abstractions.AbstractPerson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * A person who don't work there and haven't got a pass.
 */
@Entity
@Table(name = "visitors")
@Check(constraints = "(NOT (enter_time IS NULL AND exit_time IS NOT NULL))")
public class Visitor extends AbstractPerson {
    /**
     * A person temporary number which given everyone visitor.
     * It can includes letter and numeric characters.
     * Min value 2 characters, max value 20 characters.
     * Must be non null and has least one non space symbol.
     */
    @NotBlank
    @Column(name = "temp_num", unique = true, nullable = false)
    @Size(min = 2, max = 20)
    private String tempNum;

    /**
     * A purpose of the visit and additional information about the visit and the visitor.
     * Min value 2 characters.
     * Must be non null and has least one non space symbol.
     */
    @NotBlank
    @Column(name = "description", nullable = false)
    @Size(min = 2)
    private String description;

    /**
     * The time when the visitor enter.
     */
    @Column(name = "enter_time", nullable = false)
    private LocalDateTime enterTime;

    /**
     * The time when the visitor exit.
     */
    @Column(name = "exit_time", nullable = false)
    private LocalDateTime exitTime;

    /**
     * Returns the temporary number of the visitor.
     *
     * @return the temporary number of the visitor.
     */
    public String getTempNum() {
        return tempNum;
    }

    /**
     * Returns the description of the visitor.
     *
     * @return the description of the visitor.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the visit's enter time.
     *
     * @return the visit's enter time.
     */
    public LocalDateTime getEnterTime() {
        return enterTime;
    }

    /**
     * Returns the visit's exit time.
     *
     * @return the visit's exit time.
     */
    public LocalDateTime getExitTime() {
        return exitTime;
    }

    /**
     * Sets the specified value to the temporary number.
     *
     * @param tempNum the temporary number.
     */
    public void setTempNum(String tempNum) {
        this.tempNum = tempNum;
    }

    /**
     * Sets the specified value to the description.
     *
     * @param description the new  description value.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the specified value of the enter time.
     *
     * @param enterTime the new enter time value.
     */
    public void setEnterTime(LocalDateTime enterTime) {
        this.enterTime = enterTime;
    }

    /**
     * Sets the specified value of the exit time.
     *
     * @param exitTime the new exit time value.
     */
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    /**
     * Initializes a newly created <b>Visitor</b> object with null fields
     * and null fields of the superclass.
     *
     * @see Visitor#Visitor(String, String, String, String, String, LocalDateTime, LocalDateTime)
     * @see Visitor#Visitor(Integer, String, String, String, String, String, LocalDateTime, LocalDateTime)
     * @see Visitor#Visitor(Visitor)
     */
    public Visitor() {
    }

    /**
     * Constructs an <b>Visitor</b> object with specified lastName, firstName,
     * secondName, tempNum, description, enterTime, exitTime values
     * and null id value.
     *
     * @param lastName    the person's last name.
     * @param firstName   the person's first name.
     * @param secondName  the person's second name.
     * @param tempNum     the temporary number.
     * @param description the new  description value.
     * @param enterTime   the new enter time value.
     * @param exitTime    the new exit time value.
     * @see Visitor#Visitor()
     * @see Visitor#Visitor(Integer, String, String, String, String, String, LocalDateTime, LocalDateTime)
     * @see Visitor#Visitor(Visitor)
     */
    public Visitor(String lastName, String firstName, String secondName,
                   String tempNum, String description,
                   LocalDateTime enterTime, LocalDateTime exitTime) {
        this(null,
                lastName, firstName, secondName,
                tempNum, description,
                enterTime, exitTime);
    }

    /**
     * @param id          the specifiec id.
     * @param lastName    the person's last name.
     * @param firstName   the person's first name.
     * @param secondName  the person's second name.
     * @param tempNum     the temporary number.
     * @param description the new  description value.
     * @param enterTime   the new enter time value.
     * @param exitTime    the new exit time value.
     * @see Visitor#Visitor()
     * @see Visitor#Visitor(String, String, String, String, String, LocalDateTime, LocalDateTime)
     * @see Visitor#Visitor(Visitor)
     */
    public Visitor(Integer id,
                   String lastName, String firstName, String secondName,
                   String tempNum, String description,
                   LocalDateTime enterTime, LocalDateTime exitTime) {
        super(id, lastName, firstName, secondName);
        this.tempNum = tempNum;
        this.description = description;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param visitor the specified object to copying.
     * @see Visitor#Visitor()
     * @see Visitor#Visitor(String, String, String, String, String, LocalDateTime, LocalDateTime)
     * @see Visitor#Visitor(Integer, String, String, String, String, String, LocalDateTime, LocalDateTime)
     */
    public Visitor(Visitor visitor) {
        this(visitor.getId(),
                visitor.getLastName(),
                visitor.getFirstName(),
                visitor.getSecondName(),
                visitor.getTempNum(),
                visitor.getDescription(),
                visitor.getEnterTime(),
                visitor.getExitTime());
    }

    /**
     * Returns a String object representing this <b>Visitor</b> object.
     *
     * @return the String object representing this <b>Visitor</b> object.
     */
    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", tempNum='" + tempNum + '\'' +
                ", description='" + description + '\'' +
                ", enterTime=" + enterTime +
                ", exitTime=" + exitTime +
                '}';
    }
}
