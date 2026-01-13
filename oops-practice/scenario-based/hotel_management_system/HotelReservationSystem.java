package hotel_management_system;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Main System Controller
public class HotelReservationSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private PricingStrategy pricingStrategy;

    public HotelReservationSystem(PricingStrategy pricingStrategy) {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.pricingStrategy = pricingStrategy;
    }

    // CRUD: Room Management
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // CRUD: Reservation Booking
    public Reservation bookRoom(Guest guest, String roomType, int nights) {
        try {
            Room availableRoom = rooms.stream()
                .filter(r -> r.getType().equalsIgnoreCase(roomType) && r.isAvailable())
                .findFirst()
                .orElseThrow(() -> new RoomNotAvailableException("No " + roomType + " room available."));

            // Mark room as unavailable
            availableRoom.setAvailable(false);
            
            // Calculate price using strategy
            double amount = pricingStrategy.calculatePrice(availableRoom.getBasePrice(), nights);
            String id = UUID.randomUUID().toString().substring(0, 8);
            
            Reservation reservation = new Reservation(id, guest, availableRoom, amount);
            reservations.add(reservation);
            
            System.out.println("Reservation confirmed for " + guest.getName() + ". ID: " + id);
            return reservation;
        } catch (RoomNotAvailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
            return null;
        }
    }

    // CRUD: Check-in
    public void checkIn(String reservationId) {
        Reservation res = findReservation(reservationId);
        if (res != null && !res.isCheckedIn()) {
            res.setCheckedIn(true);
            System.out.println("Check-in successful for " + res.getGuest().getName());
        } else {
            System.out.println("Check-in failed: Invalid ID or already checked in.");
        }
    }

    // CRUD: Check-out & Invoice Generation
    public void checkOut(String reservationId) {
        Reservation res = findReservation(reservationId);
        if (res != null) {
            res.getRoom().setAvailable(true);
            System.out.println("\n=== INVOICE ===");
            System.out.println("Guest: " + res.getGuest().getName());
            System.out.println("Room: " + res.getRoom());
            System.out.println("Total Amount: Rs " + res.getTotalAmount());
            System.out.println("===============\n");
            reservations.remove(res);
        } else {
            System.out.println("Check-out failed: Reservation not found.");
        }
    }

    private Reservation findReservation(String id) {
        return reservations.stream().filter(r -> r.getReservationId().equals(id)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        // Initialize system with Peak Season Pricing
        HotelReservationSystem hotel = new HotelReservationSystem(new SeasonalPricingStrategy(true));

        hotel.addRoom(new StandardRoom("101"));
        hotel.addRoom(new StandardRoom("102"));
        hotel.addRoom(new DeluxeRoom("201"));

        Guest g1 = new Guest("Ravi", "9876543210");
        Guest g2 = new Guest("Amit", "9123456789");

        System.out.println("=== Hotel Reservation System Demo ===");
        
        Reservation r1 = hotel.bookRoom(g1, "Standard", 3);
        Reservation r2 = hotel.bookRoom(g2, "Deluxe", 2);
        
        if (r1 != null) hotel.checkOut(r1.getReservationId());
        if (r2 != null) hotel.checkOut(r2.getReservationId());
    }
}