package ru.job4j.ood.lsp.prostor.storage;

import ru.job4j.ood.lsp.prostor.product.Food;

import java.util.List;

public interface Store {
    void add(Food food);
    boolean accept(Food food);

    String getName();

    List<Food> getAllFood();

    void clear();
}