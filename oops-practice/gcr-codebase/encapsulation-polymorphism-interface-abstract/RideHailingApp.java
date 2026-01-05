import java.util.ArrayList;
import java.util.List;

// Interface for GPS tracking
interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

// Abstract class for Vehicle
abstract class DiffVehicleDetails {
    private String vehicleId;
    private String driverName;
    private double ratePerKm;

    public DiffVehicleDetails(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
    }

    // Encapsulation: Getters
    public String getVehicleId() { return vehicleId; }
    public String getDriverName() { return driverName; }
    public double getRatePerKm() { return ratePerKm; }
    // declared abstract methods
    public abstract double calculateFare(double distance);

    public void getVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Driver: " + driverName);
        System.out.println("Rate/Km: Rs " + ratePerKm);
    }
}

class Carrs extends DiffVehicleDetails implements GPS {
    private String currentLocation;

    public Carrs(String id, String driver, double rate, String location) {
        super(id, driver, rate);
        this.currentLocation = location;
    }
    // abstract method implementation
    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance + 50; // Base fare 50
    }
    // interface methods
    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }
    // interface methods
    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Car location updated to: " + newLocation);
    }
}

class MotorCycle extends DiffVehicleDetails implements GPS {
    private String currentLocation;

    public MotorCycle(String id, String driver, double rate, String location) {
        super(id, driver, rate);
        this.currentLocation = location;
    }
    // abstract method implementation
    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance; // No base fare
    }
    // interface methods
    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }
    // interface methods
    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Bike location updated to: " + newLocation);
    }
}

class Auto extends DiffVehicleDetails implements GPS {
    private String currentLocation;

    public Auto(String id, String driver, double rate, String location) {
        super(id, driver, rate);
        this.currentLocation = location;
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance + 20; // Base fare 20
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Auto location updated to: " + newLocation);
    }
}

public class RideHailingApp {
    public static void main(String[] args) {
        List<DiffVehicleDetails> rides = new ArrayList<>();
        rides.add(new Carrs("C001", "Raj", 15, "Downtown"));
        rides.add(new MotorCycle("B001", "Amit", 8, "Uptown"));
        rides.add(new Auto("A001", "Vikram", 10, "Midtown"));

        double distance = 10.0; // 10 km ride
        System.out.println("=== Ride Hailing Application ===");
        
        for (DiffVehicleDetails v : rides) {
            v.getVehicleDetails();
            System.out.println("Estimated Fare for " + distance + " km: Rs " + v.calculateFare(distance));
            
            if (v instanceof GPS) {
                GPS gps = (GPS) v;
                System.out.println("Current Location: " + gps.getCurrentLocation());
                gps.updateLocation("Destination Point");
            }
            System.out.println("------------------------------");
        }
    }
}