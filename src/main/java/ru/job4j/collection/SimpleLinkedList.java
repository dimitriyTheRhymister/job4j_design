package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> currentNode = head;
        Node<E> newNode = new Node<>(value, null);
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = head;
        for (int i = 0; currentNode != null; i++) {
            if (i == index) {
                break;
            }
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Modifying a collection while iterating over it is not allowed!");
                }
                return currentNode != null;
            }

            @Override
            public E next() {
                if (currentNode == null) {
                    throw new NoSuchElementException("There are no more items in the collection!");
                }
                E currentNodeValue = currentNode.value;
                currentNode = currentNode.next;
                return currentNodeValue;
            }
        };
    }

    private static class Node<E> {
        private final E value;
        private Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}