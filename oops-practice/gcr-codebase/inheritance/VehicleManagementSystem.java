interface Refuelable {
    void refuel();
    
}
interface Chargeable {
    void charge();
}


// using Gadi in place of vehicle as vehicle already exist with different properties ans creating an error while running the program
class Gaadi {
    String model;
    int maxSpeed;

    public Gaadi(String model, int maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public void displaySpecs() {
        System.out.println("Model: " + model);
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }
}

class ElectricVehicle extends Gaadi implements Chargeable{
    int batteryCapacity; // in kWh

    public ElectricVehicle(String model, int maxSpeed, int batteryCapacity) {
        super(model, maxSpeed);
        this.batteryCapacity = batteryCapacity;
    }
    
    @Override
    public void charge() {
        System.out.println("Action: Charging battery (" + batteryCapacity + " kWh capacity)...");
    }
}

class PetrolVehicle extends Gaadi implements Refuelable {
    String fuelType;

    public PetrolVehicle(String model, int maxSpeed, String fuelType) {
        super(model, maxSpeed);
        this.fuelType = fuelType;
    }

    @Override
    public void refuel() {
        System.out.println("Action: Refueling with " + fuelType + "...");
    }
}

public class VehicleManagementSystem {
    public static void main(String[] args) {
        // multiple inheritance
        ElectricVehicle ev = new ElectricVehicle("Tesla Model 3", 260, 75);
        ev.displaySpecs();
        ev.charge();
        System.out.println();

        PetrolVehicle pv = new PetrolVehicle("Ford Mustang", 250, "Petrol");
        pv.displaySpecs();
        pv.refuel();
    }
}