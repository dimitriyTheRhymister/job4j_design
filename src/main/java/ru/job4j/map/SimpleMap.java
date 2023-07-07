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
        int index = getIndex(key);
        boolean rsl = isNull(table[index]);
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
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
            if (nonNull(entry)) {
                tableNew[getIndex(entry.key)] = entry;
            }
        }
        table = tableNew;
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = table[getIndex(key)];
        return nonNull(entry) && isEquals(entry.key, key)
                ? entry.value
                : null;
    }

    private boolean isEquals(K tabKey, K key) {
        return Objects.hashCode(tabKey) == Objects.hashCode(key)
                && Objects.equals(tabKey, key);
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        MapEntry<K, V> entry = table[index];
        boolean rsl = nonNull(entry) && isEquals(entry.key, key);
        if (rsl) {
            table[index] = null;
            count--;
            modCount++;
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