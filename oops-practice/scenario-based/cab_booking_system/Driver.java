package cab_booking_system;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String driverId;
    private String name;
    private String vehicleDetails;
    private boolean available;
    private List<Ride> rides;


    public Driver(String driverId, String name, String vehicleDetails) {
        this.driverId = driverId;
        this.name = name;
        this.vehicleDetails = vehicleDetails;
        this.available = true; // Drivers are available by default when created
        this.rides=new ArrayList<>();
    }

    public List<Ride> getRides() {
        return rides;
    }
    public void addRide(Ride ride) {
        rides.add(ride);
    }

    public String getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Driver{" +
               "driverId='" + driverId + '\'' +
               ", name='" + name + '\'' +
               ", vehicleDetails='" + vehicleDetails + '\'' +
               ", available=" + available +
               '}';
    }

}