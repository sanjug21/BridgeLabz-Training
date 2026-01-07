import java.util.*;

// Exception Handling: Custom Exception
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// Abstraction: BankService Interface
interface BankingService {
    void deposit(double amount, boolean addTransactionDetails);

    void withdraw(double amount, boolean addTransactionDetails) throws InsufficientBalanceException;

    double checkBalance();

    void transfer(Account target, double amount) throws InsufficientBalanceException;
}

// OOP & Inheritance: Abstract Account class
abstract class Account implements BankingService {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;
    protected List<String> transactionHistory;

    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account created with balance: " + balance);
    }

    // Multithreading: synchronized for thread safety
    // interface methods implementation
    @Override
    public synchronized void deposit(double amount, boolean addTransactionDetails) {
        balance += amount;
        if (addTransactionDetails) {
            addTransaction("Deposited: " + amount + ", New Balance: " + balance);
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + " to " + accountNumber);

        }
    }

    @Override
    public synchronized void withdraw(double amount, boolean addTransactionDetails)
            throws InsufficientBalanceException {
        if (balance >= amount) {
            balance -= amount;
            if (addTransactionDetails) {
                addTransaction("Withdrew: " + amount + ", New Balance: " + balance);
                System.out.println(Thread.currentThread().getName() + " withdrew " + amount + " from " + accountNumber);
            }
        } else {
            throw new InsufficientBalanceException("Insufficient balance in " + accountNumber);
        }
    }

    @Override
    public void transfer(Account target, double amount) throws InsufficientBalanceException {
        // Withdraw from source (this) - Thread safe due to synchronized withdraw
        this.withdraw(amount, false);
        // Deposit to target - Thread safe due to synchronized deposit
        target.deposit(amount, false);

        this.addTransaction("Transferred " + amount + " to " + target.accountNumber);
        target.addTransaction("Received " + amount + " from " + this.accountNumber);
        System.out.println(
                "Transfer successful: " + amount + " from " + this.accountNumber + " to " + target.accountNumber);
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    public void addTransaction(String message) {
        transactionHistory.add(message);
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History for " + accountNumber + " (" + accountHolder + "):");
        for (String t : transactionHistory) {
            System.out.println(" - " + t);
        }
        System.out.println("-------------------------------");
    }

    // Polymorphism: Abstract method for interest calculation
    abstract void calculateInterest();

}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    @Override
    void calculateInterest() {
        double interest = balance * interestRate / 100;
        deposit(interest, true);
        addTransaction("Interest credited: " + interest);
        System.out.println("Interest calculated and credited for " + accountNumber);

    }
}

class CurrentsAccount extends Account {

    CurrentsAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    void calculateInterest() {
        System.out.println("No interest for Current Account " + accountNumber);
    }
}

public class OnlineBanking {

    public static void main(String[] args) {
        Account acc1 = new SavingsAccount("SA101", "Sanju", 1000, 5.0);
        Account acc2 = new CurrentsAccount("CA202", "Shubham", 2000);
        // created runnable objects for each user to pass to threads

        // Runnable is a functional interface used to define a "task" that can be
        // executed by a thread
        Runnable user1Task = () -> {
            try {
                acc1.deposit(500, true);
                acc1.transfer(acc2, 1000);
            } catch (InsufficientBalanceException e) {
                System.out.println("User1 Error: " + e.getMessage());
            }
        };

        Runnable user2Task = () -> {
            try {
                acc2.withdraw(100, true);
                acc2.transfer(acc1, 500);
            } catch (InsufficientBalanceException e) {
                System.out.println("User2 Error: " + e.getMessage());
            }
        };
        // Threads for both users
        Thread t1 = new Thread(user1Task, "Sanju");
        Thread t2 = new Thread(user2Task, "Shubham");

        // Start the threads
        t1.start();
        t2.start();

        try {
            // Wait for both threads to finish otherwise main thred will exit
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Final Account States ---");
        acc1.calculateInterest();
        acc1.printTransactionHistory();

        acc2.calculateInterest();
        acc2.printTransactionHistory();

    }

}
