package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public SimpleArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalStateException("Capacity can't be less than 0!");
        }
        container = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    private T[] increaseCapacity() {
        if (size == 0) {
            container = (T[]) new Object[10];
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
        return container;
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = increaseCapacity();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T exValue = container[index];
        container[index] = newValue;
        return exValue;
    }

    @Override
    public T remove(int index) {
        T removedValue = container[index];
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        modCount++;
        return removedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Modifying a collection while iterating over it is not allowed!");
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("There are no more items in the collection!");
                }
                return container[index++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleList<Integer> list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list = new SimpleArrayList<>(0);
        System.out.println(list.size());
        list.add(1);
        System.out.println(list.size());
        System.out.println(list.get(0));
    }
}
