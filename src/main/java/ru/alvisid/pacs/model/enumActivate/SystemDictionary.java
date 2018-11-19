package ru.alvisid.pacs.model.enumActivate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class SystemDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
