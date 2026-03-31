package traffic_manager;

public class Roundabout {
    private Vehicle head = null;
    private Vehicle tail = null;
    private int count = 0;
    private final int capacity;

    public Roundabout(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return count >= capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // Add a vehicle to the circular path
    public void enter(Vehicle vehicle) {
        if (isFull()) {
            System.out.println("Roundabout is full! " + vehicle + " cannot enter.");
            return;
        }

        if (head == null) {
            head = vehicle;
            tail = vehicle;
            vehicle.next = head; // Point to itself to form circle
        } else {
            tail.next = vehicle;
            tail = vehicle;
            tail.next = head; // Maintain circle
        }
        count++;
        System.out.println(vehicle + " entered the roundabout.");
    }

    // Remove a vehicle from the circular path
    public void exit(String vehicleId) {
        if (isEmpty()) {
            System.out.println("Roundabout is empty.");
            return;
        }

        Vehicle current = head;
        Vehicle prev = tail;
        boolean found = false;

        // Traverse the circular list
        do {
            if (current.id.equals(vehicleId)) {
                found = true;
                // Case 1: Only one node
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } 
                // Case 2: Removing head
                else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } 
                // Case 3: Removing tail
                else if (current == tail) {
                    tail = prev;
                    tail.next = head;
                } 
                // Case 4: Removing from middle
                else {
                    prev.next = current.next;
                }
                count--;
                System.out.println("Vehicle-" + vehicleId + " exited the roundabout.");
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("Vehicle-" + vehicleId + " not found in the roundabout.");
        }
    }

    public void display() {
        System.out.print("Roundabout State: ");
        if (isEmpty()) {
            System.out.println("[Empty]");
            return;
        }
        Vehicle current = head;
        do {
            System.out.print("[" + current.id + "] -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(back to start)");
    }
}