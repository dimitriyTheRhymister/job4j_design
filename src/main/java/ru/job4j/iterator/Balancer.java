package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    private static void append(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        for (ArrayList<Integer> integerArrayList : nodes) {
            integerArrayList.add(source.next());
            if (!source.hasNext()) {
                break;
            }
        }
    }

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        while (source.hasNext()) {
            append(nodes, source);
        }
    }

    public static void main(String[] args) {
        List<ArrayList<Integer>> nodes = List.of(
                new ArrayList<>(),
                new ArrayList<>()
        );
        Iterator<Integer> source = List.of(1, 2, 3).iterator();
        Balancer.split(nodes, source);
        System.out.println(nodes);
    }
}