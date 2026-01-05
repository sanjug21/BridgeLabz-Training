import java.util.ArrayList;
import java.util.List;

// Interface for loan capabilities
interface Loanable {
    void applyForLoan(double amount);
    double calculateLoanEligibility();
}

// Abstract class for Bank Account
abstract class BankAccDetails {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccDetails(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Encapsulation: Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    
    // Protected setter to allow subclasses to modify balance if needed, or keep it strictly controlled via deposit/withdraw
    protected void setBalance(double balance) { this.balance = balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(holderName + " deposited: Rs " + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println(holderName + " withdrew: Rs " + amount);
        } else {
            System.out.println("Insufficient balance for " + holderName);
        }
    }

    public abstract double calculateInterest();
}

class SavAccount extends BankAccDetails implements Loanable {
    private static final double INTEREST_RATE = 0.04; // 4% Interest

    public SavAccount(String accNum, String name, double balance) {
        super(accNum, name, balance);
    }
    // abstract method to calculate interest for savings accounts
    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }
    // Loanable interface methods
    @Override
    public double calculateLoanEligibility() {
        return getBalance() * 2.5; // Eligible for 2.5x balance
    }
    // Loanable interface methods
    @Override
    public void applyForLoan(double amount) {
        if (amount <= calculateLoanEligibility()) {
            System.out.println("Loan of Rs " + amount + " approved for " + getHolderName());
        } else {
            System.out.println("Loan denied. Max eligibility is Rs " + calculateLoanEligibility());
        }
    }
}

class CurrentAccount extends BankAccDetails implements Loanable {
    public CurrentAccount(String accNum, String name, double balance) {
        super(accNum, name, balance);
    }
    // abstract method to calculate interest for current accounts
    @Override
    public double calculateInterest() {
        return 0.0; // Typically no interest on current accounts
    }
    // Loanable interface methods
    @Override
    public double calculateLoanEligibility() {
        return getBalance() * 5.0; // Eligible for 5x balance
    }
    // Loanable interface methods
    @Override
    public void applyForLoan(double amount) {
        if (amount <= calculateLoanEligibility()) {
            System.out.println("Business Loan of Rs " + amount + " approved for " + getHolderName());
        } else {
            System.out.println("Loan denied. Max eligibility is Rs " + calculateLoanEligibility());
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {

        List<BankAccDetails> accounts = new ArrayList<>();
        accounts.add(new SavAccount("SA101", "Alice Smith", 5000));
        accounts.add(new CurrentAccount("CA202", "Bob Enterprises", 20000));

        System.out.println("=== Banking System ===");
        for (BankAccDetails acc : accounts) {
            System.out.println("Account: " + acc.getAccountNumber() + " | Holder: " + acc.getHolderName());
            System.out.println("Balance: Rs " + acc.getBalance());
            System.out.println("Interest: Rs " + acc.calculateInterest());
            
            if (acc instanceof Loanable) {
                // type cast the acc as we can access the Loanable interface methods here the object we had made is of type BankAccountDetails
                // and it does not have the Loanable interface methods
                Loanable loanAcc = (Loanable) acc;
                System.out.println("Loan Eligibility: Rs " + loanAcc.calculateLoanEligibility());
                // Attempt to apply for a loan
                loanAcc.applyForLoan(10000);
            }
            System.out.println("\n");
        }
    }
}