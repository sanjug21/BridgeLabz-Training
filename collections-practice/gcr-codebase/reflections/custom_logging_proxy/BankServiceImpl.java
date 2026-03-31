package custom_logging_proxy;

import java.util.*;

/**
 * Bank Service Implementation
 * Provides concrete implementation of banking operations with balance tracking
 */
public class BankServiceImpl implements BankService {
    private Map<Integer, Double> accounts = new HashMap<>();
    
    public BankServiceImpl() {
        // Initialize with sample accounts
        accounts.put(100, 10000.0);
        accounts.put(200, 5000.0);
        accounts.put(300, 7500.0);
    }
    
    @Override
    public boolean transfer(int fromAccount, int toAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        
        if (!accounts.containsKey(fromAccount)) {
            throw new IllegalArgumentException("Source account not found");
        }
        
        if (!accounts.containsKey(toAccount)) {
            throw new IllegalArgumentException("Destination account not found");
        }
        
        double fromBalance = accounts.get(fromAccount);
        if (fromBalance < amount) {
            System.out.println("  → Insufficient funds. Available: $" + fromBalance);
            return false;
        }
        
        accounts.put(fromAccount, fromBalance - amount);
        accounts.put(toAccount, accounts.get(toAccount) + amount);
        
        System.out.println("  → Transfer successful: $" + amount + 
            " from account " + fromAccount + " to account " + toAccount);
        return true;
    }
    
    @Override
    public double getBalance(int accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        
        double balance = accounts.get(accountId);
        System.out.println("  → Balance for account " + accountId + ": $" + balance);
        return balance;
    }
    
    @Override
    public void deposit(int accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        
        accounts.put(accountId, accounts.get(accountId) + amount);
        System.out.println("  → Deposited $" + amount + " to account " + accountId);
    }
    
    @Override
    public void withdraw(int accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        
        double currentBalance = accounts.get(accountId);
        if (currentBalance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        
        accounts.put(accountId, currentBalance - amount);
        System.out.println("  → Withdrew $" + amount + " from account " + accountId);
    }
}
