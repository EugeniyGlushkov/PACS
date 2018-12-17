package util;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {
    private static String[] ignoringFields;

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, ignoringFields);
    }

    public static <T> void assertMatch(Iterable <T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected){
        assertThat(actual).usingElementComparatorIgnoringFields(ignoringFields).isEqualTo(expected);
    }

    public static String[] getIgnoringFields() {
        return ignoringFields;
    }

    public static void setIgnoringFields(String[] ignoringFields) {
        TestUtil.ignoringFields = ignoringFields;
    }
}
