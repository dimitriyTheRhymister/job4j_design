package ru.job4j.ood.tdd;

import java.util.List;

public class YourFilm implements Film {

    private String name;
    private String genre;
    private int duration;

    @Override
    public List<Film> getAll() {
        return List.of();
    }

    @Override
    public Film getById(Long id) {
        return null;
    }

    @Override
    public Film getByGenre(Long id) {
        return null;
    }
}
