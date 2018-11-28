package ru.alvisid.pacs.model.abstractions;

/**
 * Object can has identifier - id.
 */
public interface HasId {
    /**
     * Returns the specifiec id.
     *
     * @return the specifiec id.
     */
    Integer getId();

    /**
     * Sets the specifiec id.
     *
     * @param id the specifiec id.
     */
    void setId(Integer id);
}
