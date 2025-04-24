package ru.job4j.ood.lsp.prostor.storage;

import ru.job4j.ood.lsp.prostor.product.Food;

public class Trash extends AbstractStore {
    public Trash() {
        super("Trash");
    }

    @Override
    public boolean accept(Food food) {
        return food.getExpiryRate() >= 1;
    }
}
