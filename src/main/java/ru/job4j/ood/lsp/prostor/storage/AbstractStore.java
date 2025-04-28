package ru.job4j.ood.lsp.prostor.storage;

import ru.job4j.ood.lsp.prostor.product.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final String name;
    protected List<Food> foods;

    public AbstractStore(String name) {
        this.name = name;
        this.foods = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    public List<Food> getAllFood() {
        return new ArrayList<>(foods);
    }

    public void clear() {
        foods.clear();
    }
}
