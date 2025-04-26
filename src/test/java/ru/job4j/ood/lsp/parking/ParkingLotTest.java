package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class ParkingLotTest {

    @Test
    public void testParkPassengerCar() {
        ParkingLot parkingLot = new ParkingLot(2, 1, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car passengerCar = new PassengerCar();
        assertTrue(parkingService.park(passengerCar));
    }

    @Test
    public void testParkTruckOnTruckSpace() {
        ParkingLot parkingLot = new ParkingLot(2, 1, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car truck = new Truck();
        assertTrue(parkingService.park(truck));
    }

    @Test
    public void testParkTruckOnPassengerSpaces() {
        ParkingLot parkingLot = new ParkingLot(4, 0, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car truck = new Truck(2);
        assertTrue(parkingService.park(truck));
    }

    @Test
    public void testUnpark() {
        ParkingLot parkingLot = new ParkingLot(2, 1, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car car = new PassengerCar();
        parkingService.park(car);
        assertTrue(parkingService.unpark(car));
    }

    @Test
    public void testUnparkTruck() {
        ParkingLot parkingLot = new ParkingLot(4, 1, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car truck = new Truck(2);
        parkingService.park(truck);
        assertTrue(parkingService.unpark(truck));
    }

    @Test
    public void testGetFreePassengerSpaces() {
        ParkingLot parkingLot = new ParkingLot(2, 1, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car passengerCar = new PassengerCar();
        assertEquals(2, parkingLot.getFreePassengerSpaces());
        parkingService.park(passengerCar);
        assertEquals(1, parkingLot.getFreePassengerSpaces());
    }

    @Test
    public void testGetFreeTruckSpaces() {
        ParkingLot parkingLot = new ParkingLot(4, 1, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car truck = new Truck(2);
        int expectedPassengerSpaces4Truks = parkingLot.getFreePassengerSpaces() / truck.getSize();
        assertEquals(expectedPassengerSpaces4Truks + 1, parkingLot.getFreeTruckSpaces());
        parkingService.park(truck);
        assertEquals(expectedPassengerSpaces4Truks, parkingLot.getFreeTruckSpaces());
    }
}