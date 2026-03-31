import java.time.LocalDateTime;
import java.util.*;

public class RestaurantReservationSystem {

    private Map<Integer, Table> tables;
    private List<Reservation> reservations;
    private int reservationCounter;

    public RestaurantReservationSystem() {
        this.tables = new HashMap<>();
        this.reservations = new ArrayList<>();
        this.reservationCounter = 1000;
    }

    // Initialize restaurant with tables
    public void addTable(int tableNumber, int capacity) {
        if (!tables.containsKey(tableNumber)) {
            tables.put(tableNumber, new Table(tableNumber, capacity));
            System.out.println("Table " + tableNumber + " added with capacity " + capacity);
        } else {
            System.out.println("Table " + tableNumber + " already exists");
        }
    }

    // Reserve a table
    public void reserveTable(int tableNumber, String customerName, 
                             LocalDateTime reservationTime, int numberOfGuests) 
                             throws TableAlreadyReservedException {
        
        // Check if table exists
        if (!tables.containsKey(tableNumber)) {
            System.out.println("Table " + tableNumber + " does not exist");
            return;
        }

        Table table = tables.get(tableNumber);

        // Check if table is already reserved
        if (table.isReserved()) {
            throw new TableAlreadyReservedException("Table " + tableNumber + " is already reserved");
        }

        // Check if number of guests exceeds table capacity
        if (numberOfGuests > table.getCapacity()) {
            System.out.println("Number of guests exceeds table capacity");
            return;
        }

        // Reserve the table
        table.setReserved(true);
        
        // Create and add reservation
        Reservation reservation = new Reservation(
                ++reservationCounter, 
                tableNumber, 
                customerName, 
                reservationTime, 
                numberOfGuests
        );
        reservations.add(reservation);
        
        System.out.println("Table " + tableNumber + " reserved for " + customerName + 
                           " at " + reservationTime);
    }

    // Cancel a reservation
    public void cancelReservation(int reservationId) {
        Reservation toRemove = null;
        
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) {
                toRemove = res;
                break;
            }
        }

        if (toRemove != null) {
            reservations.remove(toRemove);
            
            // Mark table as available
            Table table = tables.get(toRemove.getTableNumber());
            table.setReserved(false);
            
            System.out.println("Reservation " + reservationId + " cancelled");
        } else {
            System.out.println("Reservation " + reservationId + " not found");
        }
    }

    // Show available tables
    public void showAvailableTables() {
        System.out.println("\n=== Available Tables ===");
        boolean anyAvailable = false;
        
        for (Table table : tables.values()) {
            if (!table.isReserved()) {
                System.out.println(table);
                anyAvailable = true;
            }
        }

        if (!anyAvailable) {
            System.out.println("No tables available");
        }
    }

    // Show all reservations
    public void showAllReservations() {
        System.out.println("\n=== All Reservations ===");
        if (reservations.isEmpty()) {
            System.out.println("No reservations");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }

    // Get table by number
    public Table getTable(int tableNumber) {
        return tables.get(tableNumber);
    }

    // Get all reservations
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    // Main method with user input
    public static void main(String[] args) {
        RestaurantReservationSystem system = new RestaurantReservationSystem();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Restaurant Table Reservation System ===\n");
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Table");
            System.out.println("2. Reserve Table");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Show Available Tables");
            System.out.println("5. Show All Reservations");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    // Add Table
                    System.out.print("Enter table number: ");
                    int tableNumber = scanner.nextInt();
                    System.out.print("Enter table capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();
                    system.addTable(tableNumber, capacity);
                    break;
                    
                case 2:
                    // Reserve Table
                    System.out.print("Enter table number to reserve: ");
                    int tableNum = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter number of guests: ");
                    int guests = scanner.nextInt();
                    scanner.nextLine();
                    
                    try {
                        system.reserveTable(tableNum, customerName, LocalDateTime.now(), guests);
                    } catch (TableAlreadyReservedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    // Cancel Reservation
                    System.out.print("Enter reservation ID to cancel: ");
                    int reservationId = scanner.nextInt();
                    scanner.nextLine();
                    system.cancelReservation(reservationId);
                    break;
                    
                case 4:
                    // Show Available Tables
                    system.showAvailableTables();
                    break;
                    
                case 5:
                    // Show All Reservations
                    system.showAllReservations();
                    break;
                    
                case 6:
                    // Exit
                    System.out.println("Thank you for using the Restaurant Reservation System!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
}
