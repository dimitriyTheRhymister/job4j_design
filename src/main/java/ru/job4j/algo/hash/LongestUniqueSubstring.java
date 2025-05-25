package ru.job4j.algo.hash;

import java.util.HashSet;

public class LongestUniqueSubstring {

    public static String longestUniqueSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int maxLength = 0;
        int start = 0;
        int startIndex = 0;

        for (int end = 0; end < s.length(); end++) {
            while (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            }
            set.add(s.charAt(end));

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                startIndex = start;
            }
        }

        return s.substring(startIndex, startIndex + maxLength);
    }
}