package ru.alvisid.pacs.service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.pacs.model.abstractions.AbstractId;
import ru.alvisid.pacs.model.enumActivate.AbstractDictionary;
import ru.alvisid.pacs.model.enumActivate.MappedEnum;
import ru.alvisid.pacs.repository.loader.EnumLoader;
import ru.alvisid.pacs.util.ValidationUtil;
import ru.alvisid.pacs.util.cache.Cached;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import ru.alvisid.pacs.util.profileResolver.ActiveDbProfilesResolver;
import util.AbstractTestData;
import util.TimingRules;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static util.TestUtil.assertMatch;
import static ru.alvisid.pacs.util.ValidationUtil.*;

/**
 * Abstract class which contains common functional and fields for all <b>ServiceTest</b> classes.
 *
 * @param <T> the type of the test object, must extends {@code AbstractId}.
 * @param <S> the type of the {@code Service} for objects T-type, must extends {@code TypicalService<T>}.
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see TypicalService
 * @see AbstractTestData
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@ActiveProfiles(resolver = ActiveDbProfilesResolver.class)
@Sql(scripts = "classpath:db/populateDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractServiceTest<T extends AbstractId, S extends TypicalService<T>> {
    /**
     * Service for testing.
     */
    protected S service;

    /**
     * The keeper of the test data.
     */
    protected AbstractTestData<T> testData;

    static {
        // needed only for java.util.logging (postgres driver)
        SLF4JBridgeHandler.install();
    }

    /**
     * Sets the specified Service to the {@code service} field.
     * Must be realized in the heir classes for initialization of the {@code service} field.
     *
     * @param service the specified Service.
     */
    public abstract void setService(S service);

    /**
     * Shows enums was updated by {@code EnumLoader} or not.
     */
    private static boolean enumsIsUpdated = false;

    /**
     * Execute for test class.
     *
     * @see TimingRules#SUMMARY
     */
    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    /**
     * The {@code ExpectedException} rule allows to verify that a test method code
     * throws a specific exception.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Executes for every test.
     *
     * @see TimingRules#STOPWATCH
     */
    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;

    /**
     * {@code EntityManager} for enums activate enum's dictionaries.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * {@code CacheManager} for clearing cache.
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * Executes before every test,
     * clears cache if {@code service} is {@code Cached}
     * and activate enum's dictionaries.
     *
     * @see Cached
     */
    @Rule
    public ExternalResource beforeRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            if (service instanceof Cached) {
                cacheManager.getCache(((Cached) service).getCacheAlias()).clear();
            }

            /*Sets enumConstants field in the enum's classes to the start condition
            because enum array size not equals number of dictionary members from DB.
            zero-value from enum array is null in the updated enum, and the array has
            n+1 element.*/
            if (enumsIsUpdated) {
                //Get all mapped entity types.
                Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

                //Get all mapped classes.
                List<?> entityClasses = entities.stream()
                        .map(EntityType::getJavaType)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                for (Object obj : entityClasses) {
                    if (!AbstractDictionary.class.isAssignableFrom((Class<AbstractDictionary>) obj) ||
                            !((Class) obj).isAnnotationPresent(MappedEnum.class)) {
                        continue;
                    }

                    //Get MappedEnum object of the current entity.
                    MappedEnum mappedEnum = ((Class<?>) obj).getAnnotation(MappedEnum.class);
                    Class<? extends Enum> enumClass = mappedEnum.enumClass();
                    Enum[] oldValues = enumClass.getEnumConstants();
                    Enum[] newValues = Arrays.copyOfRange(oldValues, 1, oldValues.length);
                    Field field = enumClass.getClass().getDeclaredField("enumConstants");
                    field.setAccessible(true);
                    field.set(enumClass, newValues);
                    System.out.println(enumClass.getEnumConstants().length);
                }
            }

            EnumLoader enumLoader = new EnumLoader(entityManager);
            enumLoader.init();
            enumsIsUpdated = true;
        }
    };

    /**
     * Constructs new {@code AbstractServiceTest}
     * and sets a specified value of the TestData to the testData field.
     *
     * @param testData the specified value of the TestData.
     */
    public AbstractServiceTest(AbstractTestData<T> testData) {
        this.testData = testData;
    }

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object to the DB;
     * checks matching the actual list of all objects to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void create() {
        T newObj = testData.getNew();
        T createdObj = service.create(newObj);
        newObj.setId(createdObj.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(newObj));
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object from DB;
     * checks matching the actual list of all objects to the expected list of all objects from {@code testData}.
     */
    @Test
    public void update() {
        T expected = testData.getUpdated();
        service.update(expected);
        T actual = service.get(expected.getId());
        assertMatch(testData.IGNORING_FIELDS, actual, expected);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated object's id in the DB.
     */
    @Test
    public void updateNotFound() {
        T updated = testData.getUpdated();
        updated.setId(testData.NOT_FOUND_ID);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(updated);
    }

    /**
     * Checks deletion object from DB by the specified id:
     * deletes object by specified id;
     * checks matching the actual list of all objects to the expected list of all objects after deletion from {@code testData}.
     */
    @Test
    public void delete() {
        int deleteId = testData.getDeletedId();
        service.delete(deleteId);
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getDeletedArray());
    }

    /**
     * Checks the {@code NotFoundException} when there are no deleted object's id in the DB.
     */
    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.delete(testData.NOT_FOUND_ID);
    }

    /**
     * Checks matching the actual gotten value from DB to the expected gotten value from {@code testData}.
     */
    @Test
    public void get() {
        T expectedObj = testData.getGotten();
        T gottenObj = service.get(expectedObj.getId());
        assertMatch(testData.IGNORING_FIELDS, gottenObj, expectedObj);
    }

    /**
     * Checks the {@code NotFoundException} when there are no object's id to getting in the DB.
     */
    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.get(testData.NOT_FOUND_ID);
    }

    /**
     * Checks the actual gotten list of the all objects from DB to
     * the expected gotten list of the all objects from {@code testData}.
     */
    @Test
    public void getAll() {
        List<T> actualAllObjects = service.getAll();
        assertMatch(testData.IGNORING_FIELDS, actualAllObjects, testData.getAllArray());
    }

    public <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
        try {
            runnable.run();
            Assert.fail("Expected: " + exceptionClass.getName());
        } catch (Exception exc) {
            Assert.assertThat(getRootCause(exc), instanceOf(exceptionClass));
        }
    }
}
