package ru.job4j.ood.lsp.parking;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5, 2, 2);
        ParkingService parkingService = new ParkingServiceImpl(parkingLot);
        Car passengerCar = new PassengerCar();
        Car truck = new Truck(2);

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());

        System.out.println(parkingService.park(truck));
        System.out.println(parkingService.park(truck));

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());

        System.out.println(parkingService.park(truck));

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());

        System.out.println(parkingService.park(truck));

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());

        System.out.println(parkingService.park(truck));

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());

        System.out.println(parkingService.unpark(truck));

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());

        System.out.println(parkingService.park(passengerCar));

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());

        System.out.println(parkingService.park(truck));

        System.out.println(parkingLot.getFreePassengerSpaces());
        System.out.println(parkingLot.getFreeTruckSpaces());
    }
}
