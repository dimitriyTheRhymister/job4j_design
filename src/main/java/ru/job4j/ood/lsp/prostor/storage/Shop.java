package ru.job4j.ood.lsp.prostor.storage;

import ru.job4j.ood.lsp.prostor.product.Food;

public class Shop extends AbstractStore {
    public Shop() {
        super("Shop");
    }

    @Override
    public boolean accept(Food food) {
        return food.getExpiryRate() >= 0.25
                && food.getExpiryRate() < 1;
    }

    public void discount(Food food) {
        if (food.getExpiryRate() > 0.75) {
            food.setPrice(food.getPrice() * 0.8);
        }
    }

    @Override
    public void add(Food food) {
        super.add(food);
        discount(food);
    }
}
