import java.util.*;


interface IRentable {
    double calculateRent(int days);
}

class Vehicle {
    protected String nameOfVehicle;
    protected double rentPerDay;
    boolean isAvailable;

    Vehicle(String nameOfVehicle, double rentPerDay) {
        this.nameOfVehicle = nameOfVehicle;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
    }

    public String getNameOfVehicle() {
        return nameOfVehicle;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

}

class Bike extends Vehicle implements IRentable {
    Bike(String nameOfVehicle, double rentPerDay) {
        super(nameOfVehicle, rentPerDay);
    }

    @Override
    public double calculateRent(int days) {
        return rentPerDay * days;
    }
}

class Car extends Vehicle implements IRentable {
    private double extraChargePerDay = 50;

    Car(String nameOfVehicle, double rentPerDay) {
        super(nameOfVehicle, rentPerDay);
    }

    @Override
    public double calculateRent(int days) {
        return (rentPerDay + extraChargePerDay) * days;
    }
}

class Truck extends Vehicle implements IRentable {
    private double cargoTax = 200;

    Truck(String nameOfVehicle, double rentPerDay) {
        super(nameOfVehicle, rentPerDay);
    }

    @Override
    public double calculateRent(int days) {
        return (rentPerDay * days) + cargoTax;
    }
}



public class VehicleRentalSystem {
    static List<Vehicle> fleet = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        fleet.add(new Bike("Yamaha R15", 500));
        fleet.add(new Car("Swift Dzire", 1500));
        fleet.add(new Truck("Tata Ace", 2500));

        while (true) {
            System.out.println("\n=== Vehicle Rental System ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Display Available Vehicles");
            System.out.println("3. Rent Vehicle (Calculate Cost)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Type (1. Bike, 2. Car, 3. Truck): ");
                    int type = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Model Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Rent Per Day: ");
                    double rent = sc.nextDouble();
                    
                    if (type == 1) fleet.add(new Bike(name, rent));
                    else if (type == 2) fleet.add(new Car(name, rent));
                    else if (type == 3) fleet.add(new Truck(name, rent));
                    else System.out.println("Invalid type.");
                    System.out.println("Vehicle added.");
                    break;
                case 2:
                    System.out.println("--- Fleet ---");
                    for (int i = 0; i < fleet.size(); i++) {
                        Vehicle v = fleet.get(i);
                        System.out.println((i + 1) + ". " + v.getNameOfVehicle() + " - Rate: " + v.getRentPerDay());
                    }
                    break;
                case 3:
                    System.out.print("Enter Vehicle Number from list to rent: ");
                    int idx = sc.nextInt() - 1;
                    if (idx >= 0 && idx < fleet.size()) {
                        System.out.print("Enter Number of Days: ");
                        int days = sc.nextInt();
                        Vehicle v = fleet.get(idx);
                        if (v instanceof IRentable) {
                            double cost = ((IRentable) v).calculateRent(days);
                            System.out.println("Total Rent for " + v.getNameOfVehicle() + " for " + days + " days is: " + cost);
                        }
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;
                case 4: System.exit(0);
            }
        }
    }
}