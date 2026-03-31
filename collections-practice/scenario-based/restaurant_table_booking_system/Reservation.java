import java.time.LocalDateTime;

public class Reservation {

    private int reservationId;
    private int tableNumber;
    private String customerName;
    private LocalDateTime reservationTime;
    private int numberOfGuests;

    public Reservation(int reservationId, int tableNumber, String customerName, 
                       LocalDateTime reservationTime, int numberOfGuests) {
        this.reservationId = reservationId;
        this.tableNumber = tableNumber;
        this.customerName = customerName;
        this.reservationTime = reservationTime;
        this.numberOfGuests = numberOfGuests;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", tableNumber=" + tableNumber +
                ", customerName='" + customerName + '\'' +
                ", reservationTime=" + reservationTime +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
