package parcel_tracker;
import java.util.Scanner;

public class ParcelTracker {
    private StageNode head;
    static Scanner sc = new Scanner(System.in);

    // Requirement 1: Forward tracking through stages (Add to end)
    public void addStage(String stageName) {
        StageNode newNode = new StageNode(stageName);
        if (head == null) {
            head = newNode;
        } else {
            StageNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Stage added: " + stageName);
    }

    // Requirement 2: Add custom intermediate checkpoints
    public void addCheckpoint(String stageName, String afterStage) {
        if (head == null) {
            System.out.println("Tracking list is empty. Cannot add checkpoint.");
            return;
        }

        StageNode temp = head;
        while (temp != null) {
            if (temp.stageName.equalsIgnoreCase(afterStage)) {
                StageNode newNode = new StageNode(stageName);
                newNode.next = temp.next;
                temp.next = newNode;
                System.out.println("Checkpoint '" + stageName + "' added after '" + afterStage + "'.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Stage '" + afterStage + "' not found. Checkpoint not added.");
    }

    // Display tracking info
    public void trackParcel() {
        if (head == null) {
            System.out.println("No tracking information available.");
            return;
        }

        System.out.println("\n--- Parcel Tracking Status ---");
        StageNode temp = head;
        while (temp != null) {
            System.out.print("[" + temp.stageName + "]");
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println("\n------------------------------");
    }

    // Requirement 3: Handle lost/missing parcels (null pointers)
    // Simulates a lost parcel by breaking the chain after a specific stage.
    public void simulateLostParcel(String lastKnownStage) {
        if (head == null) return;

        StageNode temp = head;
        while (temp != null) {
            if (temp.stageName.equalsIgnoreCase(lastKnownStage)) {
                temp.next = null; // Break the link to simulate loss
                System.out.println("Parcel reported LOST after stage: " + lastKnownStage);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Stage '" + lastKnownStage + "' not found.");
    }

    public static void main(String[] args) {
        ParcelTracker tracker = new ParcelTracker();
        System.out.println("==== Parcel Tracker (Delivery Chain Management) ====");

        // Initial Setup
        tracker.addStage("Packed");
        tracker.addStage("Shipped");
        tracker.addStage("In Transit");
        tracker.addStage("Delivered");

        while (true) {
            System.out.println("\n1. View Tracking History");
            System.out.println("2. Add Intermediate Checkpoint");
            System.out.println("3. Report Parcel Lost (Simulate Break)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) tracker.trackParcel();
            else if (choice == 2) {
                System.out.print("Enter new checkpoint name: ");
                String name = sc.nextLine();
                System.out.print("Add after which stage? ");
                String after = sc.nextLine();
                tracker.addCheckpoint(name, after);
            } else if (choice == 3) {
                System.out.print("Enter last known stage: ");
                String stage = sc.nextLine();
                tracker.simulateLostParcel(stage);
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}