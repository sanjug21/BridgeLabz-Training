package inventory_management_system;
import java.util.ArrayList;
import java.util.List;


public class Inventory {
    private List<Product> products = new ArrayList<>();
    private AlertService alertService;

    public Inventory(AlertService alertService) {
        this.alertService = alertService;
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Added product: " + product.getName());
    }

    public void updateStock(String productId, int quantityChange) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                if (quantityChange > 0) {
                    product.increaseStock(quantityChange);
                    System.out.println("Restocked " + product.getName() + " by " + quantityChange);
                } else {
                    try {
                        product.reduceStock(-quantityChange);
                        System.out.println("Dispatched " + (-quantityChange) + " unit(s) of " + product.getName());

                        // Check for low stock alert
                        if (product.getStockLevel() <= product.getReorderThreshold()) {
                            alertService.sendAlert(
                                    "Low stock for " + product.getName() + "! Current: " + product.getStockLevel());
                        }
                    } catch (OutOfStockException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                return;
            }
        }
        System.out.println("Product not found: " + productId);
    }

    public void displayInventory() {
        System.out.println("\n--- Current Inventory ---");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("-------------------------\n");
    }
}
