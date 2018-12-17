package ru.alvisid.pacs.util.profileResolver;

/**
 * Keeps and gets DB profiles.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class Profiles {
    /**
     * Profile's name of the postgres DB.
     */
    public static final String POSTGRES_DB = "postgres";

    /**
     * Profile's name of the hsql DB.
     */
    public static final String HSQL_DB = "hsqldb";

    /**
     * Returns a DB profile depending of the DB driver in the classpath.
     *
     * @return the DB profile depending of the DB driver in the classpath.
     * @throws IllegalStateException if do not find any DB driver in the classpath.
     */
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES_DB;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return HSQL_DB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver!");
            }
        }
    }
}
