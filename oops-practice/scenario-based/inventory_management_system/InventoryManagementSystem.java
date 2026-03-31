package inventory_management_system;


public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory(new ConsoleAlertService());

        inventory.addProduct(new Product("P001", "Laptop", 1200.00, 10, 3));
        inventory.addProduct(new Product("P002", "Smartphone", 800.00, 5, 2));

        inventory.displayInventory();

        inventory.updateStock("P001", -2); // Reduce stock
        inventory.updateStock("P002", -4); // Reduce stock, triggers alert (5-4=1 <= 2)
        inventory.updateStock("P002", -5); // Try to reduce more than available -> Exception
        inventory.updateStock("P001", 5);  // Add stock
    }
}
