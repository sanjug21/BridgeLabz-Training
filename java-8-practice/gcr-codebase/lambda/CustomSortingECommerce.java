import java.util.*;

class Product {
    private String name;
    private double price;
    private double rating;
    private int discount;

    public Product(String name, double price, double rating, int discount) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getDiscount() {
        return discount;
    }

    public double getDiscountedPrice() {
        return price - (price * discount / 100);
    }

    @Override
    public String toString() {
        return String.format("%s | Price: $%.2f | Rating: %.1f | Discount: %d%% | Final: $%.2f",
                name, price, rating, discount, getDiscountedPrice());
    }
}

public class CustomSortingECommerce {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1200.00, 4.5, 15));
        products.add(new Product("Smartphone", 800.00, 4.7, 20));
        products.add(new Product("Headphones", 150.00, 4.3, 25));
        products.add(new Product("Monitor", 350.00, 4.5, 12));
        products.add(new Product("Keyboard", 80.00, 4.2, 5));

        List<Product> byPrice = new ArrayList<>(products);
        byPrice.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        System.out.println("By Price:");
        byPrice.forEach(System.out::println);

        List<Product> byRating = new ArrayList<>(products);
        byRating.sort((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));
        System.out.println("\nBy Rating:");
        byRating.forEach(System.out::println);

        List<Product> byDiscount = new ArrayList<>(products);
        byDiscount.sort((p1, p2) -> Integer.compare(p2.getDiscount(), p1.getDiscount()));
        System.out.println("\nBy Discount:");
        byDiscount.forEach(System.out::println);
    }
}
