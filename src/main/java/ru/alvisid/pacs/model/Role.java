package ru.alvisid.pacs.model;

/**
 *Roles by which users get rights.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public enum Role {
    /**
     * Person who can read his own data.
     */
    ROLE_USER,

    /**
     * Person who can add visitors, and has access to security interface.
     */
    ROLE_SECURITY,

    /**
     * Person who can read his own department's data.
     */
    ROLE_DEPSREAD,

    /**
     * Person who can read all data.
     */
    ALL_READ,

    /**
     * Person who can add and edit employee's data.
     */
    ROLE_EMPSWRIGHT,

    /**
     * Person who can can add and edit system-wide data.
     */
    ROLE_ADMIN
}
