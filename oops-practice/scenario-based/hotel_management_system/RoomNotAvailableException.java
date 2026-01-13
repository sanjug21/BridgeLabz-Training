package hotel_management_system;

// Exception Handling: Custom exception for room availability
public class RoomNotAvailableException extends Exception {
    public RoomNotAvailableException(String message) {
        super(message);
    }
}