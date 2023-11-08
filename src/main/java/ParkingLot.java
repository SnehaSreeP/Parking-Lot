public class ParkingLot {

    private boolean isParked = false;

    public boolean park(Vehicle vehicle) {
        isParked = true;
        return true;
    }
}
