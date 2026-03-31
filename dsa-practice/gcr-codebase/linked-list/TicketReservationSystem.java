import java.util.Scanner;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;

    Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
    }
}



public class TicketReservationSystem {
    static Scanner sc = new Scanner(System.in);
    Node<Ticket> head = null;
    Node<Ticket> tail = null;

    public void addTicket(Ticket ticket) {
        Node<Ticket> nn = new Node<Ticket>(ticket);
        if (head == null) {
            head = nn;
            tail = nn;
            nn.next = head;
        } else {
            tail.next = nn;
            tail = nn;
            tail.next = head;
        }
        System.out.println("Ticket booked successfully. ID: " + ticket.ticketId);
    }

    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Node<Ticket> current = head;
        Node<Ticket> prev = tail;

        do {
            if (current.data.ticketId == ticketId) {
                if (current == head && current == tail) { // Only one node
                    head = null;
                    tail = null;
                } else {
                    // if current is at head 
                    if (current == head) {
                        head = head.next;
                        tail.next = head;
                    // if current is at tail
                    } else if (current == tail) {
                        tail = prev;
                        tail.next = head;
                    } else {
                        prev.next = current.next;
                    }
                }
                System.out.println("Ticket ID " + ticketId + " cancelled successfully.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket ID " + ticketId + " not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Node<Ticket> temp = head;
        System.out.printf("%-10s %-20s %-20s %-10s %-15s%n", "ID", "Customer", "Movie", "Seat", "Time");
        System.out.println("-------------------------------------------------------------------------------");
        do {
            System.out.printf("%-10d %-20s %-20s %-10s %-15s%n",
                    temp.data.ticketId,
                    temp.data.customerName,
                    temp.data.movieName,
                    temp.data.seatNumber,
                    temp.data.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicket(String query) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Node<Ticket> temp = head;
        boolean found = false;
        System.out.printf("%-10s %-20s %-20s %-10s %-15s%n", "ID", "Customer", "Movie", "Seat", "Time");
        System.out.println("-------------------------------------------------------------------------------");
        do {
            if (temp.data.customerName.equalsIgnoreCase(query) || temp.data.movieName.equalsIgnoreCase(query)) {
                System.out.printf("%-10d %-20s %-20s %-10s %-15s%n",
                        temp.data.ticketId,
                        temp.data.customerName,
                        temp.data.movieName,
                        temp.data.seatNumber,
                        temp.data.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tickets found matching: " + query);
        }
    }

    public void countTickets() {
        if (head == null) {
            System.out.println("Total booked tickets: 0");
            return;
        }
        int count = 0;
        Node<Ticket> temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        System.out.println("Total booked tickets: " + count);
    }

    public static void main(String[] args) {
        TicketReservationSystem trs = new TicketReservationSystem();
        System.out.println("==== Online Ticket Reservation System ====");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket");
            System.out.println("5. Count Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    trs.addTicketInput();
                    break;
                case 2:
                    System.out.println("Enter Ticket ID to cancel: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    trs.removeTicket(id);
                    break;
                case 3:
                    trs.displayTickets();
                    break;
                case 4:
                    System.out.println("Enter Customer Name or Movie Name to search: ");
                    String query = sc.nextLine();
                    trs.searchTicket(query);
                    break;
                case 5:
                    trs.countTickets();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void addTicketInput() {
        try {
            System.out.println("Enter Ticket ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Customer Name: ");
            String customer = sc.nextLine();
            System.out.println("Enter Movie Name: ");
            String movie = sc.nextLine();
            System.out.println("Enter Seat Number: ");
            String seat = sc.nextLine();
            System.out.println("Enter Booking Time: ");
            String time = sc.nextLine();

            Ticket ticket = new Ticket(id, customer, movie, seat, time);
            addTicket(ticket);
        } catch (Exception e) {
            System.out.println("Error booking ticket: " + e.getMessage());
            sc.nextLine();
        }
    }
}