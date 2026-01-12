package online_banking_system;

public class CurrentAccount extends Account {
    private final double OVERDRAFT_LIMIT = 1000;

    public CurrentAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        addTransactionDetails("Deposited: " + amount + ", New Balance: " + balance);
    }
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (balance + OVERDRAFT_LIMIT >= amount) {
            balance -= amount;
            addTransactionDetails("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
            throw new InsufficientBalanceException("Insufficient balance and overdraft limit exceeded in " + accountNumber);
        }
    }

    @Override
    public void calculateInterest() {
        System.out.println("No interest for Current Accounts.");
    }

    
}
