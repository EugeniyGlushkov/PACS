package ru.alvisid.pacs.model;

public class Employee extends AbstractPerson {
    private Department department;
    private Position position;
    private Integer cardNum;
    private String email;

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

    public Employee(){
    }

    public Employee(String lastName, String firstName, String secondtName,
                    Department department, Position position, Integer cardNum, String email) {
        super(lastName, firstName, secondtName);
        this.department = department;
        this.position = position;
        this.cardNum = cardNum;
        this.email = email;
    }

    public Employee(Integer id, String lastName, String firstName, String secondtName,
                    Department department, Position position, Integer cardNum, String email) {
        super(id, lastName, firstName, secondtName);
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
