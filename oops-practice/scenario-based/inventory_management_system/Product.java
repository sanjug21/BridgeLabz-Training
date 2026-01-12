package inventory_management_system;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int stockLevel;
    private int reorderThreshold;

    public Product(String productId, String name, double price, int stockLevel, int reorderThreshold) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockLevel = stockLevel;
        this.reorderThreshold = reorderThreshold;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void reduceStock(int quantity) throws OutOfStockException {
        if (this.stockLevel < quantity) {
            throw new OutOfStockException(
                    "Insufficient stock for product: " + name + " (Available: " + stockLevel + ")");
        }
        this.stockLevel -= quantity;
    }

    public void increaseStock(int quantity) {
        this.stockLevel += quantity;
    }

    @Override
    public String toString() {
        return "ID: " + productId + ", Name: " + name + ", Price: $" + price + ", Stock: " + stockLevel;
    }
}
