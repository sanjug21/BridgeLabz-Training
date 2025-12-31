 class BankAccount{
    public long accountNumber;
    protected String accountHolder;
    private double balance;
    public BankAccount(long accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getter and Setter for private member balance
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
public class SavingAccount extends BankAccount{
    // Constructor for SavingAccount with super keyword
    public SavingAccount(long accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    public static void main(String[] args) {
        SavingAccount sa = new SavingAccount(123456789, "Sanju", 999999000.0);

        // both are public and protected members are accessible here
        System.out.println("Account Number: " + sa.accountNumber);
        System.out.println("Account Holder: " + sa.accountHolder);

    }
}