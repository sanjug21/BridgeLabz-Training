package hotel_management_system;

// OOP: Abstract base class for Rooms
public abstract class Room {
    private String roomNumber;
    private double basePrice;
    private boolean isAvailable;
    private String type;

    public Room(String roomNumber, double basePrice, String type) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
        this.type = type;
        this.isAvailable = true;
    }

    public String getRoomNumber() { return roomNumber; }
    public double getBasePrice() { return basePrice; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return type + " Room " + roomNumber;
    }
}