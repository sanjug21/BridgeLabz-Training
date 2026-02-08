package banking_system_transactions;
import java.time.LocalTime;

class Transaction implements Runnable {
    private BankAccount account;
    private String customerName;
    private double amount;

    public Transaction(BankAccount account, String customerName, double amount) {
        this.account = account;
        this.customerName = customerName;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println("[" + customerName + "] Attempting to withdraw " + (int) amount);
        
        try {
            //thread.sleep method is used to wait for a random time to simulate the time taken for processing the transaction, which can lead to race conditions
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Transaction successful: " + customerName + ", Amount: " + (int) amount + ", Balance: " + (int) account.getBalance() + " at " + LocalTime.now());
        } else {
            System.out.println("Transaction failed: " + customerName + ", Amount: " + (int) amount + ", Insufficient balance at " + LocalTime.now());
        }
    }
}
