package hotel_management_system;

// OOP: Reservation class to link Guest, Room, and Billing
public class Reservation {
    private String reservationId;
    private Guest guest;
    private Room room;
    private double totalAmount;
    private boolean isCheckedIn;

    public Reservation(String reservationId, Guest guest, Room room, double totalAmount) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.totalAmount = totalAmount;
        this.isCheckedIn = false;
    }

    public String getReservationId() { return reservationId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isCheckedIn() { return isCheckedIn; }
    public void setCheckedIn(boolean checkedIn) { isCheckedIn = checkedIn; }
}