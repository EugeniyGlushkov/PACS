package ru.alvisid.pacs;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.pacs.model.EditType;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.WeekDay;
import ru.alvisid.pacs.repository.EmployeeRepository;
import ru.alvisid.pacs.repository.impl.DataJpaEmployeeRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    private static final Logger log = getLogger(Main.class);

    public static void main(String[] args) {
        log.debug("In method Main");
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-db.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
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

    }
}
