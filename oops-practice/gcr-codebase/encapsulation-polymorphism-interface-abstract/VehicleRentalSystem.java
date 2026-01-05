import java.util.ArrayList;
import java.util.List;

// Vehicle interface for common methods
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}
// Abstract class for vehicles to implement common methods in their subclasses
abstract class VehDetails {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    public VehDetails(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }
    // getter and setter methods for vehicleNumber, type, and rentalRate
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getRentalRate() { return rentalRate; }
    public void setRentalRate(double rentalRate) { this.rentalRate = rentalRate; }
    // abstract method to calculate rental cost for vehicles will be implemented in subclasses
    public abstract double calculateRentalCost(int days);
}

class Cars extends VehDetails implements Insurable {
    private String insurancePolicyNumber;

    public Cars(String number, double rate, String policyNumber) {
        super(number, "Car", rate);
        this.insurancePolicyNumber = policyNumber;
    }
    // abstract method to calculate rental cost for cars
    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }
    // Insurable interface methods
    @Override
    public double calculateInsurance() {
        return 50.0; // Flat insurance rate
    }
    // Insurable interface methods
    @Override
    public String getInsuranceDetails() {
        return "Policy #" + insurancePolicyNumber;
    }
}

class Bikes extends VehDetails implements Insurable {
    private String insurancePolicyNumber;

    public Bikes(String number, double rate, String policyNumber) {
        super(number, "Bike", rate);
        this.insurancePolicyNumber = policyNumber;
    }
    // abstract method to calculate rental cost for bikes
    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }
    // Insurable interface methods
    @Override
    public double calculateInsurance() {
        return 10.0;
    }
    // Insurable interface methods
    @Override
    public String getInsuranceDetails() {
        return "Policy #" + insurancePolicyNumber;
    }
}

class Trucks extends VehDetails implements Insurable {
    private String insurancePolicyNumber;

    public Trucks(String number, double rate, String policyNumber) {
        super(number, "Truck", rate);
        this.insurancePolicyNumber = policyNumber;
    }
    // abstract method to calculate rental cost for trucks
    @Override
    public double calculateRentalCost(int days) {
        return (getRentalRate() * days) + 100; // Extra charge for trucks
    }
    // Insurable interface methods
    @Override
    public double calculateInsurance() {
        return 100.0;
    }
    // Insurable interface methods
    @Override
    public String getInsuranceDetails() {
        return "Policy #" + insurancePolicyNumber;
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        List<VehDetails> vehicles = new ArrayList<>();
        vehicles.add(new Cars("C-123", 50, "POL-CAR-001"));
        vehicles.add(new Bikes("B-456", 15, "POL-BIKE-002"));
        vehicles.add(new Trucks("T-789", 100, "POL-TRUCK-003"));

        int rentalDays = 5;
        System.out.println("=== Vehicle Rental System (" + rentalDays + " days) ===");
        
        for (VehDetails v : vehicles) {
            double cost = v.calculateRentalCost(rentalDays);
            double insurance = 0;
            String policy = "N/A";
            // calculates insurance based on the vehicle type
            // checks if the vehicle implements the Insurable interface and calls the appropriate methods
            if (v instanceof Insurable) {
                insurance = ((Insurable) v).calculateInsurance();
                policy = ((Insurable) v).getInsuranceDetails();
            }

            System.out.println("Vehicle: " + v.getType() + " (" + v.getVehicleNumber() + ")");
            System.out.println("Rental Cost: Rs " + cost);
            System.out.println("Insurance Cost: Rs " + insurance);
            System.out.println("Insurance Details: " + policy);
            System.out.println("Total Cost: Rs " + (cost + insurance));
            System.out.println("\n");
        }
    }
}