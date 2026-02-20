
import java.util.*;

public class RailwayPlatformManager {
    private Map<Integer, Platform> platforms;
    private PriorityQueue<Train> incomingTrains;
    private Map<String, Integer> trainAllocation; // Track which platform is allocated to which train

    public RailwayPlatformManager() {
        this.platforms = new HashMap<>();
        this.incomingTrains = new PriorityQueue<>();
        this.trainAllocation = new HashMap<>();
    }

    // Initialize platforms
    public void addPlatform(int platformNumber, String platformType) {
        platforms.put(platformNumber, new Platform(platformNumber, platformType));
        System.out.println("Platform " + platformNumber + " (" + platformType + ") added");
    }

    // Add train to incoming queue
    public void addIncomingTrain(Train train) {
        incomingTrains.offer(train);
        System.out.println("Train " + train.trainNumber + " added to arrival queue");
    }

    // Allocate platform to next arriving train
    public void allocateNextTrain() throws PlatformUnavailableException {
        if (incomingTrains.isEmpty()) {
            System.out.println("No trains in queue.");
            return;
        }

        Train train = incomingTrains.poll();
        System.out.println("\nProcessing: " + train);

        // Try to allocate preferred platform first
        int allocatedPlatform = -1;

        if (train.platformPreference > 0 && platforms.containsKey(train.platformPreference)) {
            Platform preferred = platforms.get(train.platformPreference);
            if (!preferred.isOccupied) {
                allocatedPlatform = train.platformPreference;
            }
        }

        // If preferred not available, find nearest available platform
        if (allocatedPlatform == -1) {
            allocatedPlatform = findNearestAvailablePlatform(train.platformPreference);
        }

        if (allocatedPlatform == -1) {
            throw new PlatformUnavailableException(
                "No platforms available for train " + train.trainNumber
            );
        }

        // Allocate platform
        Platform platform = platforms.get(allocatedPlatform);
        platform.allocateTrain(train.trainNumber);
        trainAllocation.put(train.trainNumber, allocatedPlatform);

        System.out.println("Train " + train.trainNumber + " allocated to Platform " + allocatedPlatform);
    }

    // Find nearest available platform
    private int findNearestAvailablePlatform(int preferredPlatform) {
        List<Integer> platformNumbers = new ArrayList<>(platforms.keySet());
        Collections.sort(platformNumbers);

        int nearestPlatform = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int platformNum : platformNumbers) {
            Platform platform = platforms.get(platformNum);
            if (!platform.isOccupied) {
                int distance = Math.abs(platformNum - preferredPlatform);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPlatform = platformNum;
                }
            }
        }

        return nearestPlatform;
    }

    // Release platform when train departs
    public void releasePlatform(String trainNumber) throws PlatformUnavailableException {
        if (!trainAllocation.containsKey(trainNumber)) {
            throw new PlatformUnavailableException(
                "Train " + trainNumber + " is not currently allocated to any platform"
            );
        }

        int platformNumber = trainAllocation.get(trainNumber);
        Platform platform = platforms.get(platformNumber);
        platform.releasePlatform();
        trainAllocation.remove(trainNumber);

        System.out.println("Platform " + platformNumber + " released (Train " + trainNumber + " departed)");
    }

    // Process all trains in queue
    public void processAllTrains() {
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("    PROCESSING ALL INCOMING TRAINS");
        System.out.println("═══════════════════════════════════════════════");

        int processed = 0;
        int failed = 0;

        while (!incomingTrains.isEmpty()) {
            try {
                allocateNextTrain();
                processed++;
            } catch (PlatformUnavailableException e) {
                System.out.println(e.getMessage());
                failed++;
            }
        }

        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("Successfully Allocated: " + processed);
        System.out.println("Failed (No Platform): " + failed);
        System.out.println("═══════════════════════════════════════════════");
    }

    // Display platform status
    public void displayPlatformStatus() {
        System.out.println("\n--- Platform Status ---");
        List<Integer> platformNumbers = new ArrayList<>(platforms.keySet());
        Collections.sort(platformNumbers);

        for (int platformNum : platformNumbers) {
            System.out.println(platforms.get(platformNum));
        }
    }

    // Display incoming trains queue
    public void displayIncomingTrains() {
        System.out.println("\n--- Incoming Trains Queue (by arrival time) ---");
        if (incomingTrains.isEmpty()) {
            System.out.println("No trains in queue.");
            return;
        }

        // Create a copy to avoid modifying original queue
        PriorityQueue<Train> tempQueue = new PriorityQueue<>(incomingTrains);
        int count = 1;
        while (!tempQueue.isEmpty()) {
            System.out.println(count + ". " + tempQueue.poll());
            count++;
        }
    }

    // Check for platform conflicts
    public void checkPlatformConflicts() {
        System.out.println("\n--- Platform Conflict Check ---");
        boolean conflictFound = false;

        Map<Integer, List<String>> platformUsage = new HashMap<>();
        for (Map.Entry<String, Integer> entry : trainAllocation.entrySet()) {
            platformUsage.putIfAbsent(entry.getValue(), new ArrayList<>());
            platformUsage.get(entry.getValue()).add(entry.getKey());
        }

        for (Map.Entry<Integer, List<String>> entry : platformUsage.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("CONFLICT: Platform " + entry.getKey() + 
                                   " has multiple trains: " + entry.getValue());
                conflictFound = true;
            }
        }

        if (!conflictFound) {
            System.out.println("No conflicts detected. All platforms properly allocated.");
        }
    }

    // Get statistics
    public void displayStatistics() {
        System.out.println("\n--- Station Statistics ---");
        System.out.println("Total Platforms: " + platforms.size());
        
        int occupied = 0;
        for (Platform platform : platforms.values()) {
            if (platform.isOccupied) occupied++;
        }
        
        System.out.println("Occupied Platforms: " + occupied);
        System.out.println("Available Platforms: " + (platforms.size() - occupied));
        System.out.println("Trains in Queue: " + incomingTrains.size());
        System.out.println("Total Allocations: " + trainAllocation.size());
    }
}
