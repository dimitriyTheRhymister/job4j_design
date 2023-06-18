package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }

        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int i = data[row][column];
        boolean b1 = row < data.length - 1;
        boolean b2 = row == data.length - 1;
        boolean b3 = column < data[row].length - 1;
        boolean b4 = column == data[row].length - 1;

        if (b3 && b1 || b2) {
            column++;
            return i;
        }
        if (b4 && b1) {
            row++;
            column = 0;
            return i;
        }

        return i;
    }
}