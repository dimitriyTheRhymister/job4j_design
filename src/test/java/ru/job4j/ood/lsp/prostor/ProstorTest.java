package ru.job4j.ood.lsp.prostor;

import org.junit.Test;
import ru.job4j.ood.lsp.prostor.product.*;
import ru.job4j.ood.lsp.prostor.storage.Shop;
import ru.job4j.ood.lsp.prostor.storage.Store;
import ru.job4j.ood.lsp.prostor.storage.Trash;
import ru.job4j.ood.lsp.prostor.storage.Warehouse;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ProstorTest {

    @Test
    public void whenAddFoodToShopThenFoodIsStored() {
        Store shop = new Shop();
        Food food = new FreshFood(100, 22, 11);
        shop.add(food);
        assertFalse(shop.getAllFood().isEmpty());
        assertNotEquals(80.0, food.getPrice(), 0.01);
    }

    @Test
    public void whenAddFoodToShopThenFoodIsStoredAndDiscounted() {
        Store shop = new Shop();
        Food food = new NearlyExpiredFood(100, 14, 11);
        shop.add(food);
        assertFalse(shop.getAllFood().isEmpty());
        assertEquals(80.0, food.getPrice(), 0.01);
    }

    @Test
    public void whenAddFoodToTrashThenFoodIsStored() {
        Store trash = new Trash();
        Food food = new ExpiredFood(100, 11, 11);
        trash.add(food);
        assertFalse(trash.getAllFood().isEmpty());
        assertTrue(trash.getAllFood().contains(food));
    }

    @Test
    public void whenAddFoodToWarehouseThenFoodIsStored() {
        Store warehouse = new Warehouse();
        Food food = new LongTermFood(100, 222, 11);
        warehouse.add(food);
        assertFalse(warehouse.getAllFood().isEmpty());
        assertEquals((double) 11 / 222, food.getExpiryRate(), 0.01);
    }

    @Test
    public void whenDistributeFoodThenItGoesToCorrectStore() {
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();

        List<Store> stores = Arrays.asList(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(stores);

        Food freshFood = new FreshFood(100, 22, 11);
        Food nearlyExpiredFood = new FreshFood(100, 14, 11);
        Food expiredFood = new FreshFood(100, 11, 11);
        Food longTermFood = new FreshFood(100, 222, 11);

        controlQuality.distribute(freshFood);
        controlQuality.distribute(nearlyExpiredFood);
        controlQuality.distribute(expiredFood);
        controlQuality.distribute(longTermFood);

        assertTrue(warehouse.getAllFood().contains(longTermFood));
        assertTrue(shop.getAllFood().contains(freshFood));
        assertTrue(shop.getAllFood().contains(nearlyExpiredFood));
        assertTrue(trash.getAllFood().contains(expiredFood));
    }
}
