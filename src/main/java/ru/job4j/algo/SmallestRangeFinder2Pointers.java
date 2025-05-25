package ru.job4j.algo;

import java.util.*;

public class SmallestRangeFinder2Pointers {

    public static int[] findSmallestRange(int[] nums, int k) {
        int n = nums.length;

        /* var#2 */
        for (int left = 0; left <= n - k; left++) {
            int right = left;
            Set<Integer> set = new HashSet<>();
            while (right < n && set.size() < k) {
                set.add(nums[right]);
                right++;
            }
            if (set.size() == k && right - left <= k) {
                return new int[] {left, right - 1};
            }
        }

        return null;
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
        /* var#1
        for (int left = 0; left <= n - k; left++) {
           int right = left + k - 1;
            Set<Integer> set = new HashSet<>();
            for (int i = left; i <= right; i++) {
                set.add(nums[i]);
            }
            if (set.size() == k) {
                return new int[] {left, right};
            }
        } */
/* Var#1
Вычислительная сложность: O(nk), поскольку у нас есть внешний цикл,
который выполняется n-k+1 раз, и внутри этого цикла мы создаем HashSet и добавляем
в него k элементов. Следовательно, общая сложность равна O(nk).
Пространственная сложность: O(k), поскольку мы используем HashSet, который
в худшем случае содержит k элементов. */
/* Var#2
Вычислительная сложность: O(nk) в худшем случае, поскольку у нас есть внешний цикл,
который выполняется n-k+1 раз, и внутри этого цикла мы сдвигаем right вправо, пока
не найдем k различных элементов. В худшем случае мы можем просмотреть весь массив
для каждого left. Следовательно, общая сложность равна O(nk).
Пространственная сложность: O(k), поскольку мы используем HashSet, который
в худшем случае содержит k элементов.
 Итак, оба варианта имеют одинаковую вычислительную сложность O(n*k)
 и пространственную сложность O(k).

Однако стоит отметить, что Вариант 2 может быть немного быстрее
на практике, поскольку он останавливает
сдвиг right вправо, как только находит k различных элементов,
тогда как Вариант 1 всегда проверяет
весь диапазон длины k.*/