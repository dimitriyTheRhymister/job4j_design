package ru.job4j.ood.lsp.prostor.product;

public class FreshFood extends Food {
    public FreshFood(double price, int expiryDate, int createDate) {
        super(price, expiryDate, createDate);
    }

    @Override
    public void doSomethingSpecific() {
        System.out.println("I'm FreshFood. Fresh food is being stored in a cool place.");
    }
}
