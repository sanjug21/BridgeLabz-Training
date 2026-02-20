import java.util.Scanner;

public class SmartParkingSystem {

    public static void main(String[] args) {
        ParkingManager manager = new ParkingManager();
        Scanner sc = new Scanner(System.in);

        initializeDemoData(manager);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("  SMART PARKING SLOT ALLOCATION SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Parking Slot");
            System.out.println("2. Add Vehicle to Waiting Queue");
            System.out.println("3. Allocate Slot to Vehicle");
            System.out.println("4. Allocate Next from Queue");
            System.out.println("5. Release Slot");
            System.out.println("6. Display Slot Status");
            System.out.println("7. Display Waiting Queue");
            System.out.println("8. Display Statistics");
            System.out.println("9. Exit");
            System.out.print("\nChoose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addParkingSlot(manager, sc);
                        break;
                    case 2:
                        addVehicleToQueue(manager, sc);
                        break;
                    case 3:
                        allocateSlot(manager, sc);
                        break;
                    case 4:
                        manager.allocateNextFromQueue();
                        break;
                    case 5:
                        releaseSlot(manager, sc);
                        break;
                    case 6:
                        manager.displaySlotStatus();
                        break;
                    case 7:
                        manager.displayWaitingQueue();
                        break;
                    case 8:
                        manager.displayStatistics();
                        break;
                    case 9:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NoParkingSlotAvailableException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(ParkingManager manager) {
        manager.addParkingSlot(1, "Car");
        manager.addParkingSlot(2, "Car");
        manager.addParkingSlot(3, "Bike");
        manager.addParkingSlot(4, "Bike");
        manager.addParkingSlot(5, "Car");

        try {
            Vehicle c1 = new Car("MH12AB1234", "Rahul");
            manager.allocateSlot(c1);

            Vehicle b1 = new Bike("KA01CD5678", "Priya");
            manager.allocateSlot(b1);

            manager.addToWaitingQueue(new Car("DL03EF9012", "Amit"));
            manager.addToWaitingQueue(new Bike("TN09GH3456", "Sneha"));

            System.out.println("\nDemo data initialized successfully!");

        } catch (NoParkingSlotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addParkingSlot(ParkingManager manager, Scanner sc) {
        System.out.print("\nEnter Slot Number: ");
        int slotNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Slot Type (Car/Bike): ");
        String slotType = sc.nextLine();

        manager.addParkingSlot(slotNumber, slotType);
    }

    private static void addVehicleToQueue(ParkingManager manager, Scanner sc) {
        System.out.print("\nEnter Vehicle Type (1-Car, 2-Bike): ");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = sc.nextLine();

        System.out.print("Enter Owner Name: ");
        String ownerName = sc.nextLine();

        Vehicle vehicle;
        if (type == 1) {
            vehicle = new Car(vehicleNumber, ownerName);
        } else {
            vehicle = new Bike(vehicleNumber, ownerName);
        }

        manager.addToWaitingQueue(vehicle);
    }

    private static void allocateSlot(ParkingManager manager, Scanner sc) 
            throws NoParkingSlotAvailableException {
        System.out.print("\nEnter Vehicle Type (1-Car, 2-Bike): ");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = sc.nextLine();

        System.out.print("Enter Owner Name: ");
        String ownerName = sc.nextLine();

        Vehicle vehicle;
        if (type == 1) {
            vehicle = new Car(vehicleNumber, ownerName);
        } else {
            vehicle = new Bike(vehicleNumber, ownerName);
        }

        manager.allocateSlot(vehicle);
    }

    private static void releaseSlot(ParkingManager manager, Scanner sc) {
        System.out.print("\nEnter Slot Number: ");
        int slotNumber = sc.nextInt();
        sc.nextLine();

        manager.releaseSlot(slotNumber);
    }
}
