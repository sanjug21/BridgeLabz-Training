package e_commerce_management_system;

import java.util.*;

public class ECommerceManagementSystem {
    static List<Product> products;
    static List<Order> orders;

    static void addDummyProducts() {
        // add 10 dummy products to the products list
        products.add(new Product("P001", "Laptop", 1200.00, 10));
        products.add(new Product("P002", "Smartphone", 800.00, 25));
        products.add(new Product("P003", "Headphones", 150.00, 50));
        products.add(new Product("P004", "Smartwatch", 250.00, 15));
        products.add(new Product("P005", "Keyboard", 75.00, 30));
        products.add(new Product("P006", "Mouse", 30.00, 40));
        products.add(new Product("P007", "Monitor", 300.00, 12));
        products.add(new Product("P008", "Webcam", 60.00, 20));
        products.add(new Product("P009", "Printer", 200.00, 8));
        products.add(new Product("P010", "Router", 90.00, 18));

    
    }



    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Customer customer1 = new Customer("C001", "Sanju","sanju@gmail.com", "Mathura");

        // display products
        products = new ArrayList<>();
        orders = new ArrayList<>();
        addDummyProducts();

        System.out.println("Available Products:");
        int i=0;
        for (Product product : products) {
            System.out.println(++i +". "+"ID"+ product.getProductId() + ", Name: " + product.getName() + ", Price: $" + product.getPrice() + ", Stock: " + product.getStock());
        }

        System.out.println("Enter serial no. of product you want to add to order");
        int sno=sc.nextInt();
        if(sno>10 || sno<0){
            System.out.println("Invalid Input");
            sc.close();
            return;
        }
        Date date=new Date();
        String orderDate=date.toString();

        Order order=new Order("OD001", customer1, products.get(sno-1), 5,orderDate);

        System.out.println("Order Created!");
        order.displayOrderDetails();

        System.out.println("Choose Payment Method");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Wallet");
        int choice=sc.nextInt();
        switch(choice){
            case 1:
                order.payUsingUPI("sanju@okSbi", order.getTotalAmount());
                break;
            case 2:
                order.payUsingCard("1234567890123456", "12/25", "123", order.getTotalAmount());
                break;
            case 3:
                order.payUsingWallet("465789",order.getTotalAmount());
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }    

        sc.close();
    }
}