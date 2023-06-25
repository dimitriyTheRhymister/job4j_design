package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> currentNode = head;
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        modCount++;
        size++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("There are no more items in the collection!");
        }
        Node<T> deletedNode = head;
        T deletedNodeValue = head.value;
        head = head.next;
        deletedNode.next = null;
        deletedNode.value = null;
        modCount++;
        size--;
        return deletedNodeValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Modifying a collection while iterating over it is not allowed!");
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("There are no more items in the collection!");
                }
                T currentNodeValue = currentNode.value;
                currentNode = currentNode.next;
                return currentNodeValue;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}