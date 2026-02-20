abstract class Vehicle {
    String vehicleNumber;
    String ownerName;

    public Vehicle(String vehicleNumber, String ownerName) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }

    public abstract String getVehicleType();

    @Override
    public String toString() {
        return vehicleNumber + " | " + ownerName + " | " + getVehicleType();
    }
}

class Car extends Vehicle {
    public Car(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

class Bike extends Vehicle {
    public Bike(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}
