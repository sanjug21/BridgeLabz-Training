package hotel_management_system;

// Inheritance: Concrete subclass for Deluxe Room
public class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber, 3500.0, "Deluxe");
    }
}