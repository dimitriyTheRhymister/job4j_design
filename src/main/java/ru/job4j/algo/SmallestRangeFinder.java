package ru.job4j.algo;

import java.util.*;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int[] result = new int[]{0, 0};
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                result[0] = i;
            }
            result[1]++;
            if (result[1] - result[0] == k - 1) {
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        System.out.println(Arrays.toString(result)); // Вывод: [0,2]

        int[] nums2 = {1, 2, 3, 3, 5, 6, 7};
        int k2 = 4;
        int[] result2 = findSmallestRange(nums2, k2);
        System.out.println(Arrays.toString(result2)); // Вывод: [3,6]
    }
}
/* Вычислительная сложность: O(n), поскольку мы проходим по массиву
один раз и выполняем константное количество операций для каждого элемента.
Пространственная сложность: O(n), поскольку в худшем случае мы храним
все элементы в HashMap. */