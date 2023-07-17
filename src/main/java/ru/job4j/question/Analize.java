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
            String mapKey = user.getName();
            int mapValue = user.getId();
            if (map.get(mapKey) == null
                    && isContains(map, mapValue)) {
                change++;
            }
            if (map.get(mapKey) == null
                    && !isContains(map, mapValue)) {
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