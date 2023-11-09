package org.example.parking;

import org.example.parking.exceptions.ParkingLotFullException;
import org.example.parking.exceptions.VehicleNotparkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Vehicle> listOfParkedVehicles = new ArrayList<Vehicle>();

    public Notification notification = new Notification();

    public int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Vehicle park(Vehicle vehicle) throws ParkingLotFullException {
        if(capacity > 0) {
            capacity--;
            vehicle.isParked = true;
            listOfParkedVehicles.add(vehicle);
            notification.setNotifyMessage(capacity);
            return vehicle;
        }
        else {
            throw new ParkingLotFullException();
        }
    }

    public Vehicle unpark(Vehicle car1) throws VehicleNotparkedException {
        if(listOfParkedVehicles.contains(car1)) {
            listOfParkedVehicles.remove(car1);
            car1.isParked = false;
            capacity++;
            notification.setNotifyMessage(capacity);
        } else {
            throw new VehicleNotparkedException();
        }

        return car1;
    }

}
