package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;

public class ParkingLot {
    private final List<ParkingSpace> passengerSpaces;
    private final List<ParkingSpace> truckSpaces;
    int truckSize;
    int usedPassengerSpacesForTrucks;

    public ParkingLot(int passengerSpaces, int truckSpaces, int truckSize) {
        this.passengerSpaces = new ArrayList<>();
        this.truckSpaces = new ArrayList<>();
        this.truckSize = truckSize;

        for (int i = 0; i < passengerSpaces; i++) {
            this.passengerSpaces.add(new ParkingSpaceImpl());
        }

        for (int i = 0; i < truckSpaces; i++) {
            this.truckSpaces.add(new ParkingSpaceImpl());
        }
    }

    public boolean park(Car car) {
        return car.getSize() == 1 ? parkPassengerCar() : parkTruck(car);
    }

    private boolean parkPassengerCar() {
        for (ParkingSpace space : passengerSpaces) {
            if (!space.isOccupied()) {
                space.occupy();
                return true;
            }
        }
        return false;
    }

    private boolean parkTruck(Car car) {
        /* Сначала пытаемся запарковать на место для грузовиков */
        for (ParkingSpace space : truckSpaces) {
            if (!space.isOccupied()) {
                space.occupy();
                return true;
            }
        }

        /* Если места для грузовиков заняты, пытаемся занять N мест для легковых машин */
        boolean parked = operateTruk(passengerSpaces, car.getSize(), space -> !space.isOccupied(), ParkingSpace::occupy);
        if (parked) {
            usedPassengerSpacesForTrucks += car.getSize();
        }
        return parked;
    }

    public boolean unpark(Car car) {
        return car.getSize() == 1 ? unparkPassengerCar() : unparkTruck(car);
    }

    private boolean unparkPassengerCar() {
        for (ParkingSpace space : passengerSpaces) {
            if (space.isOccupied()) {
                space.free();
                return true;
            }
        }
        return false;
    }

    private boolean unparkTruck(Car car) {
        /* Сначала пытаемся освободить место для грузовика */
        for (ParkingSpace space : truckSpaces) {
            if (space.isOccupied()) {
                space.free();
                return true;
            }
        }

        /* Если не нашли занятое место для грузовика, ищем N занятых мест для легковых машин */
        boolean unparked = operateTruk(passengerSpaces, car.getSize(), ParkingSpace::isOccupied, ParkingSpace::free);
        if (unparked) {
            usedPassengerSpacesForTrucks -= car.getSize();
        }
        return unparked;
    }

    private boolean operateTruk(List<ParkingSpace> spaces, int requiredSpaces, Predicate<ParkingSpace> condition, Consumer<ParkingSpace> action) {
        for (int i = 0; i <= spaces.size() - requiredSpaces; i++) {
            boolean canOperate = true;
            for (int j = i; j < i + requiredSpaces; j++) {
                if (!condition.test(spaces.get(j))) {
                    canOperate = false;
                    break;
                }
            }
            if (canOperate) {
                for (int j = i; j < i + requiredSpaces; j++) {
                    action.accept(spaces.get(j));
                }
                return true;
            }
        }
        return false;
    }

    public int getFreePassengerSpaces() {
        return (int) passengerSpaces.stream()
                .filter(space -> !space.isOccupied())
                .count();
    }

    public int getFreeTruckSpaces() {
        int freeTruckSpaces = (int) truckSpaces.stream()
                .filter(space -> !space.isOccupied())
                .count();
        int additionalTruckSpaces = getFreePassengerSpaces() / truckSize;
        return freeTruckSpaces + additionalTruckSpaces;
    }
}