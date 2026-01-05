import java.util.ArrayList;
import java.util.List;


// Product interface for common methods
interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

// Abstract class for products to implement common methods in their subclasses
abstract class ProductDetails {
    private String productId;
    private String name;
    private double price;

    public ProductDetails(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
    // getter and setter methods for productId, name, and price
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public abstract double calculateDiscount();
}



class Electronics extends ProductDetails implements Taxable {
    public Electronics(String id, String name, double price) {
        super(id, name, price);
    }
    // abstract method to calculate discount for electronics
    @Override
    public double calculateDiscount() {
        return getPrice() * 0.10; // 10% discount
    }
    // Taxable interface methods
    @Override
    public double calculateTax() {
        return getPrice() * 0.15; // 15% tax
    }
    // Taxable interface methods
    @Override
    public String getTaxDetails() {
        return "Electronics Tax (15%)";
    }
}

class Clothing extends ProductDetails implements Taxable {
    public Clothing(String id, String name, double price) {
        super(id, name, price);
    }
    // abstract method to calculate discount for clothing
    @Override
    public double calculateDiscount() {
        return getPrice() * 0.20; // 20% discount
    }
    // Taxable interface methods
    @Override
    public double calculateTax() {
        return getPrice() * 0.05; // 5% tax
    }
    // Taxable interface methods
    @Override
    public String getTaxDetails() {
        return "Clothing Tax (5%)";
    }
}

class Groceries extends ProductDetails {
    public Groceries(String id, String name, double price) {
        super(id, name, price);
    }
    // abstract method to calculate discount for groceries
    @Override
    public double calculateDiscount() {
        return 0; // No discount
    }
}

public class ECommercePlatform {
    public static void main(String[] args) {
        List<ProductDetails> products = new ArrayList<>();
        products.add(new Electronics("E001", "Laptop", 1000));
        products.add(new Clothing("C001", "T-Shirt", 50));
        products.add(new Groceries("G001", "Apple", 2));

        calculateAndPrintFinalPrices(products);
    }

    public static void calculateAndPrintFinalPrices(List<ProductDetails> products) {
        System.out.println("=== E-Commerce Platform ===");
        for (ProductDetails p : products) {

            // calculates tax and discount based on the product type
            double tax = (p instanceof Taxable) ? ((Taxable) p).calculateTax() : 0;
            double discount = p.calculateDiscount();
            double finalPrice = p.getPrice() + tax - discount;

            System.out.println("Product: " + p.getName());
            System.out.println("Final Price: Rs" + finalPrice + " (Base: " + p.getPrice() + ", Tax: " + tax + ", Disc: " + discount + ")");
            System.out.println("\n");
        }
    }
}