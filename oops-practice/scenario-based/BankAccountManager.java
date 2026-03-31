public class BankAccountManager {
    public String accountHolderName;
    private double balance;
    private int accountNumber;

    BankAccountManager(String accountHolderName, double balance, int accountNumber) {
        this.accountHolderName=accountHolderName;
        this.balance=balance;
        this.accountNumber=accountNumber; 
    }
    public double checkBalance() {
        return balance;
    }
    public void deposit(double amount) {
        balance += amount;
    }
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
    public static void main(String[] args) {
        BankAccountManager acc=new BankAccountManager("Sanju", 5000.0, 983257650);
        // acc.displayAccountDetails();
        System.out.println("Account balance is: "+acc.checkBalance());
        acc.withdraw(5825);
        
        System.out.println("Amount deposited: 1000");
        acc.deposit(1000);
        System.out.println("Amount withdrawn: 5825");
        acc.withdraw(5825);
        System.out.println("Updated balance is: "+acc.checkBalance());
        System.out.println();
        acc.displayAccountDetails();
    }


   
}
