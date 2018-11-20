package ru.alvisid.pacs;

import org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    private static final Logger log = getLogger(Main.class);
    public static void main(String[] args) {
        log.debug("In method Main");
    }
}
