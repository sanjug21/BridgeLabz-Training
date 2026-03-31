import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order {
    String customer;
    double total;

    public Order(String customer, double total) {
        this.customer = customer;
        this.total = total;
    }

    public String getCustomer() {
        return customer;
    }

    public double getTotal() {
        return total;
    }
}

public class OrderRevenueSummary {

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Kiran", 1200.50));
        orders.add(new Order("Meera", 850.00));
        orders.add(new Order("Kiran", 430.75));
        orders.add(new Order("Arjun", 999.99));
        orders.add(new Order("Meera", 150.25));

        Map<String, Double> revenueByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,
                        Collectors.summingDouble(Order::getTotal)));

        System.out.println("Order Revenue By Customer:");
        revenueByCustomer.forEach((customer, total) -> System.out.println(customer + " -> " + total));
    }
}
