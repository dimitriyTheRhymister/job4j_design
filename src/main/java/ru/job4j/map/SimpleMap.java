package ru.job4j.map;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rsl = false;
        int hash = isNull(key) ? 0 : hash(key.hashCode());
        int index = indexFor(hash);
        if (isNull(table[index])) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tableNew = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (nonNull(entry)
                    && nonNull(entry.key)) {
                MapEntry<K, V> entryNew = new MapEntry<>(entry.key, entry.value);
                int hash = hash(entry.key.hashCode());
                int index = indexFor(hash);
                tableNew[index] = entryNew;
            }
        }
        tableNew[0] = table[0];
        table = tableNew;
    }

    @Override
    public V get(K key) {
        return isNull(key) ? getForNull() : getForNoNull(key);
    }

    private V getForNull() {
        return nonNull(table[0]) && isNull(table[0].key) ? table[0].value : null;
    }

    private V getForNoNull(K key) {
        V rsl = null;
        for (MapEntry<K, V> entry : table) {
            if (testBeforeGetOrRemove(entry, key)) {
                rsl = entry.value;
                break;
            }
        }
        return rsl;
    }

    private boolean testBeforeGetOrRemove(MapEntry<K, V> entry, K key) {
        return nonNull(entry)
                && nonNull(entry.key)
                && nonNull(key)
                && entry.key.hashCode() == key.hashCode()
                && Objects.equals(entry.key, key);
    }

    @Override
    public boolean remove(K key) {
        return isNull(key) ? removeForNull() : removeForNoNull(key);
    }

    private boolean removeForNull() {
        table[0] = null;
        count--;
        modCount++;
        return true;
    }

    private boolean removeForNoNull(K key) {
        boolean rsl = false;
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        for (MapEntry<K, V> entry : table) {
            if (testBeforeGetOrRemove(entry, key)) {
                table[index] = null;
                count--;
                modCount++;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int indexInItr = 0;
            int countInItr = count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Modifying a collection while iterating over it is not allowed!");
                }
                while (indexInItr < capacity
                        && isNull(table[indexInItr])) {
                    indexInItr++;
                    countInItr++;
                }
                return indexInItr < countInItr;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("There are no more items in the collection!");
                }
                return table[indexInItr++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}