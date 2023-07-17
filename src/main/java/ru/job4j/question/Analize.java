package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int delete;
        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }
        for (User user : current) {
            String mapValue = map.get(user.getId());
            boolean isContains = isContains(map, user.getId());
            if (isContains
                    && !mapValue.equals(user.getName())) {
                change++;
            }
            if (!isContains) {
                add++;
            }
        }
        delete = previous.size() - current.size() + add;
        return new Info(add, change, delete);
    }

    private static boolean isContains(Map<Integer, String> map, Integer mapKey) {
        return map.containsKey(mapKey);
    }
}