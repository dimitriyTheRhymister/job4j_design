package ru.job4j.ood.lsp.parking;

public abstract class Car {
    private final int size;

    public Car(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
