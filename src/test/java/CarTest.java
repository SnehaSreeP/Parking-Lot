import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class CarTest {

    @Test
    public void shouldParkCarToCatchFlight() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        assertTrue(parkingLot.park(car));
    }

}
