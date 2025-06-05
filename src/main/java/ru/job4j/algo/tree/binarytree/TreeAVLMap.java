package ru.job4j.algo.tree.binarytree;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private final AvlTree<T> avlTree;
    private final Map<T, V> values;

    public TreeAVLMap() {
        this.avlTree = new AvlTree<>();
        this.values = new HashMap<>();
    }

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(value)) {
            if (avlTree.contains(key)) {
                values.put(key, value);
                result = true;
            } else {
                result = avlTree.insert(key);
                if (result) {
                    values.put(key, value);
                }
            }
        }
        return result;
    }

    public boolean remove(T key) {
        boolean result = avlTree.remove(key);
        if (result) {
            values.remove(key);
        }
        return result;
    }

    public V get(T key) {
        return values.get(key);
    }

    public Set<T> keySet() {
        return new HashSet<>(avlTree.inSymmetricalOrder());
    }

    public Collection<V> values() {
        return values.values();
    }
}