package ru.alvisid.pacs.model;

public class Department extends AbstractEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department() {
        super();
    }

    public Department(String description, String name) {
        super(description);
        this.name = name;
    }

    public Department(Integer id, String description, String name) {
        super(id, description);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Department) {
            Department that = (Department) o;

            if (!name.equals(that.name)) {
                return false;
            }

            return super.equals(o);
        }

        return false;
    }
}
