import org.example.parking.ParkingLot;
import org.example.parking.Vehicle;
import org.example.parking.exceptions.ParkingLotFullException;

import java.util.List;

public class Valet {
    private List<ParkingLot> parkingLots;

    public Valet(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    public Vehicle park(Vehicle vehicle) throws ParkingLotFullException {
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.capacity > 0) {
                vehicle = parkingLot.park(vehicle);
                break;
            }
        }
        if(!vehicle.isParked) {
            throw new ParkingLotFullException("All Parking Lots are full");
        }
        return vehicle;
    }
}
