package ru.alvisid.pacs.service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.pacs.model.Department;
import ru.alvisid.pacs.model.DeptSchedule;
import ru.alvisid.pacs.repository.loader.EnumLoader;
import ru.alvisid.pacs.util.exceptions.NotFoundException;
import ru.alvisid.pacs.util.profileResolver.ActiveDbProfilesResolver;
import util.DepartmentTestData;
import util.TestUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
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
public class DepartmentServiceTest extends AbstractServiceTest {
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
        log.info(
                "\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }
    private DepartmentService service;

    @Autowired
    public void setService(DepartmentService service) {
        this.service = service;
    }

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
        Department newDepartment = new Department(null, "Новый департамент", "Департамент для тестов");
        Department created = service.create(newDepartment);
        newDepartment.setId(created.getId());
        assertMatch(service.getAll(), DEPARTMENT_3, newDepartment, DEPARTMENT_1, DEPARTMENT_2);
    }

    @Test
    public void duplicateNameCreate() throws Exception{
        thrown.expect(DataAccessException.class);
        Department newDepartment = new Department(null, "Отдел кадров", "Департамент с существующим названием.");
        service.create(newDepartment);
    }

    @Test
    public void update() {
        Department updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(updated.getId()), updated);
    }

    @Test
    public void updateDuplicateName() {
        thrown.expect(DataAccessException.class);
        Department updated = getUpdated();
        updated.setName(DEPARTMENT_2.getName());
        service.update(updated);
    }

    @Test
    public void updateNotFound() {
        Department updated = getUpdated();
        updated.setId(1000000);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + updated.getId());
        service.update(updated);
    }

    @Test
    public void delete() {
        service.delete(DEPARTMENT_2.getId());
        assertMatch(service.getAll(), DEPARTMENT_3, DEPARTMENT_1);
    }

    @Test
    public void deleteNotFound() {
        int notFoundId = 1000000;
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + notFoundId);
        service.delete(notFoundId);
    }

    @Test
    public void get() {
        Department department = service.get(DEPARTMENT_2.getId());
        assertMatch(department, DEPARTMENT_2);
    }

    @Test
    public void getNotFound() {
        int notFoundId = 1000000;
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + notFoundId);
        service.get(notFoundId);
    }

    @Test
    public void getAll() {
        List<Department> allDepts = service.getAll();
        assertMatch(allDepts, DEPARTMENT_3, DEPARTMENT_1, DEPARTMENT_2);
    }
}
