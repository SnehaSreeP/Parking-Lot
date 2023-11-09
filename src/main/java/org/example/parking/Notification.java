package org.example.parking;

public class Notification {

    public String notifyOwner = "Slots available";
    public String notifyTrafficCop = "Slots available";
    public String notifySecurity = "Slots available";

    public void setNotifyMessage(int capacity) {
        if(capacity == 0) {
            this.notifyOwner = "Parking slots are full";
            this.notifyTrafficCop = "Parking slots are full please divert traffic";
        } else if(capacity > 0){
            this.notifyOwner = "Parking slots are available";
            this.notifyTrafficCop = "Parking slots are available";
            this.notifySecurity = "Parking slots are available";
        }
    }
}
