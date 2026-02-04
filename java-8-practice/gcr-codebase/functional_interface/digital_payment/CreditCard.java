package digital_payment;
class CreditCard implements Payment {
    private String cardNumber;
    private String cardHolder;

    public CreditCard(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(double amount) {
        String maskedCard = "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Credit Card Payment: $" + amount + " | Card: " + maskedCard + " | Holder: " + cardHolder + " | Status: Success");
    }
}
