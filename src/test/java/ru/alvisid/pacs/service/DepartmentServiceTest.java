package ru.alvisid.pacs.service;

import org.junit.*;
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
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.repository.loader.EnumLoader;
import ru.alvisid.pacs.util.profileResolver.ActiveDbProfilesResolver;
import util.DepartmentTestData;
import util.TestUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static util.DepartmentTestData.*;
import static util.TestUtil.assertMatch;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@ActiveProfiles(resolver = ActiveDbProfilesResolver.class)
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
    public void before() {
        EnumLoader enumLoader = new EnumLoader(entityManager);
        enumLoader.init();
        TestUtil.setIgnoringFields(DepartmentTestData.IGNORING_FIELDS);
    }

    @Test
    public void create() {
        DeptSchedule deptSchedule = new DeptSchedule();

        Department newDepartment = new Department(null, "Департамент для тестов", "Новый департамент");
        Department created = service.create(newDepartment);
        newDepartment.setId(created.getId());
        assertMatch(service.getAll(), DEPARTMENT_3, newDepartment, DEPARTMENT_1, DEPARTMENT_2);
    }
}
