package event_manager;

// Entity class representing an Event Ticket
public class Ticket {
    private String ticketId;
    private String eventName;
    private double price;

    public Ticket(String ticketId, String eventName, double price) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.price = price;
    }

    public double getPrice() { return price; }
    
    @Override
    public String toString() {
        return String.format("Ticket[%s]: %s - $%.2f", ticketId, eventName, price);
    }
}