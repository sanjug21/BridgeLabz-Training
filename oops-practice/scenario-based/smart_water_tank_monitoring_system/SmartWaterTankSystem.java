
import java.util.Scanner;

public class SmartWaterTankSystem {
    public static void main(String[] args) {
        WaterTankManager manager = new WaterTankManager();
        Scanner scanner = new Scanner(System.in);

        // Initialize with demo data
        initializeDemoData(manager);

        while (true) {
            System.out.println("\n========== SMART WATER TANK MONITORING SYSTEM ==========");
            System.out.println("1. Add New Tank");
            System.out.println("2. Update Water Level");
            System.out.println("3. Display All Tanks");
            System.out.println("4. Display Tanks by Lowest Level");
            System.out.println("5. Generate Alerts");
            System.out.println("6. Display System Summary");
            System.out.println("7. Exit");
            System.out.println("========================================================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewTank(manager, scanner);
                    break;

                case 2:
                    updateWaterLevel(manager, scanner);
                    break;

                case 3:
                    manager.displayAllTanks();
                    break;

                case 4:
                    manager.displayTanksByLowestLevel();
                    break;

                case 5:
                    manager.generateAlerts();
                    break;

                case 6:
                    manager.displaySystemSummary();
                    break;

                case 7:
                    System.out.println("Exiting Smart Water Tank Monitoring System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Initialize demo data
    private static void initializeDemoData(WaterTankManager manager) {
        try {
            manager.addTank(new WaterTank("T001", 1000, 850));
            manager.addTank(new WaterTank("T002", 2000, 300));
            manager.addTank(new WaterTank("T003", 1500, 150));
            manager.addTank(new WaterTank("T004", 800, 720));
            manager.addTank(new WaterTank("T005", 1200, 100));
            System.out.println("\nDemo data initialized with 5 water tanks.\n");
        } catch (InvalidWaterLevelException e) {
            System.out.println("Error initializing demo data: " + e.getMessage());
        }
    }

    // Add a new tank
    private static void addNewTank(WaterTankManager manager, Scanner scanner) {
        System.out.print("Enter Tank ID: ");
        String tankId = scanner.nextLine();

        System.out.print("Enter Tank Capacity (liters): ");
        double capacity = scanner.nextDouble();

        System.out.print("Enter Current Water Level (liters): ");
        double currentLevel = scanner.nextDouble();
        scanner.nextLine();

        try {
            WaterTank tank = new WaterTank(tankId, capacity, currentLevel);
            manager.addTank(tank);
            
            if (tank.needsAlert()) {
                System.out.println("WARNING: This tank has low water level (" + 
                        String.format("%.2f", tank.getUsagePercentage()) + "%)");
            }
        } catch (InvalidWaterLevelException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update water level
    private static void updateWaterLevel(WaterTankManager manager, Scanner scanner) {
        System.out.print("Enter Tank ID: ");
        String tankId = scanner.nextLine();

        System.out.print("Enter New Water Level (liters): ");
        double newLevel = scanner.nextDouble();
        scanner.nextLine();

        manager.updateWaterLevel(tankId, newLevel);
    }
}
