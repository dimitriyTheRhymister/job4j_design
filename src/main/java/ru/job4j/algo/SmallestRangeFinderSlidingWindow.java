package ru.job4j.algo;

import java.util.*;

public class SmallestRangeFinderSlidingWindow {

    public static int[] findSmallestRange(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int[] distinctCount = new int[] {0};
        int left = 0;
        int[] result = null;

        for (int right = 0; right < nums.length; right++) {
            expandRange(nums, right, countMap, distinctCount);

            while (distinctCount[0] == k) {
                if (result == null || right - left < result[1] - result[0]) {
                    result = new int[] {left, right};
                }
                shrinkRange(nums, left, countMap, distinctCount);
                left++;
            }
        }

        return result;
    }

    private static void expandRange(int[] nums, int right, Map<Integer, Integer> countMap, int[] distinctCount) {
        countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
        if (countMap.get(nums[right]) == 1) {
            distinctCount[0]++;
        }
    }

    private static void shrinkRange(int[] nums, int left, Map<Integer, Integer> countMap, int[] distinctCount) {
        countMap.put(nums[left], countMap.get(nums[left]) - 1);
        if (countMap.get(nums[left]) == 0) {
            distinctCount[0]--;
        }
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