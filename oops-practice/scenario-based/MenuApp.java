import java.util.Scanner;

public class MenuApp {
    String[] menu = {
            "Veg Burger", "Chicken Pizza", "Pasta", "Grilled Sandwich", "Cold Coffee",
            "Masala Tea", "Caesar Salad", "French Fries", "Paneer Wrap", "Chocolate Cake"
    };
    int menuLength=10;

    // Method to display the menu items with their indices
    public  void displayMenu() {
        System.out.println("=== Cafeteria Menu ===");
        for (int i = 0; i < menuLength; i++) {
            // Displaying 1-based index for user friendliness
            System.out.println((i + 1) + ". " + menu[i]);
        }
    }

    // Method to get an item by its index (handling 1-based user input)
    public  String getItemByIndex( int index) {
        // Convert 1-based index to 0-based
        int arrayIndex = index - 1;
        if (arrayIndex >= 0 && arrayIndex < menu.length) {
            return menu[arrayIndex];
        }
        return null;
    }

    public static void main(String[] args) {
        MenuApp app = new MenuApp();
        app.displayMenu();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the number of the item you want to order: ");
        
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            String selectedItem = app.getItemByIndex(choice);
            
            if (selectedItem != null) {
                System.out.println("Order Placed: " + selectedItem);
            } else {
                System.out.println("Invalid selection. Please choose a valid number.");
            }
        } else {
            System.out.println("Invalid input.");
        }
        scanner.close();
    }
}