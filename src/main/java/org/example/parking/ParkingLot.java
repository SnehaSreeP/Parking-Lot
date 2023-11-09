package org.example.parking;

import org.example.parking.exceptions.ParkingLotFullException;
import org.example.parking.exceptions.VehicleNotparkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Vehicle> listOfParkedVehicles = new ArrayList<Vehicle>();

    public String notifyOwner = "Slots available";
    public String notifyTrafficCop = "Slots available";
    ParkingSlot slot = new ParkingSlot();
    public Vehicle park(Vehicle vehicle) throws ParkingLotFullException {
        int slotsAvailable = slot.numberOfSlotsAvailable;
        if(slotsAvailable > 0) {
            slot.numberOfSlotsAvailable--;
            vehicle.isParked = true;
            listOfParkedVehicles.add(vehicle);
            setNotifyMessage();
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
            slot.numberOfSlotsAvailable++;
            setNotifyMessage();
        } else {
            throw new VehicleNotparkedException();
        }

        return car1;
    }

    public void setNotifyMessage() {
        if(slot.numberOfSlotsAvailable == 0) {
            this.notifyOwner = "Parking slots are full";
            this.notifyTrafficCop = "Parking slots are full please divert traffic";
        } else if(slot.numberOfSlotsAvailable > 0){
            this.notifyOwner = "Parking slots are available";
            this.notifyTrafficCop = "Parking slots are available please stop diverting traffic";
        }
    }
}
