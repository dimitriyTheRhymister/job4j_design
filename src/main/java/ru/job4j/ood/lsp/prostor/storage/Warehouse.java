package ru.job4j.ood.lsp.prostor.storage;

import ru.job4j.ood.lsp.prostor.product.Food;

public class Warehouse extends AbstractStore {
    public Warehouse() {
        super("Warehouse");
    }

    @Override
    public boolean accept(Food food) {
        return food.getExpiryRate() < 0.25;
    }
}