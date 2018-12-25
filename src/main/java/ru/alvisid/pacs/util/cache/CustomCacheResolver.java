package ru.alvisid.pacs.util.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Resolves a cache for the cached target by specified cached alias value.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see Cached
 */
public class CustomCacheResolver implements CacheResolver {
    /**
     * Current cache manager.
     */
    private final CacheManager cacheManager;

    /**
     * Constructs new CustomCacheResolver and initiate the {@code cacheManager} field by specified value.
     *
     * @param cacheManager the cache manager specified value.
     */
    public CustomCacheResolver(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Returns the cache(s) to use for the specified invocation.
     *
     * @param context the context of the particular invocation
     * @return the cache(s) to use (never {@code null})
     * @throws IllegalStateException if cache resolution failed
     * @see CacheResolver#resolveCaches(CacheOperationInvocationContext)
     */
    @Override
    public Collection <? extends Cache> resolveCaches(CacheOperationInvocationContext <?> context) {
        Collection <Cache> caches = new ArrayList <>();
        Object target = context.getTarget();

        if (target instanceof Cached) {
            String cacheAlias = ((Cached) target).getCacheAlias();
            caches.add(cacheManager.getCache(cacheAlias));
        } else {
            throw new RuntimeException("The " + target.getClass().getName()
                    + " must implements " + Cached.class.getName());
        }

        return caches;
    }
}
