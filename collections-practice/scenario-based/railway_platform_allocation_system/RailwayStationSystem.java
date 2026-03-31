import java.time.LocalTime;
import java.util.Scanner;

public class RailwayStationSystem {

    public static void main(String[] args) {
        RailwayPlatformManager manager = new RailwayPlatformManager();
        Scanner sc = new Scanner(System.in);

        // Initialize demo data
        initializeDemoData(manager);

        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║  RAILWAY PLATFORM ALLOCATION SYSTEM    ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Add Platform");
            System.out.println("2. Add Incoming Train");
            System.out.println("3. Allocate Next Train");
            System.out.println("4. Process All Trains");
            System.out.println("5. Release Platform");
            System.out.println("6. Display Platform Status");
            System.out.println("7. Display Incoming Trains");
            System.out.println("8. Check Platform Conflicts");
            System.out.println("9. Display Statistics");
            System.out.println("10. Exit");
            System.out.print("\nChoose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        addPlatform(manager, sc);
                        break;
                    case 2:
                        addIncomingTrain(manager, sc);
                        break;
                    case 3:
                        manager.allocateNextTrain();
                        break;
                    case 4:
                        manager.processAllTrains();
                        break;
                    case 5:
                        releasePlatform(manager, sc);
                        break;
                    case 6:
                        manager.displayPlatformStatus();
                        break;
                    case 7:
                        manager.displayIncomingTrains();
                        break;
                    case 8:
                        manager.checkPlatformConflicts();
                        break;
                    case 9:
                        manager.displayStatistics();
                        break;
                    case 10:
                        System.out.println("Exiting system. Safe journey!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (PlatformUnavailableException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(RailwayPlatformManager manager) {
        // Add platforms
        manager.addPlatform(1, "Express");
        manager.addPlatform(2, "Passenger");
        manager.addPlatform(3, "Express");
        manager.addPlatform(4, "Passenger");
        manager.addPlatform(5, "Express");

        // Add incoming trains (PriorityQueue will sort by arrival time)
        manager.addIncomingTrain(new Train("12345", "Rajdhani Express", 
            LocalTime.of(14, 30), "Delhi", "Mumbai", 1));
        manager.addIncomingTrain(new Train("54321", "Shatabdi Express", 
            LocalTime.of(10, 15), "Chennai", "Bangalore", 3));
        manager.addIncomingTrain(new Train("67890", "Duronto Express", 
            LocalTime.of(16, 45), "Kolkata", "Delhi", 2));
        manager.addIncomingTrain(new Train("11223", "Local Passenger", 
            LocalTime.of(9, 30), "Mumbai", "Pune", 4));
        manager.addIncomingTrain(new Train("44556", "Garib Rath", 
            LocalTime.of(12, 0), "Hyderabad", "Chennai", 1));
        manager.addIncomingTrain(new Train("77889", "Humsafar Express", 
            LocalTime.of(15, 20), "Jaipur", "Mumbai", 5));

        System.out.println("\nDemo data initialized successfully!");
        System.out.println("  5 platforms added");
        System.out.println("  6 trains added to arrival queue");
    }

    private static void addPlatform(RailwayPlatformManager manager, Scanner sc) {
        System.out.print("\nEnter Platform Number: ");
        int platformNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Platform Type (Express/Passenger): ");
        String platformType = sc.nextLine();

        manager.addPlatform(platformNumber, platformType);
    }

    private static void addIncomingTrain(RailwayPlatformManager manager, Scanner sc) {
        System.out.print("\nEnter Train Number: ");
        String trainNumber = sc.nextLine();

        System.out.print("Enter Train Name: ");
        String trainName = sc.nextLine();

        System.out.print("Enter Arrival Time (HH:MM): ");
        String timeStr = sc.nextLine();
        String[] timeParts = timeStr.split(":");
        LocalTime arrivalTime = LocalTime.of(
            Integer.parseInt(timeParts[0]),
            Integer.parseInt(timeParts[1])
        );

        System.out.print("Enter Source Station: ");
        String source = sc.nextLine();

        System.out.print("Enter Destination Station: ");
        String destination = sc.nextLine();

        System.out.print("Enter Preferred Platform Number: ");
        int platformPreference = sc.nextInt();
        sc.nextLine();

        Train train = new Train(trainNumber, trainName, arrivalTime, 
                               source, destination, platformPreference);
        manager.addIncomingTrain(train);
    }

    private static void releasePlatform(RailwayPlatformManager manager, Scanner sc)
            throws PlatformUnavailableException {
        System.out.print("\nEnter Train Number to release platform: ");
        String trainNumber = sc.nextLine();
        manager.releasePlatform(trainNumber);
    }
}
