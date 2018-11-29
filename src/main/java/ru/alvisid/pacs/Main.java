package ru.alvisid.pacs;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.pacs.model.EditType;
import ru.alvisid.pacs.model.Employee;
import ru.alvisid.pacs.model.Role;
import ru.alvisid.pacs.model.WeekDay;
import ru.alvisid.pacs.repository.EmployeeRepository;
import ru.alvisid.pacs.repository.datajpa.DataJpaEmployeeRepositoryImpl;

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
        System.out.println(employee);
        employee.setCardNum(3334455);
        employee.setEmail("sdsd@mail.ru");
        employee = employeeRepository.save(employee);
        System.out.println(employee);
        employee.getRoles().add(Role.ROLE_DEPSREAD);
        System.out.println(employee);
        long start1 = System.nanoTime();
        employeeRepository.save(employee);
        //employee=employeeRepository.get(10000);
        employee.setEmail("ssss@ya.ru");
        employeeRepository.save(employee);
        long end1 = System.nanoTime();
        System.out.println(employee);
        /*System.out.println(employee.getSchedule());
        employee = employeeRepository.getByEmail("sdsd@mail.ru");
        System.out.println(employee.getSchedule());*/
        Employee employee1 = employeeRepository.getByEmail("sdsd@mail.ru");
        System.out.println(employee1);
        employee1.setLastName("Петров");
        //employee.setId(10003);
        employee1.setCardNum(3344455);
        employee1.setEmail("dfdgf@mail.ru");
        long start2 = System.nanoTime();
        employeeRepository.save(employee);
        long end2 = System.nanoTime();
        System.out.println(end1 - start1);
        System.out.println(end2 - start2);
        //employee.getSchedule().setEmployee(employee1);
        employee = employeeRepository.save(employee);
        System.out.println(employee);
        System.out.println(employee.getSchedule());

    }
}
