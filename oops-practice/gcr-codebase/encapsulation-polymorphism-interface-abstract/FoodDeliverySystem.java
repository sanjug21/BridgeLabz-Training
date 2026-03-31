import java.util.ArrayList;
import java.util.List;

// Interface for discount capabilities
interface Discountable {
    double applyDiscount(double totalAmount);
    String getDiscountDetails();
}

// Abstract class for Food Items
abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Encapsulation: Getters only to restrict modification
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // declared the abstract method
    public abstract double calculateTotalPrice();

    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price per unit: Rs " + price);
        System.out.println("Quantity: " + quantity);
    }
}

class VegItem extends FoodItem implements Discountable {
    public VegItem(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    // Implementation of abstract method
    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }
    // Implementation of interface method
    @Override
    public double applyDiscount(double totalAmount) {
        // 5% discount for veg items
        return totalAmount * 0.95;
    }
    // Implementation of interface method
    @Override
    public String getDiscountDetails() {
        return "5% Discount on Veg Items";
    }
}

class NonVegItem extends FoodItem implements Discountable {
    private static final double PROCESSING_CHARGE = 50.0;

    public NonVegItem(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    // Implementation of abstract method
    @Override
    public double calculateTotalPrice() {
        // Additional processing charge for non-veg
        return (getPrice() * getQuantity()) + PROCESSING_CHARGE;
    }
    // Implementation of interface method
    @Override
    public double applyDiscount(double totalAmount) {
        // No discount for non-veg in this policy
        return totalAmount;
    }
    // Implementation of interface method
    @Override
    public String getDiscountDetails() {
        return "No Discount on Non-Veg Items";
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        List<FoodItem> order = new ArrayList<>();
        order.add(new VegItem("Paneer Tikka", 200, 2));
        order.add(new NonVegItem("Chicken Biryani", 300, 1));

        System.out.println("=== Online Food Delivery System ===");
        double grandTotal = 0;

        for (FoodItem item : order) {
            item.getItemDetails();
            double total = item.calculateTotalPrice();
            System.out.println("Base Total: Rs " + total);

            if (item instanceof Discountable) {
                Discountable d = (Discountable) item;
                System.out.println(d.getDiscountDetails());
                total = d.applyDiscount(total);
            }
            System.out.println("Final Price: Rs " + total);
            grandTotal += total;
            System.out.println("-----------------------------");
        }
        System.out.println("Grand Total Order Value: Rs " + grandTotal);
    }
}