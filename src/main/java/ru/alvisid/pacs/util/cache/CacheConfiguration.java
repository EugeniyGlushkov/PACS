package ru.alvisid.pacs.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;

import java.net.URI;
import java.net.URISyntaxException;


@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {
    private final CacheManager cacheManager;

    @Autowired
    public CacheConfiguration(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new CustomCacheResolver(cacheManager);
    }

    /*@Override
    @Bean // not strictly necessary
    public CacheManager cacheManager() {
        JCacheManagerFactoryBean jCacheManagerFactoryBean = new JCacheManagerFactoryBean();
        URI uri;
        try {
            uri = new URI("classpath:cache/ehcache.xml");
            jCacheManagerFactoryBean.setCacheManagerUri(uri);
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
        JCacheCacheManager cacheManager = new JCacheCacheManager();
        cacheManager.setCacheManager((javax.cache.CacheManager) jCacheManagerFactoryBean);
        return cacheManager;
    }*/
/*
    //https://stackoverflow.com/questions/38570211/how-to-have-multiple-cache-manager-configuration-in-spring-cache-java
    @Bean
    public CacheManager noOpCacheManager() {
        return new NoOpCacheManager();
    }*/
}
