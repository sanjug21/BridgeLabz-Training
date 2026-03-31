package payment_gateway_integration;

class RazorpayProcessor implements PaymentProcessor {
    private String merchantId;

    public RazorpayProcessor(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Razorpay Payment: $" + String.format("%.2f", amount) + " | Merchant: " + merchantId + " | Status: Success");
    }
}
