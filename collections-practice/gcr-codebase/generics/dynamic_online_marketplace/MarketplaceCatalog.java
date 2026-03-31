
import java.util.ArrayList;
import java.util.List;

public class MarketplaceCatalog {

    public static void main(String[] args) {
        // 1. Define Categories
        BookCategory books = new BookCategory();
        ClothingCategory clothing = new ClothingCategory();
        GadgetCategory gadgets = new GadgetCategory();

        // 2. Create Products (Type Inference works here)
        Product<BookCategory> p1 = new Product<>("Java Programming", 50.00, books);
        Product<ClothingCategory> p2 = new Product<>("Leather Jacket", 120.00, clothing);
        Product<GadgetCategory> p3 = new Product<>("Wireless Earbuds", 80.00, gadgets);

        // 3. Store in a mixed catalog using Wildcards
        List<Product<? extends Category>> catalog = new ArrayList<>();
        catalog.add(p1);
        catalog.add(p2);
        catalog.add(p3);

        System.out.println("--- Catalog Before Discount ---");
        printCatalog(catalog);

        // 4. Apply Discounts using Generic Method
        applyDiscount(p1, 10); // 10% off books
        applyDiscount(p2, 20); // 20% off clothing
        applyDiscount(p3, 5);  // 5% off gadgets

        System.out.println("\n--- Catalog After Discount ---");
        printCatalog(catalog);
    }

    /**
     * Generic method to apply a discount to any Product with a valid Category.
     * <T extends Category> defines the type parameter for the method scope.
     */
    public static <T extends Category> void applyDiscount(Product<T> product, double percentage) {
        double currentPrice = product.getPrice();
        double newPrice = currentPrice - (currentPrice * (percentage / 100));
        product.setPrice(newPrice);
    }

    public static void printCatalog(List<Product<? extends Category>> products) {
        for (Product<? extends Category> p : products) {
            System.out.println(p);
        }
    }
}
