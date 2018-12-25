package ru.alvisid.pacs.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Implements of {@link CachingConfigurerSupport} and override method
 * it is interested in.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see CachingConfigurerSupport
 */
@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {
    /**
     * Current cache manager.
     */
    private final CacheManager cacheManager;

    /**
     * Constructs new CacheConfiguration and initiate the {@code cacheManager} field by specified value.
     *
     * @param cacheManager the cache manager specified value.
     */
    @Autowired
    public CacheConfiguration(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Return the {@link CacheResolver} bean to use to resolve regular caches for
     * annotation-driven cache management.
     *
     * @return Return the {@link CacheResolver} bean to use to resolve regular caches for
     * annotation-driven cache management.
     */
    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new CustomCacheResolver(cacheManager);
    }
}
