package ru.job4j.ood.lsp.prostor.product;

public class LongTermFood extends Food {
    public LongTermFood(double price, int expiryDate, int createDate) {
        super(price, expiryDate, createDate);
    }

    @Override
    public void doSomethingSpecific() {
        System.out.println("I'm LongTermFood. Long term food is being stored for a long time.");
    }
}