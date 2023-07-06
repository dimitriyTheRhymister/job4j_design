package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        int hash = key == null ? 0 : hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] == null) {
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
        capacity = capacity * 2;
        MapEntry<K, V>[] tableNew = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null
                    && entry.key != null) {
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
        V rsl = key == null ? getForNullKey() : null;
        for (MapEntry<K, V> entry : table) {
            if (testBeforeGetOrRemove(entry, key)) {
                rsl = entry.value;
                break;
            }
        }
        return rsl;
    }

    private V getForNullKey() {
        int index = 0;
        V value = null;
        for (MapEntry<K, V> e = table[index];
             e != null;
             e = table[index++]) {
            if (e.key == null) {
                value = e.value;
            }
        }
        return value;
    }

    private boolean testBeforeGetOrRemove(MapEntry<K, V> entry, K key) {
        return entry != null
                && key != null
                && entry.key != null
                && entry.key.hashCode() == key.hashCode()
                && entry.key.equals(key);
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (key == null) {
            table[0] = null;
            count--;
            modCount++;
            rsl = true;
        } else {
            for (MapEntry<K, V> entry : table) {
                if (testBeforeGetOrRemove(entry, key)) {
                    rsl = true;
                    break;
                }
            }
            if (rsl) {
                int hash = hash(key.hashCode());
                int index = indexFor(hash);
                table[index] = null;
                count--;
                modCount++;
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
                        && table[indexInItr] == null) {
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