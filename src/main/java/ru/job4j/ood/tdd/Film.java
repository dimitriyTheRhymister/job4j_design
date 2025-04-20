package ru.job4j.ood.tdd;

import java.util.List;

public interface Film {

    List<Film> getAll();
    Film getById(Long id);
    Film getByGenre(Long id);
}
