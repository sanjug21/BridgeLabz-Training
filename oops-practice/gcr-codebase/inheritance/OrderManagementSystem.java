class Order {
    String orderId;
    String orderDate;

    public Order(String orderId, String orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

// method to get order status
    public String getOrderStatus() {
        return "Order Placed";
    }
}

class ShippedOrder extends Order {
    String trackingNumber;

    public ShippedOrder(String orderId, String orderDate, String trackingNumber) {
        // calling constructor of parent class Order
        super(orderId, orderDate);
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String getOrderStatus() {
        return "Shipped";
    }
}

class DeliveredOrder extends ShippedOrder {
    String deliveryDate;

    public DeliveredOrder(String orderId, String orderDate, String trackingNumber, String deliveryDate) {
        // calling constructor of parent class ShippedOrder
        super(orderId, orderDate, trackingNumber);
        this.deliveryDate = deliveryDate;
    }
    // overriding getOrderStatus method from ShippedOrder
    @Override
    public String getOrderStatus() {
        return "Delivered";
    }
}

public class OrderManagementSystem {
    public static void main(String[] args) {

        // multi-level inheritance demonstration

        // Creating instances of different order types

        // Creating an instance of Order
        Order order1 = new Order("ORD001", "2023-10-01");
        System.out.println("Order ID: " + order1.orderId);
        System.out.println("Current Status: " + order1.getOrderStatus());
        System.out.println("\n\n");

        // Creating an instance of ShippedOrder
        ShippedOrder order2 = new ShippedOrder("ORD002", "2023-10-02", "TRK12345");
        System.out.println("Order ID: " + order2.orderId);
        System.out.println("Current Status: " + order2.getOrderStatus());
        System.out.println("Tracking Number: " + order2.trackingNumber);
        System.out.println("\n\n");

        // Creating an instance of DeliveredOrder
        DeliveredOrder order = new DeliveredOrder("ORD123", "2026-01-01", "TRK98765", "2023-10-05");
        System.out.println("Order ID: " + order.orderId);
        System.out.println("Current Status: " + order.getOrderStatus());
        System.out.println("Tracking Number: " + order.trackingNumber);
        System.out.println("Delivery Date: " + order.deliveryDate);
    }
}