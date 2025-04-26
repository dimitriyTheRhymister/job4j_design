package ru.job4j.ood.lsp.parking;

public interface ParkingService {
    boolean park(Car car);
    boolean unpark(Car car);
}