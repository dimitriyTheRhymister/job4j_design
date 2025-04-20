package ru.job4j.ood.tdd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class YourSession implements Session {

    private Film name;
    private LocalDateTime time;
    private String hall;
    private Cinema cinema;
    private Map<Integer, Integer> seats;
    private boolean isFree;

    @Override
    public List<Session> findSessionsByFilm(Film film, LocalDateTime date) {
        return List.of();
    }

    @Override
    public Map<Integer, Integer> availableSeats(Session session) {
        return Map.of();
    }

    @Override
    public void bookSeat(Session session, Map<Integer, Integer> seat, Account account) {
    }
}
