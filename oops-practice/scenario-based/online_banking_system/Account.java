package online_banking_system;

import java.util.ArrayList;
import java.util.List;

abstract class Account implements BankingServices{
    protected String accountHolder;
    protected String accountNumber;
    protected double balance;
    protected List<String> transactionHistory;


    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        addTransactionDetails("Account created with balance: " + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException("Insufficient balance in " + accountNumber);
        }
    }

    public void transfer(Account target, double amount) throws InsufficientBalanceException {
        withdraw(amount);
        target.deposit(amount);
        addTransactionDetails("Transferred " + amount + " to " + target.accountNumber);
    }

    protected void addTransactionDetails(String transactionDetails){
        transactionHistory.add(transactionDetails);

    }
    public void printTransactionHistory() {
        System.out.println("\nTransaction History for " + accountNumber + " (" + accountHolder + "):");
        for (String t : transactionHistory) {
            System.out.println(" - " + t);
        }
        System.out.println("-------------------------------");
    }

    abstract public void calculateInterest();
}
