package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int delete = 0;
        Map<String, Integer> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getName(), user.getId());
        }
        for (User user : current) {
            String mapKey = user.getName();
            int mapValue = user.getId();
            if (map.get(mapKey) == null) {
                if (isContains(map, mapValue)) {
                    change++;
                }
                if (!isContains(map, mapValue)) {
                    add++;
                }
            }
            map.put(mapKey, mapValue);
            delete = previous.size() - current.size() + add;
        }
        return new Info(add, change, delete);
    }

    public static boolean isContains(Map<String, Integer> map, int mapValue) {
        return map.containsValue(mapValue);
    }
}