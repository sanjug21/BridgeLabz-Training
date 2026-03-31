import java.util.*;

class Flight {
    String flightId;
    String airline;
    String source;
    String destination;
    double price;

    public Flight(String flightId, String airline, String source, String destination, double price) {
        this.flightId = flightId;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    @Override
    public String toString() {
        return flightId + " | " + airline + " | " + source + " -> " + destination + " | $" + price;
    }
}

class Booking {
    String bookingId;
    Flight flight;
    String passengerName;

    public Booking(String bookingId, Flight flight, String passengerName) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passengerName = passengerName;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + " | Passenger: " + passengerName + "\n   Flight: " + flight.toString();
    }
}

public class FlightBookingSystem {
    // Crated dummy flights data
    static Flight[] flights = {
            new Flight("AI101", "Air India", "Mumbai", "Delhi", 5000),
            new Flight("IG202", "IndiGo", "Bangalore", "Pune", 3000),
            new Flight("SP303", "SpiceJet", "Delhi", "Goa", 4500),
            new Flight("VS404", "Vistara", "Mumbai", "London", 45000)
    };

    // Requirement: Store user bookings in a List
    static List<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Flight Booking System ===");
            System.out.println("1. Search Flights (Source/Destination)");
            System.out.println("2. Book a Flight");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Search Query (City): ");
                    String query = sc.nextLine();
                    System.out.println("--- Search Results ---");
                    boolean found = false;
                    for (Flight f : flights) {
                        // Requirement: Case-insensitive search
                        if (f.source.equalsIgnoreCase(query) || f.destination.equalsIgnoreCase(query)) {
                            System.out.println(f);
                            found = true;
                        }
                    }
                    if (!found)
                        System.out.println("No flights found.");
                    break;
                case 2:
                    System.out.print("Enter Flight ID to book: ");
                    String fid = sc.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String pname = sc.nextLine();
                    boolean flightFound = false;
                    for (Flight f : flights) {
                        if (f.flightId.equalsIgnoreCase(fid)) {
                            int id=(int)(Math.random()*10000);
                            bookings.add(new Booking(id+"", f, pname));
                            System.out.println("Booking Successful!");
                            flightFound = true;
                            break;
                        }
                    }
                    if (!flightFound) {
                        System.out.println("Flight with ID " + fid + " not found.");
                    }
                    break;
                case 3:
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings yet.");
                    } else {
                        System.out.println("--- My Bookings ---");
                        for (Booking b : bookings)
                            System.out.println(b);
                    }
                    break;
                case 4:
                    System.out.println("Exiting Flight Booking System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        }
    }
}