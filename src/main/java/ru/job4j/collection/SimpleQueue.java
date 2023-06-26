package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int x = 0;
    int y = 0;

    public T poll() {
        if (x == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (y == 0) {
            while (x > 0) {
                out.push(in.pop());
                x--;
                y++;
            }
        }
        y--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        x++;
    }
}