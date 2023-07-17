package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int delete;
        Map<String, Integer> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getName(), user.getId());
        }
        for (User user : current) {
            Integer mapValue = map.get(user.getName());
            boolean isContains = isContains(map, user.getId());
            if (mapValue == null
                    && isContains) {
                change++;
            }
            if (mapValue == null
                    && !isContains) {
                add++;
            }
        }
        delete = previous.size() - current.size() + add;
        return new Info(add, change, delete);
    }

    private static boolean isContains(Map<String, Integer> map, int mapValue) {
        return map.containsValue(mapValue);
    }
}