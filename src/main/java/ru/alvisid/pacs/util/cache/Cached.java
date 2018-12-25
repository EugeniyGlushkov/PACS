package ru.alvisid.pacs.util.cache;

public interface Cached {
    /**
     * Returns a cache alias for this service.
     *
     * @return the cache alias for this service.
     */
    public abstract String getCacheAlias();
}
