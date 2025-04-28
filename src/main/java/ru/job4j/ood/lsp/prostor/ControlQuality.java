package ru.job4j.ood.lsp.prostor;

import ru.job4j.ood.lsp.prostor.product.Food;
import ru.job4j.ood.lsp.prostor.storage.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                System.out.println("Food: "
                        + food.getClass().getSimpleName()
                        + " with expiry rate "
                        + String.format("%.2f", food.getExpiryRate())
                        + " was distributed to "
                        + store.getName()
                        + " by price "
                        + food.getPrice());
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();

        for (Store store : stores) {
            foods.addAll(store.getAllFood());
            store.clear();
            System.out.println("Хранилище " + store.getName() + " очищено.");
        }

        for (Food food : foods) {
            distribute(food);
        }
    }
}
