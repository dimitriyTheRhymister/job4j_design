package ru.job4j.ood.lsp.prostor;

import ru.job4j.ood.lsp.prostor.product.Food;
import ru.job4j.ood.lsp.prostor.storage.Store;

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
}
