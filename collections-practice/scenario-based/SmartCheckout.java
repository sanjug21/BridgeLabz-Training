import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Product {
    String name;
    double price;
    int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("%s (Price: $%.2f, Stock: %d)", name, price, stock);
    }
}

class Customer {
    String name;
    List<String> cart;

    public Customer(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
    }

    public void addItem(String itemName) {
        cart.add(itemName);
    }
}

public class SmartCheckout {
    private static Map<String, Product> inventory = new HashMap<>();
    private static Queue<Customer> checkoutQueue = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeInventory();

        while (true) {
            System.out.println("\n--- SmartCheckout System ---");
            System.out.println("1. Add Customer to Queue");
            System.out.println("2. Process Next Customer");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    processCheckout();
                    break;
                case 3:
                    viewInventory();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void initializeInventory() {
        inventory.put("Milk", new Product("Milk", 2.50, 10));
        inventory.put("Bread", new Product("Bread", 1.50, 15));
        inventory.put("Eggs", new Product("Eggs", 3.00, 20));
        inventory.put("Apple", new Product("Apple", 0.50, 50));
        inventory.put("Chocolate", new Product("Chocolate", 1.20, 5));
    }

    private static void addCustomer() {
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        Customer customer = new Customer(name);

        System.out.println("Available Items: " + inventory.keySet());
        System.out.println("Enter items to buy (type 'done' to finish):");
        
        while (true) {
            System.out.print("Item name: ");
            String item = sc.nextLine().trim();
            if (item.equalsIgnoreCase("done")) break;
            
            if (inventory.containsKey(item)) {
                customer.addItem(item);
                System.out.println(item + " added to cart.");
            } else {
                System.out.println("Item not found in inventory.");
            }
        }
        
        checkoutQueue.add(customer);
        System.out.println("Customer " + name + " added to queue.");
    }

    private static void processCheckout() {
        if (checkoutQueue.isEmpty()) {
            System.out.println("No customers in queue.");
            return;
        }

        Customer customer = checkoutQueue.poll();
        System.out.println("\nProcessing Bill for: " + customer.name);
        double totalBill = 0;

        for (String itemName : customer.cart) {
            Product product = inventory.get(itemName);
            if (product.stock > 0) {
                product.stock--;
                totalBill += product.price;
                System.out.printf(" - %s: $%.2f (Stock remaining: %d)%n", product.name, product.price, product.stock);
            } else {
                System.out.println(" - " + itemName + ": OUT OF STOCK");
            }
        }
        System.out.printf("Total Bill: $%.2f%n", totalBill);
    }

    private static void viewInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }
}