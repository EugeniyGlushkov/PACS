package ru.alvisid.pacs.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.pacs.model.abstractions.AbstractId;
import ru.alvisid.pacs.repository.loader.EnumLoader;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import ru.alvisid.pacs.util.profileResolver.ActiveDbProfilesResolver;
import util.AbstractTestData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static util.TestUtil.assertMatch;

/**
 * Abstract class which contains common functional and fields for all <b>ServiceTest</b> classes.
 *
 * @param <T> the type of the test object, must extends {@code AbstractId}.
 * @param <S> the type of the {@code Service} for objects T-type, must extends {@code TypicalService<T>}.
 * @param <R> the type of the {@code TestData} with T-type data, must extends {@code AbstractTestData<T>}.
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
public abstract class AbstractServiceTest<T extends AbstractId, S extends TypicalService<T>, R extends AbstractTestData<T>> {
    public static Logger log = getLogger("result");

    private static StringBuilder results = new StringBuilder();

    protected S service;

    protected R testData;

    public abstract void setService(S service);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    @PersistenceContext
    EntityManager entityManager;

    @Before
    public void before() {
        EnumLoader enumLoader = new EnumLoader(entityManager);
        enumLoader.init();
    }

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

    protected T getUpdated() {
        return testData.getUpdated();
    }

    public AbstractServiceTest(R testData) {
        this.testData = testData;
    }

    @Test
    public void create() {
        T newObj = testData.getNew();
        T createdObj = service.create(newObj);
        newObj.setId(createdObj.getId());
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getCreatedArray(newObj));
    }

    @Test
    public void update() {
        T expected = testData.getUpdated();
        service.update(expected);
        T actual = service.get(expected.getId());
        assertMatch(testData.IGNORING_FIELDS, actual, expected);
    }

    @Test
    public void updateNotFound() {
        T updated = getUpdated();
        updated.setId(testData.NOT_FOUND_ID);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.update(updated);
    }

    @Test
    public void delete() {
        int deleteId = testData.getDeletedId();
        service.delete(deleteId);
        assertMatch(testData.IGNORING_FIELDS, service.getAll(), testData.getDeletedArray());
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.delete(testData.NOT_FOUND_ID);
    }

    @Test
    public void get() {
        T expectedObj = testData.getGetted();
        T gettedObj = service.get(expectedObj.getId());
        assertMatch(testData.IGNORING_FIELDS, gettedObj, expectedObj);
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + testData.NOT_FOUND_ID);
        service.get(testData.NOT_FOUND_ID);
    }

    @Test
    public void getAll() {
        List<T> actualAllObjects = service.getAll();
        assertMatch(testData.IGNORING_FIELDS, actualAllObjects, testData.getAllArray());
    }
}
