package ru.alvisid.pacs.model;

/**
 *Roles by which users get rights.
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
     * Person who can add and edit his own department's data.
     */
    ROLE_DEPSWRIGHT,

    /**
     * Person who can can add and edit system-wide data.
     */
    ROLE_ADMIN
}
