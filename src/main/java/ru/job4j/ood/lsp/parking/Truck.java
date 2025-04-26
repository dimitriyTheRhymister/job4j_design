package ru.job4j.ood.lsp.parking;

public class Truck extends Car {
    public static final int DEFAULT_SIZE = 2;

    public Truck() {
        super(DEFAULT_SIZE);
    }

    public Truck(int size) {
        super(size);
    }
}
