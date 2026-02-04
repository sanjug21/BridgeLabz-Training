package digital_payment;

class Wallet implements Payment {
    private String walletName;
    private double balance;

    public Wallet(String walletName, double balance) {
        this.walletName = walletName;
        this.balance = balance;
    }

    @Override
    public void pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Wallet Payment: $" + amount + " | Wallet: " + walletName + " | Remaining Balance: $" + balance + " | Status: Success");
        } else {
            System.out.println("Wallet Payment: $" + amount + " | Wallet: " + walletName + " | Status: Failed - Insufficient Balance");
        }
    }
}
