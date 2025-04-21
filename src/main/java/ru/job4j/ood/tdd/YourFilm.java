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
/* нарушение принципа SRP:
    - getAll(), getById() и getByGenre() - лучше вынести в отдельный FilmService
ведь создание фильма с его параметрами, которые например можно устанавливать или получать это одна сущность и одно дело, а поиск и получение фильмов по определённым условиям - это другое дело = дело взаимодействия между пользователем и репозиторием, зде хранятся фильмы
 */