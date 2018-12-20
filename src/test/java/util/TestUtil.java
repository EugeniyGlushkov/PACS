package util;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Miscellaneous utility test methods.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class TestUtil {
    /**
     * Checks matching the specified actual parameter for the specified expected parameter.
     *
     * @param ignoringFields the names of the fields for excluding from the matching, may be empty.
     * @param actual         the specified actual parameter.
     * @param expected       the specified expected parameter.
     * @param <T>            the type of the checked parameters.
     * @see TestUtil#assertMatch(String[], Iterable, Object[])
     * @see TestUtil#assertMatch(String[], Iterable, Iterable)
     */
    public static <T> void assertMatch(String[] ignoringFields, T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, ignoringFields);
    }

    /**
     * Checks matching the specified actual parameter's set for the specified expected parameter's set.
     *
     * @param ignoringFields the names of the fields for excluding from the matching, may be empty.
     * @param actual         the specified actual parameter's set.
     * @param expected       the specified expected parameter's set.
     * @param <T>            the type of the checked parameters.
     * @see TestUtil#assertMatch(String[], Object, Object)
     * @see TestUtil#assertMatch(String[], Iterable, Iterable)
     */
    public static <T> void assertMatch(String[] ignoringFields, Iterable <T> actual, T... expected) {
        assertMatch(ignoringFields, actual, List.of(expected));
    }

    /**
     * Checks matching the specified actual parameter's set for the specified expected parameter's set.
     *
     * @param ignoringFields the names of the fields for excluding from the matching, may be empty.
     * @param actual         the specified actual parameter's set.
     * @param expected       the specified expected parameter's set.
     * @param <T>            the type of the checked parameters.
     * @see TestUtil#assertMatch(String[], Object, Object)
     * @see TestUtil#assertMatch(String[], Iterable, Object[])
     */
    public static <T> void assertMatch(String[] ignoringFields, Iterable <T> actual, Iterable <T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields(ignoringFields).isEqualTo(expected);
    }

    // never instantiated
    private TestUtil() {
    }
}