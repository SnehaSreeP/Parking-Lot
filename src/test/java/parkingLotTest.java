
import org.example.parking.*;
import org.example.parking.exceptions.ParkingLotFullException;
import org.example.parking.exceptions.VehicleNotparkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class parkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    @Test
    public void carIsParkedToCatchTheFlight() throws ParkingLotFullException {
        Vehicle car = new Car();
        car = parkingLot.park(car);
        assertTrue(car.isParked);
    }

    @Test
    public void carIsNotParkedToCatchTheFlight() {
        Vehicle car = new Car();
        assertFalse(car.isParked);
    }

    @Test
    public void parkMoreThanOneCar() throws VehicleNotparkedException, ParkingLotFullException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        Vehicle car4 = new Car();
        car1 = parkingLot.park(car1);
        car2 = parkingLot.park(car2);
        car3 = parkingLot.park(car3);
        assertTrue(car1.isParked);
        assertTrue(car2.isParked);
        assertTrue(car3.isParked);
        Vehicle finalCar = car4;
        assertThrows(ParkingLotFullException.class,() -> parkingLot.park(finalCar));
    }

    @Test
    public void unparkParkedCar() throws VehicleNotparkedException, ParkingLotFullException {
        Vehicle car1 = new Car();
        car1 = parkingLot.park(car1);
        car1 = parkingLot.unpark(car1);
        assertFalse(car1.isParked);
    }

    @Test
    public void unparkUnParkedCar() throws VehicleNotparkedException, ParkingLotFullException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        car1 = parkingLot.park(car1);
        assertThrows(VehicleNotparkedException.class,() -> parkingLot.unpark(car2));
    }

    @Test
    public void notifyOwnerWhenTheSlotsAreFull() throws ParkingLotFullException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        car1 = parkingLot.park(car1);
        car2 = parkingLot.park(car2);
        car3 = parkingLot.park(car3);
        assertEquals(parkingLot.notifyOwner, "Parking slots are full");
    }

    @Test
    public void notifyTrafficCopWhenTheSlotsAreFull() throws ParkingLotFullException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        car1 = parkingLot.park(car1);
        car2 = parkingLot.park(car2);
        car3 = parkingLot.park(car3);
        assertEquals(parkingLot.notifyTrafficCop, "Parking slots are full please divert traffic");
    }

//    As a parking lot owner I would like to be notified when the parking lot is available for parking
//    again so that I can remove the sign.
//    As a parking lot security, I would like to be notified when the parking lot is available for
//    parking again so that I can stop redirecting traffic away from the lot.

    @Test
    public void notifyOwnerWhenParkingLotIsAvailable() throws ParkingLotFullException, VehicleNotparkedException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        car1 = parkingLot.park(car1);
        car2 = parkingLot.park(car2);
        car3 = parkingLot.park(car3);
        car1 = parkingLot.unpark(car1);
        assertEquals(parkingLot.notifyOwner,"Parking slots are available");
    }

    @Test
    public void notifyTrafficCopWhenParkingLotIsAvailable() throws ParkingLotFullException, VehicleNotparkedException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        car1 = parkingLot.park(car1);
        car2 = parkingLot.park(car2);
        car3 = parkingLot.park(car3);
        car1 = parkingLot.unpark(car1);
        assertEquals(parkingLot.notifyTrafficCop,"Parking slots are available please stop diverting traffic");
    }
}
