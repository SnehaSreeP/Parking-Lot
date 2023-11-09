package org.example.parking.exceptions;

public class VehicleNotparkedException extends Exception {

    public VehicleNotparkedException() {}
    public VehicleNotparkedException(String message) {
        super(message);
    }
}
