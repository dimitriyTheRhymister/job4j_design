package ru.job4j.algo;

import java.util.*;

public class SmallestRangeFinderWithSet {

    public static int[] findSmallestRange(int[] nums, int k) {
        int n = nums.length;

        /* Проверяем подмассивы длины k */
        for (int i = 0; i <= n - k; i++) {
            /* Проверяем, содержит ли подмассив k различных элементов */
            if (isUnique(nums, i, i + k - 1, k)) {
                return new int[] {i, i + k - 1};
            }
        }

        return null;
    }

    /* Метод для проверки, содержит ли подмассив k различных элементов */
    private static boolean isUnique(int[] nums, int start, int end, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            set.add(nums[i]);
        }
        return set.size() == k;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        System.out.println(Arrays.toString(result));

        int[] nums2 = {1, 2, 3, 3, 5, 6, 7};
        int k2 = 4;
        int[] result2 = findSmallestRange(nums2, k2);
        System.out.println(Arrays.toString(result2));
    }
}
/* Сложность этого кода можно оценить следующим образом:
Внешний цикл for (int i =0; i <= n - k; i++) имеет сложность O(n), где n - длина массива nums.
Внутри этого цикла мы вызываем метод isUnique, который имеет сложность O(k), поскольку он проходится
по подмассиву длины k и добавляет элементы в HashSet.
Следовательно, общая сложность кода равна O(n) * O(k) = O(n*k).
Таким образом, сложность этого кода равна O(n*k). */