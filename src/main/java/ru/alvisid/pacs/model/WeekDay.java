package ru.alvisid.pacs.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *Represent the weekdays.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */

public enum WeekDay {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

//del
/*@Entity
@Access(AccessType.FIELD)
@Table(name = "week_days")
public enum WeekDay {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    WeekDay() {
    }

    WeekDay(Integer id) {
        this.id = id;
        this.name = String.valueOf(this);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}*/
