package payment_gateway_integration;

public interface PaymentProcessor {
    void pay(double amount);

    default void refund(double amount) {
        System.out.println("Refund processed for $" + String.format("%.2f", amount));
    }
}
