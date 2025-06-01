package ru.job4j.algo.tree;

import java.util.*;

public class Node<E> {
    private final E value;
    private final List<Node<E>> children = new ArrayList<>();

    public Node(E value) {
        this.value = value;
    }

    @SafeVarargs
    public Node(E value, Node<E>... children) {
        this.value = value;
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public String toString() {
        return String.format("Node{ %s }", value);
    }

    public E getValue() {
        return value;
    }

    public List<Node<E>> getChildren() {
        return children;
    }
}