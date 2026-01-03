class BankAccountDetails {
    String accountNumber;
    double balance;

    public BankAccountDetails(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void displayInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

class SavingsAccount extends BankAccountDetails {
    double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void displayAccountType() {
        System.out.println("Account Type: Savings Account");
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}

class CheckingAccount extends BankAccountDetails {
    double withdrawalLimit;

    public CheckingAccount(String accountNumber, double balance, double withdrawalLimit) {
        super(accountNumber, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public void displayAccountType() {
        System.out.println("Account Type: Checking Account");
        System.out.println("Withdrawal Limit: $" + withdrawalLimit);
    }
}

class FixedDepositAccount extends BankAccountDetails {
    int maturityPeriod; // in months

    public FixedDepositAccount(String accountNumber, double balance, int maturityPeriod) {
        super(accountNumber, balance);
        this.maturityPeriod = maturityPeriod;
    }

    public void displayAccountType() {
        System.out.println("Account Type: Fixed Deposit Account");
        System.out.println("Maturity Period: " + maturityPeriod + " months");
    }
}

public class BankAccountTypes {
    public static void main(String[] args) {

        // hierarchical inheritance


        // creating instances of different account types

        // instance of SavingsAccount
        SavingsAccount sa = new SavingsAccount("SA101", 5000.0, 3.5);
        // instance of CheckingAccount
        CheckingAccount ca = new CheckingAccount("CA102", 2000.0, 1000.0);
        // instance of FixedDepositAccount
        FixedDepositAccount fd = new FixedDepositAccount("FD103", 10000.0, 12);

        System.out.println("--- Savings Account ---");
        sa.displayInfo();
        sa.displayAccountType();
        System.out.println();

        System.out.println("--- Checking Account ---");
        ca.displayInfo();
        ca.displayAccountType();
        System.out.println();

        System.out.println("--- Fixed Deposit Account ---");
        fd.displayInfo();
        fd.displayAccountType();
    }
}