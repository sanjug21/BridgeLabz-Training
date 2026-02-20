import java.util.Scanner;

public class EVChargingSystem {

    public static void main(String[] args) {
        ChargingStationManager manager = new ChargingStationManager();
        Scanner sc = new Scanner(System.in);

        initializeDemoData(manager);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("  EV CHARGING STATION MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Charging Slot");
            System.out.println("2. Add Vehicle to Waiting Queue");
            System.out.println("3. Allocate Slot to Vehicle");
            System.out.println("4. Allocate Next from Queue");
            System.out.println("5. Update Units Consumed");
            System.out.println("6. Release Slot and Calculate Bill");
            System.out.println("7. Display Slot Status");
            System.out.println("8. Display Waiting Queue");
            System.out.println("9. Display Statistics");
            System.out.println("10. Exit");
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
                        addChargingSlot(manager, sc);
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
                        updateUnits(manager, sc);
                        break;
                    case 6:
                        releaseSlot(manager, sc);
                        break;
                    case 7:
                        manager.displaySlotStatus();
                        break;
                    case 8:
                        manager.displayWaitingQueue();
                        break;
                    case 9:
                        manager.displayStatistics();
                        break;
                    case 10:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NoChargingSlotAvailableException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(ChargingStationManager manager) {
        manager.addChargingSlot(1);
        manager.addChargingSlot(2);
        manager.addChargingSlot(3);

        try {
            Vehicle v1 = new Vehicle("KA01AB1234", "Rahul", "Tesla Model 3");
            manager.allocateSlot(v1);
            manager.updateUnitsConsumed(1, 25.5);

            Vehicle v2 = new Vehicle("MH12CD5678", "Priya", "Tata Nexon EV");
            manager.allocateSlot(v2);
            manager.updateUnitsConsumed(2, 18.0);

            manager.addToWaitingQueue(new Vehicle("DL03EF9012", "Amit", "MG ZS EV"));
            manager.addToWaitingQueue(new Vehicle("TN09GH3456", "Sneha", "Hyundai Kona"));

            System.out.println("\nDemo data initialized successfully!");

        } catch (NoChargingSlotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addChargingSlot(ChargingStationManager manager, Scanner sc) {
        System.out.print("\nEnter Slot ID: ");
        int slotId = sc.nextInt();
        sc.nextLine();
        manager.addChargingSlot(slotId);
    }

    private static void addVehicleToQueue(ChargingStationManager manager, Scanner sc) {
        System.out.print("\nEnter Vehicle Number: ");
        String vehicleNumber = sc.nextLine();

        System.out.print("Enter Owner Name: ");
        String ownerName = sc.nextLine();

        System.out.print("Enter Vehicle Type: ");
        String vehicleType = sc.nextLine();

        Vehicle vehicle = new Vehicle(vehicleNumber, ownerName, vehicleType);
        manager.addToWaitingQueue(vehicle);
    }

    private static void allocateSlot(ChargingStationManager manager, Scanner sc) 
            throws NoChargingSlotAvailableException {
        System.out.print("\nEnter Vehicle Number: ");
        String vehicleNumber = sc.nextLine();

        System.out.print("Enter Owner Name: ");
        String ownerName = sc.nextLine();

        System.out.print("Enter Vehicle Type: ");
        String vehicleType = sc.nextLine();

        Vehicle vehicle = new Vehicle(vehicleNumber, ownerName, vehicleType);
        manager.allocateSlot(vehicle);
    }

    private static void updateUnits(ChargingStationManager manager, Scanner sc) {
        System.out.print("\nEnter Slot ID: ");
        int slotId = sc.nextInt();

        System.out.print("Enter Units Consumed: ");
        double units = sc.nextDouble();
        sc.nextLine();

        manager.updateUnitsConsumed(slotId, units);
    }

    private static void releaseSlot(ChargingStationManager manager, Scanner sc) {
        System.out.print("\nEnter Slot ID: ");
        int slotId = sc.nextInt();
        sc.nextLine();

        System.out.println("\nSelect Pricing:");
        System.out.println("1. Normal Hours (Rs. 8/unit)");
        System.out.println("2. Peak Hours (Rs. 12/unit)");
        System.out.println("3. Off-Peak Hours (Rs. 5/unit)");
        System.out.print("Choice: ");
        int pricingChoice = sc.nextInt();
        sc.nextLine();

        PricingStrategy pricing;
        switch (pricingChoice) {
            case 1:
                pricing = new NormalPricing();
                break;
            case 2:
                pricing = new PeakHourPricing();
                break;
            case 3:
                pricing = new OffPeakPricing();
                break;
            default:
                System.out.println("Invalid choice. Using Normal pricing.");
                pricing = new NormalPricing();
        }

        manager.releaseSlotAndCalculateBill(slotId, pricing);
    }
}
