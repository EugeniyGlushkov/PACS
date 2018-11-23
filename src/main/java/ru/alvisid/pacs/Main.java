package ru.alvisid.pacs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.pacs.model.EditType;
import ru.alvisid.pacs.model.WeekDay;
import ru.alvisid.pacs.model.enumActivate.AbstractDictionary;
import ru.alvisid.pacs.model.enumActivate.WeekDayDict;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.*;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.alvisid.pacs.model.WeekDay.*;

public class Main {
    private static final Logger log = getLogger(Main.class);

    public static void main(String[] args) {
        log.debug("In method Main");
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-db.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        Enum[] days = WeekDay.values();
        System.out.println(days.length);
        for (Enum e : days) {
            System.out.println(e == null ? 0 : e.ordinal() );
        }
        days = EditType.values();
        System.out.println(days.length);
        for (Enum e : days) {
            System.out.println(e == null ? 0 : e.ordinal() );
        }

        /*EntityManager em = ((Cont)appCtx.getBean(Cont.class)).getEm();
        System.out.println(em);
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        List<?> classes = entities.stream()
                .map(EntityType::getJavaType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        classes.forEach(System.out::println);
        Session session = (Session)em.getDelegate();
        System.out.println(session);
        SessionFactory sf = session.getSessionFactory();
        Session newSession = sf.openSession();
        System.out.println(newSession);
        System.out.println(MONDAY.ordinal());
        String sql = "FROM " + WeekDayDict.class.getSimpleName();
        System.out.println("sql = " + sql);

        List<AbstractDictionary> weekDays = em.createQuery(sql).getResultList();
        System.out.println(weekDays);

        System.out.println(AbstractDictionary.class.isAssignableFrom(((Object)(new WeekDayDict())).getClass()));*/
    }
}
