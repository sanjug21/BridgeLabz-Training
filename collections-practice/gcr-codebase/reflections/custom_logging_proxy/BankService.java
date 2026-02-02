package custom_logging_proxy;


public interface BankService {
    boolean transfer(int fromAccount, int toAccount, double amount);
    double getBalance(int accountId);
    void deposit(int accountId, double amount);
    void withdraw(int accountId, double amount);
}
