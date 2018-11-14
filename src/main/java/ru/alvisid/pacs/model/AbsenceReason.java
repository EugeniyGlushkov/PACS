package ru.alvisid.pacs.model;

public class AbsenceReason extends AbstractEntity {
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AbsenceReason() {
    }

    public AbsenceReason(String description, String reason) {
        super(description);
        this.reason = reason;
    }

    public AbsenceReason(Integer id, String description, String reason) {
        super(id, description);
        this.reason = reason;
    }

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

    @Override
    public String toString() {
        return "AbsenceReason{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
