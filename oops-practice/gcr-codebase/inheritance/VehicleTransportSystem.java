class Veh {
    int maxSpeed;
    String fuelType;

    Veh(int maxSpeed, String fuelType) {
        this.maxSpeed = maxSpeed;
        this.fuelType = fuelType;
    }

    void displayInfo() {
        System.out.println("Max Speed: " + maxSpeed + " km/h");
        System.out.println("Fuel Type: " + fuelType);
    }
}

class Car extends Veh {
    int seatCapacity;

    Car(int maxSpeed, String fuelType, int seatCapacity) {
        super(maxSpeed, fuelType);
        this.seatCapacity = seatCapacity;
    }

    @Override
    void displayInfo() {
        System.out.println("--- Car Details ---");
        super.displayInfo();
        System.out.println("Seat Capacity: " + seatCapacity);
    }
}

class Truck extends Veh {
    double loadCapacity; // in tons

    Truck(int maxSpeed, String fuelType, double loadCapacity) {
        super(maxSpeed, fuelType);
        this.loadCapacity = loadCapacity;
    }

    @Override
    void displayInfo() {
        System.out.println("--- Truck Details ---");
        super.displayInfo();
        System.out.println("Load Capacity: " + loadCapacity + " tons");
    }
}

class Motorcycle extends Veh {
    boolean hasSidecar;

    Motorcycle(int maxSpeed, String fuelType, boolean hasSidecar) {
        super(maxSpeed, fuelType);
        this.hasSidecar = hasSidecar;
    }

    @Override
    void displayInfo() {
        System.out.println("--- Motorcycle Details ---");
        super.displayInfo();
        System.out.println("Has Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }
}

public class VehicleTransportSystem {
    public static void main(String[] args) {
        // hierarchical inheritance
        Veh[] vehicles = {
            new Car(220, "Petrol", 5),
            new Truck(120, "Diesel", 15.5),
            new Motorcycle(180, "Petrol", false)
        };

        System.out.println("Vehicle Transport System");
        for (Veh v : vehicles) {
            v.displayInfo();
            System.out.println();
        }
    }
}