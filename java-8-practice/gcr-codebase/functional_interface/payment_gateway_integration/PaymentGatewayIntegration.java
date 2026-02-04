package payment_gateway_integration;

import java.util.Arrays;
import java.util.List;

public class PaymentGatewayIntegration {

    public static void main(String[] args) {
        List<PaymentProcessor> processors = Arrays.asList(
                new StripeProcessor("acct_2026"),
                new RazorpayProcessor("merchant_789")
        );

        System.out.println("Payment Gateway Integration");
        System.out.println("===========================");

        for (PaymentProcessor processor : processors) {
            processor.pay(199.99);
            processor.refund(49.99);
            System.out.println();
        }
    }
}
