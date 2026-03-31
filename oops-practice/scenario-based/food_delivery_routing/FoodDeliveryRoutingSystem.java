
import java.util.*;

public class FoodDeliveryRoutingSystem {

    static void addDummyAgents(DeliveryService service) {
        service.addAgent(new Agent("A001", "Rahul", 12.9716, 77.5946));
        service.addAgent(new Agent("A002", "Priya", 12.9352, 77.6245));
        service.addAgent(new Agent("A003", "Amit", 12.9800, 77.6000));
        service.addAgent(new Agent("A004", "Sneha", 12.9500, 77.5800));
        service.addAgent(new Agent("A005", "Ravi", 12.9900, 77.6100));
    }

    static void addDummyOrders(DeliveryService service) {
        service.addOrder(new Order("ORD001", "Sanju", "Pizza Hut", 12.9716, 77.5946));
        service.addOrder(new Order("ORD002", "Ashish", "KFC", 12.9352, 77.6245));
        service.addOrder(new Order("ORD003", "Rohit", "Dominos", 12.9800, 77.6000));
        service.addOrder(new Order("ORD004", "Neha", "Burger King", 12.9500, 77.5800));
        service.addOrder(new Order("ORD005", "Vikas", "Subway", 12.9900, 77.6100));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DeliveryService service = new DeliveryService();

        addDummyAgents(service);
        addDummyOrders(service);

        while (true) {
            System.out.println("\n===== Food Delivery Order Routing System =====");
            System.out.println("1. Assign Next Order");
            System.out.println("2. Cancel Order");
            System.out.println("3. View Active Deliveries");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        service.assignOrder();
                        break;

                    case 2:
                        System.out.print("Enter Order ID to cancel: ");
                        String orderId = sc.nextLine();
                        service.cancelOrder(orderId);
                        break;

                    case 3:
                        service.viewActiveDeliveries();
                        break;

                    case 4:
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoAgentAvailableException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
