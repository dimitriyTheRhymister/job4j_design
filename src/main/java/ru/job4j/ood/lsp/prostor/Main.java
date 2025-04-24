package ru.job4j.ood.lsp.prostor;

import ru.job4j.ood.lsp.prostor.product.*;
import ru.job4j.ood.lsp.prostor.storage.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();

        List<Store> stores = Arrays.asList(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(stores);

        Food freshFood = new FreshFood(100, 22, 11);
        Food nearlyExpiredFood = new NearlyExpiredFood(100, 14, 11);
        Food expiredFood = new ExpiredFood(100, 11, 11);
        Food longTermFood = new LongTermFood(100, 222, 11);

        controlQuality.distribute(freshFood);
        controlQuality.distribute(nearlyExpiredFood);
        controlQuality.distribute(expiredFood);
        controlQuality.distribute(longTermFood);

        System.out.println("------------------------------");

        freshFood.doSomethingSpecific();
        nearlyExpiredFood.doSomethingSpecific();
        expiredFood.doSomethingSpecific();
        longTermFood.doSomethingSpecific();
    }
}
