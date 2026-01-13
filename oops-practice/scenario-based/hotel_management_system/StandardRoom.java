package hotel_management_system;

// Inheritance: Concrete subclass for Standard Room
public class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber, 1500.0, "Standard");
    }
}