package ru.job4j.algo.finish.test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VisitCounter {

    /**
     * Метод для подсчета количества посещений из списков массивов
     * и фильтрации чисел, которые встречаются максимальное количество раз.
     *
     * @param visitTimes Список массивов int, представляющих посещения.
     * @return Список массивов int, содержащий числа, которые встречаются максимальное количество раз.
     * @throws IllegalArgumentException Если входной список пустой или содержит пустые массивы.
     */
    public static List<int[]> countVisitTimes(List<int[]> visitTimes) {
        // Проверка входных параметров
        if (visitTimes == null || visitTimes.isEmpty()) {
            throw new IllegalArgumentException("Список посещений не должен быть пустым.");
        }

        // Проверка на наличие пустых массивов
        for (int[] times : visitTimes) {
            if (times == null || times.length == 0) {
                throw new IllegalArgumentException("Массив посещений не должен быть пустым.");
            }
        }

        // Создаем Map и сразу фильтруем его
        Map<Integer, Long> countMap = visitTimes.stream()
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long maxValue = countMap.values().stream()
                .max(Long::compareTo)
                .orElse(0L);

        // Фильтруем элементы по максимальному значению
        countMap.entrySet().removeIf(entry -> entry.getValue() < maxValue);

        return convertMapToList(countMap);
    }

    /**
     * Преобразует Map с количеством чисел в список массивов int.
     *
     * @param map Map, где ключи - числа, а значения - их количество.
     * @return Список массивов int, представляющий ключи Map.
     */
    private static List<int[]> convertMapToList(Map<Integer, Long> map) {
        List<int[]> resultList = new ArrayList<>();
        List<Integer> keys = new ArrayList<>(map.keySet());

        for (int i = 0; i < keys.size(); i += 2) {
            int[] pair = new int[Math.min(2, keys.size() - i)];
            for (int j = 0; j < pair.length; j++) {
                pair[j] = keys.get(i + j);
            }
            resultList.add(pair);
        }

        return resultList;
    }

    public static void main(String[] args) {
        List<int[]> visitTimes = Arrays.asList(
                new int[]{1, 2, 3, 4, 5},
                new int[]{2, 3, 4, 5, 6},
                new int[]{3, 4, 5, 6, 7, 8},
                new int[]{4, 5, 6, 7},
                new int[]{7, 8, 9, 10},
                new int[]{8, 9, 10, 11},
                new int[]{9, 10, 11, 12},
                new int[]{9, 10, 11, 12, 13, 14, 15},
                new int[]{21, 22, 23, 24, 25},
                new int[]{22, 23, 24, 25, 26},
                new int[]{23, 24, 25, 26, 27, 28},
                new int[]{24, 25, 26, 27},
                new int[]{30, 32},
                new int[]{29, 30},
                new int[]{30, 31},
                new int[]{30, 33}
        );

        List<int[]> resultList = countVisitTimes(visitTimes);

        for (int[] arr : resultList) {
            System.out.println(Arrays.toString(arr));
        }
    }
}