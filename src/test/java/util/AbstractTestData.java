package util;

/**
 *
 *
 * @param <T> type of objects for tests that are contained in the class.
 * @author Glushkov Evgeniy
 * @version 1.0 *
 */
public abstract class AbstractTestData<T> {
    public final int NOT_FOUND_ID;
    public final String[] IGNORING_FIELDS;

    AbstractTestData(int notFoundId, String[] ignoringFields) {
        this.NOT_FOUND_ID = notFoundId;
        this.IGNORING_FIELDS = ignoringFields;
    }

    public abstract T getNew();
    public abstract T getUpdated();
    public abstract T getGetted();
    public abstract int getDeletedId();
    public abstract T[] getCreatedArray(T expectedObj);
    public abstract T[] getDeletedArray();
    public abstract T[] getAllArray();
}
