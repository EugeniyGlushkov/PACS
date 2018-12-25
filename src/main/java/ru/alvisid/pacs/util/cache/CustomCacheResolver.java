package ru.alvisid.pacs.util.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.ArrayList;
import java.util.Collection;

public class CustomCacheResolver implements CacheResolver {

    private final CacheManager cacheManager;

    public CustomCacheResolver(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public Collection <? extends Cache> resolveCaches(CacheOperationInvocationContext <?> context) {
        Collection <Cache> caches = new ArrayList <>();
        Object target = context.getTarget();

        if (target instanceof Cached){
            String cacheAlias = ((Cached) target).getCacheAlias();
            caches.add(cacheManager.getCache(cacheAlias));
        }

        return caches;
    }
}
