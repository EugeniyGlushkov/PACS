package ru.alvisid.pacs.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.pacs.repository.loader.EnumLoader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@ActiveProfiles("hsqldb")
@Sql(scripts = "classpath:db/populateDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DepartmentServiceTest {
    public static Logger log = getLogger("result");

    private static StringBuilder results = new StringBuilder();

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

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

    @Autowired
    private DepartmentService service;

    @PersistenceContext
    EntityManager entityManager;

    @Before
    public void before(){
        EnumLoader enumLoader = new EnumLoader(entityManager);
        enumLoader.init();
    }


}
