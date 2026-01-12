package online_banking_system;

interface BankingServices {

    void deposit(double amount);

    void withdraw(double amount) throws InsufficientBalanceException;

    double checkBalance();

    
} 