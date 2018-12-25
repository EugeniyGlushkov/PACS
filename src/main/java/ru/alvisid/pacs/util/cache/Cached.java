package ru.alvisid.pacs.util.cache;

/**
 * The object uses caching and there are cache alias for it.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public interface Cached {
    /**
     * Returns a cache alias for this service.
     *
     * @return the cache alias for this service.
     */
    public abstract String getCacheAlias();
}
