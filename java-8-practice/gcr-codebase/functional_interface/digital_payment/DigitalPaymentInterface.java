package digital_payment;

public class DigitalPaymentInterface {

    public static void main(String[] args) {
        Payment upi = new UPI("john@paytm");
        Payment creditCard = new CreditCard("1234567890123456", "John Doe");
        Payment wallet = new Wallet("PayPal", 500.00);

        System.out.println("Digital Payment System:");
        System.out.println("----------------------");

        upi.pay(150.00);
        creditCard.pay(250.00);
        wallet.pay(100.00);

        System.out.println();
        System.out.println("Attempting payment with insufficient balance:");
        wallet.pay(450.00);
    }
}
