package testdata;

/**
 * Contains test data and methods which are common
 * to all {@code TestData} classes without realization.
 *
 * @param <T> type of objects for tests that are contained in the class.
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public abstract class AbstractTestData<T> {
    /**
     * Id of the missing object.
     */
    public final int NOT_FOUND_ID = 100000;

    /**
     * Names of the fields which are excluding from matching.
     * May be empty.
     */
    public final String[] IGNORING_FIELDS;

    /**
     * Constructs new {@code TestData} and sets the specified
     * {@code IGNORING_FIELDS} field
     *
     * @param ignoringFields the names of the fields which are excluding from matching.
     */
    AbstractTestData(String... ignoringFields) {
        this.IGNORING_FIELDS = ignoringFields;
    }

    /**
     * Returns a new entity from the test data.
     *
     * @return the new entity from the test data.
     */
    public abstract T getNew();

    /**
     * Returns an updated entity from the test data.
     *
     * @return the updated entity from the test data.
     */
    public abstract T getUpdated();

    /**
     * Returns an object that is equals entity which will be get from DB.
     *
     * @return the object that is equals entity which will be get from DB.
     */
    public abstract T getGotten();

    /**
     * Returns an entity's id for delete.
     *
     * @return the entity's id for delete.
     */
    public abstract int getDeletedId();

    /**
     * Returns the all objects from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created object.
     * @return the all objects from test data with the specified created object.
     */
    public abstract T[] getCreatedArray(T expectedCreatedObj);

    /**
     * Returns the all objects from the test data without the deleted object.
     *
     * @return the all objects from the test data without the deleted object.
     */
    public abstract T[] getDeletedArray();

    /**
     * Returns the all objects from the test data.
     *
     * @return the all objects from the test data.
     */
    public abstract T[] getAllArray();
}
