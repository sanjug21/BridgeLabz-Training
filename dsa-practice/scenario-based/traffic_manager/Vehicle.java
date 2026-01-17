package traffic_manager;

// Represents a vehicle in the system (Node)
public class Vehicle {
    String id;
    Vehicle next; // Pointer for the Circular Linked List

    public Vehicle(String id) {
        this.id = id;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Vehicle-" + id;
    }
}