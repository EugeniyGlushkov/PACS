package ru.alvisid.pacs.model.enumActivate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Abstraction for dictionaries.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractDictionary {
    /**
     * The cpecifiec identifier for each member of the dictionary.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The code of the dictionary's member.
     */
    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    /**
     * Returns the specifiec identifier of the dictionary's member.
     *
     * @return the specifiec identifier of the dictionary's member.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the specified value of the specifiec identifier.
     *
     * @param id the specified value of the specifiec identifier.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the code of the dictionary's member.
     *
     * @return the code of the dictionary's member.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the specified value of the code of the dictionary's member.
     *
     * @param code the specified value of the code of the dictionary's member.
     */
    public void setCode(String code) {
        this.code = code;
    }
}
