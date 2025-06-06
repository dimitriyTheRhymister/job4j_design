package ru.job4j.algo.finish.test;

import org.jetbrains.annotations.NotNull;

public class Event implements Comparable<Event> {

    int time;
    EventType type;

    Event(int time, EventType type) {
        this.time = time;
        this.type = type;
    }

    @Override
    public int compareTo(@NotNull Event other) {
        if (this.time == other.time && this.type == other.type) {
            return 0;
        }
        if (this.time == other.time) {
            return this.type == EventType.ARRIVAL ? -1 : 1;
        }
        return Integer.compare(this.time, other.time);
    }

    @Override
    public String toString() {
        return "Event{"
                + "time=" + time
                + ", type=" + type
                + '}';
    }
}
