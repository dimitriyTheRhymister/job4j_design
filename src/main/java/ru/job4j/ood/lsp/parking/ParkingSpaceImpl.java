package ru.job4j.ood.lsp.parking;

public class ParkingSpaceImpl implements ParkingSpace {
    private boolean occupied = false;

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public void occupy() {
        occupied = true;
    }

    @Override
    public void free() {
        occupied = false;
    }
}