
import org.example.parking.*;
import org.example.parking.exceptions.ParkingLotFullException;
import org.example.parking.exceptions.VehicleNotparkedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class parkingLotTest {
    ParkingLot parkingLot = new ParkingLot(3);
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
        assertEquals(parkingLot.notification.notifyOwner, "Parking slots are full");
    }

    @Test
    public void notifyTrafficCopWhenTheSlotsAreFull() throws ParkingLotFullException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        car1 = parkingLot.park(car1);
        car2 = parkingLot.park(car2);
        car3 = parkingLot.park(car3);
        assertEquals(parkingLot.notification.notifyTrafficCop, "Parking slots are full please divert traffic");
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
        assertEquals(parkingLot.notification.notifyOwner,"Parking slots are available");
    }

    @Test
    public void notifySecurityWhenParkingLotIsAvailable() throws ParkingLotFullException, VehicleNotparkedException {
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        car1 = parkingLot.park(car1);
        car2 = parkingLot.park(car2);
        car3 = parkingLot.park(car3);
        car1 = parkingLot.unpark(car1);
        assertEquals(parkingLot.notification.notifySecurity,"Parking slots are available");
    }

//    Requirement 7 - Valet / Attendant
//    Valet managing multiple parking lots.
//    A Valet is responsible for parking and unparking cars from multiple parking lots. When asked to park a car,
//    valet parks the car in the first lot with free space.

    @Test
    public void valetParkingCarInParkingLot() throws ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Valet valet = new Valet(parkingLots);
        Vehicle car1 = new Car();
        car1 = valet.park(car1);
        assertTrue(car1.isParked);
        Vehicle car2 = new Car();
        car2 = valet.park(car2);
        assertTrue(car2.isParked);
        Vehicle car3 = new Car();
        car3 = valet.park(car3);
        assertTrue(car3.isParked);
        Vehicle car4 = new Car();
        assertThrows(ParkingLotFullException.class,() -> valet.park(car4));
    }
}
