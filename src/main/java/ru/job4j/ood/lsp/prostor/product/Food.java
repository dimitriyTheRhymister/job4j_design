package ru.job4j.ood.lsp.prostor.product;

public abstract class Food {
    private double price;
    private final int expiryDate;
    private final int createDate;

    public Food(double price, int expiryDate, int createDate) {
        this.price = price;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getExpiryRate() {
        return (double) createDate / expiryDate;
    }

    public abstract void doSomethingSpecific();
}
