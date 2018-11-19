package ru.alvisid.pacs.model;

import ru.alvisid.pacs.model.abstractions.AbstractPerson;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = "card_num", name = "employees_unique_card_num_idx"),
        @UniqueConstraint(columnNames = "email", name = "employees_unique_emale_idx")
})
public class Employee extends AbstractPerson {
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id", nullable = false)
    private Department department;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pos_id", nullable = false)
    private Position position;

    @NotNull
    @Column(name = "card_num", nullable = false, unique = true)
    private Integer cardNum;

    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    @Size(max = 100)
    @Email
    private String email;
    private EmpSchedule schedule;

    public Department getDepartment() {
        return department;
    }

    public Position getPosition() {
        return position;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public String getEmail() {
        return email;
    }

    public EmpSchedule getSchedule() {
        return schedule;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSchedule(EmpSchedule schedule) {
        this.schedule = schedule;
    }

    public Employee() {
    }

    public Employee(String lastName, String firstName, String secondName,
                    Department department, Position position, Integer cardNum, String email) {
        super(lastName, firstName, secondName);
        this.department = department;
        this.position = position;
        this.cardNum = cardNum;
        this.email = email;
    }

    public Employee(Integer id, String lastName, String firstName, String secondName,
                    Department department, Position position, Integer cardNum, String email) {
        super(id, lastName, firstName, secondName);
        this.department = department;
        this.position = position;
        this.cardNum = cardNum;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (!department.equals(employee.department)) return false;
        if (!position.equals(employee.position)) return false;
        if (!cardNum.equals(employee.cardNum)) return false;
        return email.equals(employee.email);
    }
}
