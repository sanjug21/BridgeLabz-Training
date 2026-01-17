package traffic_manager;

import java.util.Scanner;

public class TrafficManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Initialize Roundabout with capacity 5 and Queue with capacity 3
        Roundabout roundabout = new Roundabout(5);
        WaitingQueue waitingQueue = new WaitingQueue(3);
        int choice;

        do {
            System.out.println("\n--- Traffic Manager Menu ---");
            System.out.println("1. Vehicle Arrives (Add to Queue)");
            System.out.println("2. Move Vehicle to Roundabout");
            System.out.println("3. Vehicle Exits Roundabout");
            System.out.println("4. Display System State");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle ID: ");
                    String id = scanner.nextLine();
                    waitingQueue.add(id);
                    break;
                case 2:
                    if (!waitingQueue.isEmpty()) {
                        if (!roundabout.isFull()) {
                            Vehicle v = waitingQueue.remove();
                            roundabout.enter(v);
                        } else {
                            System.out.println("Roundabout is full. Vehicle must wait.");
                        }
                    } else {
                        System.out.println("No vehicles in the waiting queue.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Vehicle ID to exit: ");
                    String exitId = scanner.nextLine();
                    roundabout.exit(exitId);
                    break;
                case 4:
                    roundabout.display();
                    waitingQueue.display();
                    break;
                case 0:
                    System.out.println("Exiting Traffic Manager.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }
}