package ru.job4j.iterator;

import java.util.*;
import java.util.stream.IntStream;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4};

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Collections.reverse(list);
        Object[] arrayRevVar1 = list.toArray();
        for (Object i : arrayRevVar1) {
            System.out.print(i + " ");
        }

        System.out.println();

        int[] arrayRevVar2 = IntStream.rangeClosed(1, array.length)
                .map(i -> array[array.length - i])
                .toArray();
        for (int i : arrayRevVar2) {
            System.out.print(i + " ");
        }

    }
}