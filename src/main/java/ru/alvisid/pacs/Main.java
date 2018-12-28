package ru.alvisid.pacs;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.alvisid.pacs.model.EditType;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.WeekDay;
import ru.alvisid.pacs.repository.EmployeeRepository;
import ru.alvisid.pacs.repository.impl.DataJpaEmployeeRepositoryImpl;
import ru.alvisid.pacs.repository.loader.EnumLoader;
import ru.alvisid.pacs.service.DepartmentService;
import ru.alvisid.pacs.service.EmployeeService;
import ru.alvisid.pacs.service.impl.DepartmentServiceImpl;
import ru.alvisid.pacs.service.impl.EmployeeServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    private static final Logger log = getLogger(Main.class);

    //--illegal-access=warn

    public static void main(String[] args) {
        log.debug("In method Main");
        /*ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext();
        appCtx.setConfigLocations("spring/spring-db.xml", "spring/spring-app.xml", "spring/spring-tools.xml");
        appCtx.getEnvironment().setActiveProfiles("hsqldb");
        appCtx.refresh();*/
        GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext();
        appCtx.getEnvironment().setActiveProfiles("hsqldb");
        appCtx.load("spring/spring-db.xml", "spring/spring-app.xml");
        appCtx.refresh();
        //ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext();
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        //EnumLoader enumLoader = (EnumLoader) appCtx.getBean(EnumLoader.class);
        //System.out.println(enumLoader);

        Enum[] days = WeekDay.values();
        System.out.println(days.length + "\n");
        for (Enum e : days) {
            System.out.println(e == null ? 0 : e.ordinal() );
        }
        days = EditType.values();
        System.out.println(days.length + "\n");
        for (Enum e : days) {
            System.out.println(e == null ? 0 : e.ordinal() );
        }
        EmployeeRepository employeeRepository = (DataJpaEmployeeRepositoryImpl)appCtx.getBean(DataJpaEmployeeRepositoryImpl.class);
        Employee employee = employeeRepository.getByEmail("ivanov@mail.ru");
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)));
        EmployeeService employeeService = (EmployeeServiceImpl)appCtx.getBean(EmployeeServiceImpl.class);
        DepartmentService departmentService = (DepartmentServiceImpl)appCtx.getBean(DepartmentServiceImpl.class);
        System.out.println(employeeService);
        System.out.println(departmentService);
        //Employee employeeN = employeeService.get(10000);
        System.out.println(employee);

        //System.out.println(employeeN);
        WeekDay.values();
    }
}
