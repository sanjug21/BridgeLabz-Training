import java.time.LocalDate;
import java.util.Scanner;

public class TrafficFineSystem {

    public static void main(String[] args) {
        TrafficFineManager manager = new TrafficFineManager();
        Scanner sc = new Scanner(System.in);

        // Demo data - pre-populate with some violations
        initializeDemoData(manager);

        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║  SMART TRAFFIC FINE COLLECTION SYSTEM  ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Record New Violation");
            System.out.println("2. View Violation History");
            System.out.println("3. Calculate Total Fine");
            System.out.println("4. Generate Monthly Report");
            System.out.println("5. View All Records");
            System.out.println("6. View Statistics");
            System.out.println("7. Exit");
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
                        recordNewViolation(manager, sc);
                        break;
                    case 2:
                        viewViolationHistory(manager, sc);
                        break;
                    case 3:
                        calculateFine(manager, sc);
                        break;
                    case 4:
                        generateReport(manager, sc);
                        break;
                    case 5:
                        manager.printAllRecords();
                        break;
                    case 6:
                        manager.printStatistics();
                        break;
                    case 7:
                        System.out.println("Exiting system. Stay safe on roads!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidVehicleException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(TrafficFineManager manager) {
        try {
            // Vehicle 1 - Repeat Offender (4 violations)
            manager.recordViolation("MH12AB1234",
                new SpeedingViolation(LocalDate.of(2026, 2, 1), "Mumbai Highway", 80, 110));
            manager.recordViolation("MH12AB1234",
                new RedLightViolation(LocalDate.of(2026, 2, 5), "Andheri Junction"));
            manager.recordViolation("MH12AB1234",
                new ParkingViolation(LocalDate.of(2026, 2, 10), "Bandra", "No Parking"));
            manager.recordViolation("MH12AB1234",
                new SpeedingViolation(LocalDate.of(2026, 2, 15), "Western Express", 60, 95));

            // Vehicle 2 - Regular offender (2 violations)
            manager.recordViolation("DL01CD5678",
                new HelmetViolation(LocalDate.of(2026, 2, 8), "Delhi CP"));
            manager.recordViolation("DL01CD5678",
                new SeatbeltViolation(LocalDate.of(2026, 2, 12), "Connaught Place"));

            // Vehicle 3 - Single violation
            manager.recordViolation("KA03EF9012",
                new SpeedingViolation(LocalDate.of(2026, 2, 18), "Bangalore Ring Road", 100, 145));

            // Vehicle 4 - Old violations (January)
            manager.recordViolation("TN09GH3456",
                new RedLightViolation(LocalDate.of(2026, 1, 20), "Chennai Anna Nagar"));
            manager.recordViolation("TN09GH3456",
                new ParkingViolation(LocalDate.of(2026, 1, 25), "T Nagar", "Reserved"));

            System.out.println("\nDemo data initialized successfully!");

        } catch (InvalidVehicleException e) {
            System.out.println("Error initializing demo data: " + e.getMessage());
        }
    }

    private static void recordNewViolation(TrafficFineManager manager, Scanner sc)
            throws InvalidVehicleException {
        System.out.print("\nEnter Vehicle Number (e.g., MH12AB1234): ");
        String vehicleNumber = sc.nextLine().toUpperCase();

        System.out.println("\nSelect Violation Type:");
        System.out.println("1. Speeding");
        System.out.println("2. Red Light Jumped");
        System.out.println("3. Illegal Parking");
        System.out.println("4. No Helmet");
        System.out.println("5. No Seatbelt");
        System.out.print("Choice: ");

        int violationType = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        Violation violation = null;

        switch (violationType) {
            case 1:
                System.out.print("Speed Limit (km/h): ");
                int speedLimit = sc.nextInt();
                System.out.print("Actual Speed (km/h): ");
                int actualSpeed = sc.nextInt();
                sc.nextLine();
                violation = new SpeedingViolation(LocalDate.now(), location, speedLimit, actualSpeed);
                break;
            case 2:
                violation = new RedLightViolation(LocalDate.now(), location);
                break;
            case 3:
                System.out.print("Parking Zone (No Parking/Reserved/Other): ");
                String zone = sc.nextLine();
                violation = new ParkingViolation(LocalDate.now(), location, zone);
                break;
            case 4:
                violation = new HelmetViolation(LocalDate.now(), location);
                break;
            case 5:
                violation = new SeatbeltViolation(LocalDate.now(), location);
                break;
            default:
                System.out.println("Invalid violation type.");
                return;
        }

        manager.recordViolation(vehicleNumber, violation);

        if (manager.isRepeatOffender(vehicleNumber)) {
            System.out.println("WARNING: " + vehicleNumber + " is now a REPEAT OFFENDER!");
            System.out.println("   Additional 50% penalty will be applied on total fine.");
        }
    }

    private static void viewViolationHistory(TrafficFineManager manager, Scanner sc)
            throws InvalidVehicleException {
        System.out.print("\nEnter Vehicle Number: ");
        String vehicleNumber = sc.nextLine().toUpperCase();
        manager.printViolationHistory(vehicleNumber);
    }

    private static void calculateFine(TrafficFineManager manager, Scanner sc)
            throws InvalidVehicleException {
        System.out.print("\nEnter Vehicle Number: ");
        String vehicleNumber = sc.nextLine().toUpperCase();

        double fine = manager.calculateTotalFine(vehicleNumber);
        System.out.println("\nTotal Fine for " + vehicleNumber + ": ₹" + fine);

        if (manager.isRepeatOffender(vehicleNumber)) {
            System.out.println("(Includes 50% repeat offender penalty)");
        }
    }

    private static void generateReport(TrafficFineManager manager, Scanner sc) {
        System.out.print("\nEnter Year (e.g., 2026): ");
        int year = sc.nextInt();
        System.out.print("Enter Month (1-12): ");
        int month = sc.nextInt();
        sc.nextLine();

        manager.generateMonthlyReport(year, month);
    }
}
