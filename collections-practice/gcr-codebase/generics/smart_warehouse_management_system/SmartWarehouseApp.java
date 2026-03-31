

import java.util.List;

public class SmartWarehouseApp {

    public static void main(String[] args) {
        // 1. Create specific storage units (Type Safety in action)
        Storage<Electronics> electronicsSection = new Storage<>();
        Storage<Groceries> grocerySection = new Storage<>();
        Storage<Furniture> furnitureSection = new Storage<>();

        // 2. Add items
        electronicsSection.addItem(new Electronics("Laptop", 1200.00, 24));
        electronicsSection.addItem(new Electronics("Smartphone", 800.00, 12));

        grocerySection.addItem(new Groceries("Milk", 2.50, "2023-12-01"));
        grocerySection.addItem(new Groceries("Bread", 1.50, "2023-11-25"));

        furnitureSection.addItem(new Furniture("Office Chair", 150.00, "Mesh/Plastic"));

        // 3. Display items using the wildcard method
        System.out.println("\n--- Warehouse Inventory Report ---");

        System.out.println("Electronics Section:");
        displayInventory(electronicsSection.getInventory());

        System.out.println("\nGrocery Section:");
        displayInventory(grocerySection.getInventory());

        System.out.println("\nFurniture Section:");
        displayInventory(furnitureSection.getInventory());
    }

    /**
     * Wildcard method to display items from any list containing WarehouseItem
     * subtypes.
     * Uses Upper Bounded Wildcard (? extends WarehouseItem).
     */
    public static void displayInventory(List<? extends WarehouseItem> items) {
        if (items.isEmpty()) {
            System.out.println("  [Empty Section]");
            return;
        }

        for (WarehouseItem item : items) {
            // We can safely read as WarehouseItem because of the upper bound
            System.out.println("  " + item.toString());
        }
    }
}
