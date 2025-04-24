package ru.job4j.ood.lsp.prostor.product;

public class NearlyExpiredFood extends Food {
    public NearlyExpiredFood(double price, int expiryDate, int createDate) {
        super(price, expiryDate, createDate);
    }

    @Override
    public void doSomethingSpecific() {
        System.out.println("I'm NearlyExpiredFood. Nearly expired food is being sold at a discount.");
    }
}