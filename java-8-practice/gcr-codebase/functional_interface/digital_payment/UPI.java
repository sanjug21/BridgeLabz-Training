package digital_payment;

class UPI implements Payment {
    private String upiId;

    public UPI(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("UPI Payment: $" + amount + " | UPI ID: " + upiId + " | Status: Success");
    }
}
