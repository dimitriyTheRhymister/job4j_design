package ru.job4j.ood.lsp.parking;

public class ParkingServiceImpl implements ParkingService {
    private final ParkingLot parkingLot;

    public ParkingServiceImpl(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public boolean park(Car car) {
        return parkingLot.park(car);
    }

    @Override
    public boolean unpark(Car car) {
        return parkingLot.unpark(car);
    }
}
