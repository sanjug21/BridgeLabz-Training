package e_commerce_management_system;


class Product{
    private String productId;
    private String name;
    private double price;
    private int stock;

    public Product(String productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
            System.out.println(quantity + " units of " + name + " removed from stock.");
        } else {
            System.out.println("Not enough stock for " + name + ". Available: " + this.stock);
        }
    }

    public void increaseStock(int quantity) {
        this.stock += quantity;
        System.out.println(quantity + " units of " + name + " added to stock.");
    }

}