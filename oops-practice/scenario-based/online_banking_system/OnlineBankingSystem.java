package online_banking_system;

public class OnlineBankingSystem {
    public static void main(String[] args) throws InsufficientBalanceException{
        Account acc1=new SavingAccount("SA101", "Sanju", 5000.0);
        Account acc2=new CurrentAccount("CA101", "Shubham", 1000.0);

        acc1.withdraw(2000.0);
        acc2.deposit(1500);

        acc2.transfer(acc1, 2000);
        acc1.calculateInterest();

        acc1.printTransactionHistory();
        acc2.printTransactionHistory();


    }
}
