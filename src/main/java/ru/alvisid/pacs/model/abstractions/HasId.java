package ru.alvisid.pacs.model.abstractions;

/**
 * The functional for objects which have <b>id</b>.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
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

    /**
     * Returns {@code true} if id is null.
     *
     * @return {@code true} if id is null.
     */
    public boolean isNew();
}
