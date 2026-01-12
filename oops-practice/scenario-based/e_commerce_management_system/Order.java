package e_commerce_management_system;

public class Order implements Payment {

    private String orderId;
    private Customer customer;
    private Product product;
    private int quantity;
    private double totalAmount;
    private String orderDate;
    private String status; // e.g., "Pending", "Shipped", "Delivered"

    public Order(String orderId, Customer customer, Product product, int quantity, String orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = product.getPrice() * quantity;
        this.status = "Pending";
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderId + " status updated to " + newStatus);
    }

    public void payUsingUPI(String UPIId, double amount) {
        System.out.println("Payment of Rs" + amount + " using UPI ID " + UPIId + " successful.");
    }
    public void payUsingCard(String cardNumber, String expiryDate, String cvv, double amount) {
        System.out.println("Payment of Rs" + amount + " using card number " + cardNumber + " successful.");
    }
    public void payUsingWallet(String walletId, double amount) {
        System.out.println("Payment of Rs" + amount + " using wallet ID " + walletId + " successful.");
    }



    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Product Name: " + product.getName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
    }

}