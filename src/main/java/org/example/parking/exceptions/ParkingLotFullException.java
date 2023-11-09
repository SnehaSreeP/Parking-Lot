package org.example.parking.exceptions;

public class ParkingLotFullException extends Exception {

    public ParkingLotFullException() {}
    public ParkingLotFullException(String message) {
        super(message);
    }
}
