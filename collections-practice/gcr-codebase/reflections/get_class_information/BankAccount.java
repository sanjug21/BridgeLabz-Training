package get_class_information;

public class BankAccount {
    private String accountNumber;
    private double balance;
    public String holderName;
    protected String branch;
    
    public BankAccount() {
        this.balance = 0.0;
    }
    
    public BankAccount(String accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0.0;
    }
    
    public BankAccount(String accountNumber, String holderName, double balance, String branch) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.branch = branch;
    }
    
    public void deposit(double amount) {
        validateTransaction(amount);
        this.balance += amount;
    }
    
    private void validateTransaction(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }
    
    protected double calculateInterest(double rate) {
        return balance * rate / 100;
    }
    
    public double getBalance() {
        return balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
}
