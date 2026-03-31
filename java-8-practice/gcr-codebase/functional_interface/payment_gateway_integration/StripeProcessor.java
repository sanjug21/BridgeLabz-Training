package payment_gateway_integration;

class StripeProcessor implements PaymentProcessor {
    private String accountId;

    public StripeProcessor(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Stripe Payment: $" + String.format("%.2f", amount) + " | Account: " + accountId + " | Status: Success");
    }
}
