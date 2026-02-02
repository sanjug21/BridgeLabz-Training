package generate_json;

import java.time.LocalDate;
import java.util.List;


public class Order {
    private String orderId;
    private Customer customer;
    private List<Book> items;
    private LocalDate orderDate;

    public Order(String orderId, Customer customer, List<Book> items, LocalDate orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Book> getItems() {
        return items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
