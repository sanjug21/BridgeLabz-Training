package digital_wallet_system;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private String walletId;
    private double balance;
    private List<String> transactions;



    public Wallet(String walletId) {
        this.walletId = walletId;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }
    public String getWalletId() {
        return walletId;
    }
    public double getBalance() {
        return balance;
    }
    public List<String> getTransactions() {
        return transactions;
    }


    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }


    public void deposit(double amount) {
        balance += amount;
    
    }
    public void withdraw(double amount)  {
    try {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }


    

    
    
}
