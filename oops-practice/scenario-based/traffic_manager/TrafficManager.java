package traffic_manager;

// Main Controller
public class TrafficManager {
    public static void main(String[] args) {
        System.out.println("=== Smart City Roundabout System ===");
        
        // 1. Initialize System
        WaitingQueue waitingQueue = new WaitingQueue(3); // Capacity 3
        Roundabout roundabout = new Roundabout();

        // 2. Vehicles arrive (Queue Operations)
        waitingQueue.enqueue(new Vehicle("KA-01-1234"));
        waitingQueue.enqueue(new Vehicle("MH-12-5678"));
        waitingQueue.enqueue(new Vehicle("DL-05-9999"));
        waitingQueue.enqueue(new Vehicle("TN-09-0000")); // Overflow Test

        System.out.println("\n--- Initial State ---");
        waitingQueue.displayQueue();
        roundabout.displayFlow();

        // 3. Move vehicles to Roundabout
        System.out.println("\n--- Moving Traffic ---");
        Vehicle v1 = waitingQueue.dequeue();
        if (v1 != null) roundabout.enterRoundabout(v1);

        Vehicle v2 = waitingQueue.dequeue();
        if (v2 != null) roundabout.enterRoundabout(v2);

        System.out.println("\n--- Current State ---");
        waitingQueue.displayQueue();
        roundabout.displayFlow();

        // 4. Vehicle Exits
        System.out.println("\n--- Vehicle Exiting ---");
        roundabout.exitRoundabout("KA-01-1234");
        
        // 5. Final State
        System.out.println("\n--- Final State ---");
        waitingQueue.displayQueue();
        roundabout.displayFlow();
    }
}