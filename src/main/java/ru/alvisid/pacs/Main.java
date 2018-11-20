package ru.alvisid.pacs;

import org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory;
import org.slf4j.Logger;
import ru.alvisid.pacs.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    private static final Logger log = getLogger(Main.class);
    public static void main(String[] args) {
        log.debug("In method Main");
        System.out.println(DateTimeUtil.isBetween(LocalDateTime.of(2000, 5, 22, 9, 44),
                LocalDateTime.of(2001, 5, 22, 23, 0),
                LocalDateTime.of(2004, 5, 22, 12, 45)));
        System.out.println(DateTimeUtil.toString(LocalTime.of( 23, 0)));
    }
}
