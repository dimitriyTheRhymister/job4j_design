package ru.job4j.ood.lsp.parking;

public interface ParkingSpace {

    boolean isOccupied();

    void occupy();

    void free();
}
