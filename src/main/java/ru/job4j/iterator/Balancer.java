package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        for (int i = 0; source.hasNext(); i++) {
            if (i == nodes.size()) {
                i = 0;
            }
            nodes.get(i).add(source.next());
        }
    }
}