package online_banking_system;

 class SavingAccount extends Account {
    private final double interestRate=5;
    public SavingAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        addTransactionDetails("Deposited: " + amount + ", New Balance: " + balance);
    }
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        super.withdraw(amount);
        addTransactionDetails("Withdrew: " + amount + ", New Balance: " + balance);
    }

    @Override
    public void calculateInterest(){
        double interest=balance*interestRate/100;
        deposit(interest);
        addTransactionDetails("Interest credited: " + interest);
    } 
    
}
