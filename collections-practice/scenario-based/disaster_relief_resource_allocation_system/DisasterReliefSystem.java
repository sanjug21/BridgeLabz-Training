import java.util.Scanner;

public class DisasterReliefSystem {

    public static void main(String[] args) {
        DisasterReliefManager manager = new DisasterReliefManager();
        Scanner sc = new Scanner(System.in);

        initializeDemoData(manager);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("  DISASTER RELIEF RESOURCE ALLOCATION");
            System.out.println("========================================");
            System.out.println("1. Add Relief Center");
            System.out.println("2. Add Resource to Center");
            System.out.println("3. Add Area Request");
            System.out.println("4. Allocate Next Request");
            System.out.println("5. Allocate All Requests");
            System.out.println("6. Display Relief Centers");
            System.out.println("7. Display Area Requests");
            System.out.println("8. Generate Allocation Report");
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
                        addReliefCenter(manager, sc);
                        break;
                    case 2:
                        addResource(manager, sc);
                        break;
                    case 3:
                        addAreaRequest(manager, sc);
                        break;
                    case 4:
                        allocateNextRequest(manager, sc);
                        break;
                    case 5:
                        allocateAllRequests(manager, sc);
                        break;
                    case 6:
                        manager.displayReliefCenters();
                        break;
                    case 7:
                        manager.displayAreaRequests();
                        break;
                    case 8:
                        manager.generateAllocationReport();
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
            } catch (InsufficientResourceException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(DisasterReliefManager manager) {
        manager.addReliefCenter("RC001", "Central Relief Center");
        manager.addReliefCenter("RC002", "Eastern Relief Center");

        manager.addResourceToCenter("RC001", "Food Packets", 500);
        manager.addResourceToCenter("RC001", "Water Bottles", 1000);
        manager.addResourceToCenter("RC001", "Medical Kits", 200);
        manager.addResourceToCenter("RC001", "Blankets", 300);

        manager.addResourceToCenter("RC002", "Food Packets", 300);
        manager.addResourceToCenter("RC002", "Water Bottles", 600);
        manager.addResourceToCenter("RC002", "Medical Kits", 100);

        AreaRequest req1 = new AreaRequest("Village A", 500);
        req1.addResourceRequest("Food Packets", 100);
        req1.addResourceRequest("Water Bottles", 200);
        req1.addResourceRequest("Medical Kits", 30);
        manager.addAreaRequest(req1);

        AreaRequest req2 = new AreaRequest("Village B", 800);
        req2.addResourceRequest("Food Packets", 150);
        req2.addResourceRequest("Water Bottles", 300);
        req2.addResourceRequest("Blankets", 50);
        manager.addAreaRequest(req2);

        AreaRequest req3 = new AreaRequest("Town C", 1200);
        req3.addResourceRequest("Food Packets", 250);
        req3.addResourceRequest("Water Bottles", 500);
        req3.addResourceRequest("Medical Kits", 80);
        manager.addAreaRequest(req3);

        System.out.println("\nDemo data initialized successfully!");
    }

    private static void addReliefCenter(DisasterReliefManager manager, Scanner sc) {
        System.out.print("\nEnter Center ID: ");
        String centerId = sc.nextLine();

        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        manager.addReliefCenter(centerId, location);
    }

    private static void addResource(DisasterReliefManager manager, Scanner sc) {
        System.out.print("\nEnter Center ID: ");
        String centerId = sc.nextLine();

        System.out.print("Enter Resource Item: ");
        String item = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        manager.addResourceToCenter(centerId, item, quantity);
    }

    private static void addAreaRequest(DisasterReliefManager manager, Scanner sc) {
        System.out.print("\nEnter Area Name: ");
        String areaName = sc.nextLine();

        System.out.print("Enter Population: ");
        int population = sc.nextInt();
        sc.nextLine();

        AreaRequest request = new AreaRequest(areaName, population);

        System.out.print("Number of resource types needed: ");
        int numResources = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numResources; i++) {
            System.out.print("Resource item " + (i + 1) + ": ");
            String item = sc.nextLine();

            System.out.print("Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            request.addResourceRequest(item, quantity);
        }

        manager.addAreaRequest(request);
    }

    private static void allocateNextRequest(DisasterReliefManager manager, Scanner sc)
            throws InsufficientResourceException {
        System.out.print("\nEnter Relief Center ID: ");
        String centerId = sc.nextLine();
        manager.allocateNextRequest(centerId);
    }

    private static void allocateAllRequests(DisasterReliefManager manager, Scanner sc) {
        System.out.print("\nEnter Relief Center ID: ");
        String centerId = sc.nextLine();
        manager.allocateAllRequests(centerId);
    }
}
