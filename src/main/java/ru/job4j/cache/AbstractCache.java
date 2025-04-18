package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> valueSoftReference = new SoftReference<>(value);
        cache.put(key, valueSoftReference);
    }

    public final V get(K key) {
        SoftReference<V> valueSoftReference = cache.get(key);
        V value = null;
        if (valueSoftReference != null) {
            value = valueSoftReference.get();
        }
        if (value == null) {
            value = load(key);
            if (value != null) {
                SoftReference<V> valueSoftReferenceNew = new SoftReference<>(value);
                cache.put(key, valueSoftReferenceNew);
            }
        }
        return value;
    }

    protected abstract V load(K key);
}