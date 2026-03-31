package event_manager;

import java.util.ArrayList;
import java.util.List;

// Main Controller
public class EventPortal {
    public static void main(String[] args) {
        System.out.println("=== Event Ticket Price Optimizer (Quick Sort) ===");
        
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("T001", "Rock Concert", 150.00));
        tickets.add(new Ticket("T002", "Tech Conference", 500.00));
        tickets.add(new Ticket("T003", "Local Standup", 25.50));
        tickets.add(new Ticket("T004", "Movie Premiere", 15.00));
        tickets.add(new Ticket("T005", "Gala Dinner", 300.00));
        tickets.add(new Ticket("T006", "Museum Entry", 10.00));
        tickets.add(new Ticket("T007", "VIP Backstage", 1000.00));
        tickets.add(new Ticket("T008", "Early Bird Pass", 45.00));

        System.out.println("\n--- Unsorted Tickets ---");
        printTickets(tickets);

        TicketSorter sorter = new TicketSorter();
        sorter.quickSort(tickets, 0, tickets.size() - 1);

        System.out.println("\n--- Sorted Tickets (Price: Low to High) ---");
        printTickets(tickets);
        
        System.out.println("\n--- Top 3 Cheapest Tickets ---");
        for(int i=0; i<Math.min(3, tickets.size()); i++) {
            System.out.println(tickets.get(i));
        }
    }

    private static void printTickets(List<Ticket> tickets) {
        for (Ticket t : tickets) {
            System.out.println(t);
        }
    }
}