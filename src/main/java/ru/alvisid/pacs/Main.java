package ru.alvisid.pacs;

import org.hibernate.SessionFactory;
import org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.EntityType;
import org.slf4j.Logger;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.alvisid.pacs.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    private static final Logger log = getLogger(Main.class);
    public static void main(String[] args) {
        log.debug("In method Main");
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Configuration configuration = factoryBean.getConfiguration();
        SessionFactory sessionFactory = factoryBean.f;
        Set<javax.persistence.metamodel.EntityType<?>> entities = sessionFactory.getMetamodel().getEntities();
        List<?> classess = entities.stream().map(javax.persistence.metamodel.EntityType::getJavaType).filter(Objects::nonNull)
                .collect(Collectors.toList());

    }
}
