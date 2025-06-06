package ru.job4j.algo.finish.test;

import java.util.*;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        List<Event> events = new ArrayList<>();
        for (int[] visitTime : visitTimes) {
            events.add(new Event(visitTime[0], EventType.ARRIVAL));
            events.add(new Event(visitTime[1], EventType.DEPARTURE));
        }
        Collections.sort(events);

        int maxCount = 0;
        int currentCount = 0;
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        int prevTime = -1;

        for (Event event : events) {
            if (prevTime != -1 && currentCount > maxCount) {
                maxCount = currentCount;
                maxLoadStartTime = prevTime;
                maxLoadEndTime = event.time;
            }
            if (event.type == EventType.ARRIVAL) {
                currentCount++;
            } else {
                currentCount--;
            }
            prevTime = event.time;
        }

        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }
}
