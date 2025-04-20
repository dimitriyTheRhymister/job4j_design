package ru.job4j.ood.tdd;

import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    List<Session> findSessionsByPredicate(Predicate<Session> filter);
    void add(Session session);
}
