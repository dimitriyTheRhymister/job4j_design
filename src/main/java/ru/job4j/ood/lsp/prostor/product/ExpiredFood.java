package ru.job4j.ood.lsp.prostor.product;

public class ExpiredFood extends Food {
    public ExpiredFood(double price, int expiryDate, int createDate) {
        super(price, expiryDate, createDate);
    }

    @Override
    public void doSomethingSpecific() {
        System.out.println("I'm ExpiredFood. Expired food is being disposed of.");
    }
}
