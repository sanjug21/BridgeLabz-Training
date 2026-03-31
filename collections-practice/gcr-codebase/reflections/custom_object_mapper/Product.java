package custom_object_mapper;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private String category;
    
    public Product() {}
    
    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, price=%.2f, quantity=%d, category=%s]",
                           productId, name, price, quantity, category);
    }
}
