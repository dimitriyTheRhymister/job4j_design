package ru.job4j.ood.tdd;

import java.util.List;
import java.util.function.Predicate;

public class YourCinema implements Cinema {

    private List<Session> sessions;
    private List<String> halls;

    @Override
    public List<Session> findSessionsByPredicate(Predicate<Session> filter) {
        return List.of();
    }

    @Override
    public void add(Session session) {
    }
}
