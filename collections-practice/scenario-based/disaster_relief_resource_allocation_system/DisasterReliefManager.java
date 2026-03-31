import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DisasterReliefManager {
    private Map<String, ReliefCenter> reliefCenters;
    private Queue<AreaRequest> areaRequests;

    public DisasterReliefManager() {
        this.reliefCenters = new HashMap<>();
        this.areaRequests = new LinkedList<>();
    }

    public void addReliefCenter(String centerId, String location) {
        reliefCenters.put(centerId, new ReliefCenter(centerId, location));
        System.out.println("Relief center added: " + centerId + " at " + location);
    }

    public void addResourceToCenter(String centerId, String item, int quantity) {
        if (!reliefCenters.containsKey(centerId)) {
            System.out.println("Relief center not found: " + centerId);
            return;
        }
        reliefCenters.get(centerId).addResource(item, quantity);
        System.out.println("Added " + quantity + " " + item + " to " + centerId);
    }

    public void addAreaRequest(AreaRequest request) {
        areaRequests.offer(request);
        System.out.println("Area request added: " + request.areaName);
    }

    public void allocateNextRequest(String centerId) throws InsufficientResourceException {
        if (!reliefCenters.containsKey(centerId)) {
            System.out.println("Relief center not found: " + centerId);
            return;
        }

        if (areaRequests.isEmpty()) {
            System.out.println("No pending area requests");
            return;
        }

        AreaRequest request = areaRequests.poll();
        ReliefCenter center = reliefCenters.get(centerId);

        System.out.println("\nProcessing request from: " + request.areaName);
        System.out.println("Using relief center: " + centerId);

        for (Map.Entry<String, Integer> entry : request.requestedResources.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            center.allocateResource(item, quantity);
            System.out.println("Allocated " + quantity + " " + item + " to " + request.areaName);
        }

        System.out.println("Request completed for " + request.areaName);
    }

    public void allocateAllRequests(String centerId) {
        if (!reliefCenters.containsKey(centerId)) {
            System.out.println("Relief center not found: " + centerId);
            return;
        }

        System.out.println("\n=======================================");
        System.out.println("    PROCESSING ALL AREA REQUESTS");
        System.out.println("=======================================");

        int processed = 0;
        int failed = 0;

        while (!areaRequests.isEmpty()) {
            try {
                allocateNextRequest(centerId);
                processed++;
            } catch (InsufficientResourceException e) {
                System.out.println("Allocation failed: " + e.getMessage());
                failed++;
            }
        }

        System.out.println("\n=======================================");
        System.out.println("Successfully Allocated: " + processed);
        System.out.println("Failed: " + failed);
        System.out.println("=======================================");
    }

    public void generateAllocationReport() {
        System.out.println("\n=======================================");
        System.out.println("      RESOURCE ALLOCATION REPORT");
        System.out.println("=======================================");

        if (reliefCenters.isEmpty()) {
            System.out.println("No relief centers found.");
            return;
        }

        for (ReliefCenter center : reliefCenters.values()) {
            System.out.println("\n" + center.getDetailedInfo());
        }

        System.out.println("Pending Requests: " + areaRequests.size());
        System.out.println("=======================================");
    }

    public void displayReliefCenters() {
        System.out.println("\n--- Relief Centers ---");
        if (reliefCenters.isEmpty()) {
            System.out.println("No relief centers found.");
            return;
        }
        for (ReliefCenter center : reliefCenters.values()) {
            System.out.println(center);
        }
    }

    public void displayReliefCenterDetails(String centerId) {
        if (!reliefCenters.containsKey(centerId)) {
            System.out.println("Relief center not found: " + centerId);
            return;
        }
        System.out.println("\n" + reliefCenters.get(centerId).getDetailedInfo());
    }

    public void displayAreaRequests() {
        System.out.println("\n--- Pending Area Requests (FIFO Order) ---");
        if (areaRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        int count = 1;
        for (AreaRequest request : areaRequests) {
            System.out.println(count + ". " + request);
            count++;
        }
    }

    public void displayStatistics() {
        System.out.println("\n--- System Statistics ---");
        System.out.println("Total Relief Centers: " + reliefCenters.size());
        System.out.println("Pending Area Requests: " + areaRequests.size());

        int totalResourceTypes = 0;
        int totalQuantity = 0;

        for (ReliefCenter center : reliefCenters.values()) {
            totalResourceTypes += center.resources.size();
            for (int quantity : center.resources.values()) {
                totalQuantity += quantity;
            }
        }

        System.out.println("Total Resource Types: " + totalResourceTypes);
        System.out.println("Total Resource Quantity: " + totalQuantity);
    }
}
