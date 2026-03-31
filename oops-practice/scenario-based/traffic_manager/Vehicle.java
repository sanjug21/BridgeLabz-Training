package traffic_manager;

// Node class for Circular Linked List
public class Vehicle {
    private String licensePlate;
    private Vehicle next; 

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
        this.next = null;
    }

    public String getLicensePlate() { return licensePlate; }
    public Vehicle getNext() { return next; }
    public void setNext(Vehicle next) { this.next = next; }

    @Override
    public String toString() { return "[Car: " + licensePlate + "]"; }
}