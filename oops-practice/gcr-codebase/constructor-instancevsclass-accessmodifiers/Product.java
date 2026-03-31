
public class Product {
    String productName;
    double price;
    // made static to keep track across all instances of product class
    static int totalProducts;
    public Product(String productName,double price) {
        this.productName=productName;
        this.price=price;
        totalProducts++;
    }

    public void displayProductDetails() {
        System.out.println("Product Name: "+productName+", Price: "+price);
    }
    
    public static void displayTotalProducts() {
        System.out.println("Total Products Created: "+totalProducts);
    }
    public static void main(String[] args) {
        // created 3 product objects
        Product p1=new Product("Samsung Book 3",125000.0);
        Product p2=new Product("Iphone 17 pro",100000.0);
        Product p3=new Product("Google Buds 2 pro",45000.0);
        // Display product details
        p1.displayProductDetails();
        p2.displayProductDetails();
        p3.displayProductDetails();
        // Display total products created
        Product.displayTotalProducts();
    }
}