package traffic_manager;

// Circular Linked List Implementation
public class Roundabout {
    private Vehicle head = null;
    private Vehicle tail = null;

    // Add vehicle to the circular flow
    public void enterRoundabout(Vehicle newVehicle) {
        if (head == null) {
            head = newVehicle;
            tail = newVehicle;
            newVehicle.setNext(head); // Point to itself
        } else {
            tail.setNext(newVehicle);
            tail = newVehicle;
            tail.setNext(head); // Maintain circularity
        }
        System.out.println(newVehicle.getLicensePlate() + " entered the roundabout.");
    }

    // Remove vehicle from the circular flow
    public void exitRoundabout(String licensePlate) {
        if (head == null) {
            System.out.println("Roundabout is empty.");
            return;
        }

        Vehicle current = head;
        Vehicle prev = tail;

        // Check if head is the node to remove
        if (current.getLicensePlate().equals(licensePlate)) {
            if (head == tail) { // Only one node
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                tail.setNext(head);
            }
            System.out.println(licensePlate + " exited the roundabout.");
            return;
        }

        // Traverse to find the node
        do {
            prev = current;
            current = current.getNext();
            if (current.getLicensePlate().equals(licensePlate)) {
                prev.setNext(current.getNext());
                if (current == tail) {
                    tail = prev;
                }
                System.out.println(licensePlate + " exited the roundabout.");
                return;
            }
        } while (current != head);

        System.out.println("Vehicle " + licensePlate + " not found in roundabout.");
    }

    public void displayFlow() {
        System.out.print("Roundabout Flow: ");
        if (head == null) { System.out.println("Empty"); return; }
        Vehicle temp = head;
        do {
            System.out.print(temp.getLicensePlate() + " -> ");
            temp = temp.getNext();
        } while (temp != head);
        System.out.println("(back to start)");
    }
}