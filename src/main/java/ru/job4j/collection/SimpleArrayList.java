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
        }
        container = Arrays.copyOf(container, container.length * 2);
        return container;
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = increaseCapacity();
        }
        container[size] = value;
        size++;
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
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T[] tempContainer = container;
        container = (T[]) new Object[tempContainer.length - 1];
        T removedValue = tempContainer[index];
        System.arraycopy(tempContainer, 0, container, 0, index);
        System.arraycopy(tempContainer, index + 1, container, index, tempContainer.length - index - 1);
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
}