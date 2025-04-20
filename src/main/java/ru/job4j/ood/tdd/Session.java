package ru.job4j.ood.tdd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface Session {

    List<Session> findSessionsByFilm(Film film, LocalDateTime date);
    Map<Integer, Integer> availableSeats(Session session);
    void bookSeat(Session session, Map<Integer, Integer> seat, Account account);
}
