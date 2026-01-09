import java.util.*;
// declared Item class with its properties
class Item {
    String itemName;
    String itemId;
    int quantity;
    double price;

    Item(String itemName, String itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }
}


public class InventoryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    Node<Item> head;

    public void addItemAtBeginning(Item item) {
        Node<Item> nn = new Node<>(item);
        nn.next = head;
        head = nn;
        System.out.println("Item added at the beginning successfully.");
    }

    public void addItemAtEnd(Item item) {
        Node<Item> nn = new Node<>(item);
        if (head == null) {
            head = nn;
            return;
        }
        Node<Item> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = nn;
        System.out.println("Item added at the end successfully.");
    }

    public void addItemAtPosition(Item item, int position) {
        Node<Item> nn = new Node<>(item);
        if (position == 1) {
            addItemAtBeginning(item);
            return;
        }
        Node<Item> temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of bounds.");
        } else {
            nn.next = temp.next;
            temp.next = nn;
            System.out.println("Item added at position " + position + " successfully.");
        }
    }

    public void deleteItem(String itemId) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        if (head.data.itemId.equals(itemId)) {
            head = head.next;
            System.out.println("Item with ID " + itemId + " deleted successfully.");
            return;
        }
        Node<Item> temp = head;
        while (temp.next != null) {
            if (temp.next.data.itemId.equals(itemId)) {
                temp.next = temp.next.next;
                System.out.println("Item with ID " + itemId + " deleted successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void updateItemQuantity(String itemId, int quantity) {
        Node<Item> temp = head;
        while (temp != null) {
            if (temp.data.itemId.equals(itemId)) {
                temp.data.quantity = quantity;
                System.out.println("Quantity updated for Item ID " + itemId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void searchItem(String query) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Node<Item> temp = head;
        boolean found = false;
        System.out.printf("%-20s %-10s %-10s %-10s%n", "Name", "ID", "Quantity", "Price");
        System.out.println("-------------------------------------------------------");
        while (temp != null) {
            if (temp.data.itemId.equalsIgnoreCase(query) || temp.data.itemName.equalsIgnoreCase(query)) {
                System.out.printf("%-20s %-10s %-10d %-10.2f%n",
                        temp.data.itemName, temp.data.itemId, temp.data.quantity, temp.data.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No item found matching: " + query);
        }
    }
    // method to calculate total inventory value
    public void calculateTotalValue() {
        double total = 0;
        Node<Item> temp = head;
        while (temp != null) {
            total += (temp.data.quantity * temp.data.price);
            temp = temp.next;
        }
        System.out.printf("Total Inventory Value: %.2f%n", total);
    }
    
    // method to display inventory
    public void displayInventory() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.printf("%-20s %-10s %-10s %-10s%n", "Name", "ID", "Quantity", "Price");
        System.out.println("-------------------------------------------------------");
        Node<Item> temp = head;
        while (temp != null) {
            System.out.printf("%-20s %-10s %-10d %-10.2f%n",
                    temp.data.itemName, temp.data.itemId, temp.data.quantity, temp.data.price);
            temp = temp.next;
        }
    }
    // method to sort inventory by name or price
    public void sortInventory(int criteria, boolean ascending) {
        head = mergeSort(head, criteria, ascending);
        System.out.println("Inventory sorted successfully.");
        displayInventory();
    }
    // Helper method for merge sort
    private Node<Item> mergeSort(Node<Item> h, int criteria, boolean ascending) {
        if (h == null || h.next == null) {
            return h;
        }
        Node<Item> middle = getMiddle(h);
        Node<Item> nextOfMiddle = middle.next;
        middle.next = null;

        Node<Item> left = mergeSort(h, criteria, ascending);
        Node<Item> right = mergeSort(nextOfMiddle, criteria, ascending);

        return sortedMerge(left, right, criteria, ascending);
    }

    private Node<Item> sortedMerge(Node<Item> a, Node<Item> b, int criteria, boolean ascending) {
        // Create a dummy node to act as the starting point
        Node<Item> dummy = new Node<>(null);
        Node<Item> curr = dummy;

        while (a != null && b != null) {
            int compare;
            if (criteria == 1) { // Name
                compare = a.data.itemName.compareToIgnoreCase(b.data.itemName);
            } else { // Price
                compare = Double.compare(a.data.price, b.data.price);
            }

            // Determine if 'a' should come before 'b'
            boolean condition = ascending ? (compare <= 0) : (compare > 0);

            if (condition) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }

        // Link the remaining nodes (if any) from either list
        curr.next = (a != null) ? a : b;

        return dummy.next; // Return the actual head (ignoring dummy)
    }
    // Helper method to find the middle of the linked list for merge sort
    private Node<Item> getMiddle(Node<Item> h) {
        if (h == null) return h;
        Node<Item> slow = h, fast = h;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        System.out.println("==== Inventory Management System ====");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Quantity");
            System.out.println("4. Search Item");
            System.out.println("5. Calculate Total Value");
            System.out.println("6. Sort Inventory");
            System.out.println("7. Display Inventory");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: ims.addItemInput(); break;
                case 2:
                    System.out.println("Enter Item ID to remove: ");
                    String id = sc.nextLine();
                    ims.deleteItem(id);
                    break;
                case 3:
                    System.out.println("Enter Item ID: ");
                    String uid = sc.nextLine();
                    System.out.println("Enter new Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    ims.updateItemQuantity(uid, qty);
                    break;
                case 4:
                    System.out.println("Enter Name or ID to search: ");
                    String q = sc.nextLine();
                    ims.searchItem(q);
                    break;
                case 5: ims.calculateTotalValue(); break;
                case 6:
                    System.out.println("Sort by: 1. Name, 2. Price");
                    int crit = sc.nextInt();
                    System.out.println("Order: 1. Ascending, 2. Descending");
                    int ord = sc.nextInt();
                    sc.nextLine();
                    ims.sortInventory(crit, ord == 1);
                    break;
                case 7: ims.displayInventory(); break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }

    public void addItemInput() {
        try {
            System.out.println("Enter Item Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Item ID: ");
            String id = sc.nextLine();
            System.out.println("Enter Quantity: ");
            int qty = sc.nextInt();
            System.out.println("Enter Price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            Item item = new Item(name, id, qty, price);
            System.out.println("Where to add? (1. Beginning, 2. End, 3. Position): ");
            int posChoice = sc.nextInt();
            sc.nextLine();

            if (posChoice == 1) addItemAtBeginning(item);
            else if (posChoice == 2) addItemAtEnd(item);
            else if (posChoice == 3) {
                System.out.println("Enter position: ");
                int pos = sc.nextInt();
                sc.nextLine();
                addItemAtPosition(item, pos);
            } else addItemAtEnd(item);
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
            sc.nextLine();
        }
    }
}
