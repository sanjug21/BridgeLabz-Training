public class BankAccount {
    final String BankName="State Bank of India";
    static int totalAccounts=0;
    String accountHolderName;
    final int accountNumber;
    private double balance;
    BankAccount(String accountHolderName,int accountNumber,double balance){
        this.accountHolderName=accountHolderName;
        this.accountNumber=accountNumber;
        this.balance=balance;
        totalAccounts++;
    }
    // static method to get total number of accounts
    static int getTotalAccounts(){
        return totalAccounts;
    }
    public double getBalance(){
        return balance;
    }

    // getter and setter methods for account holder name
    public void deposit(double amount){
        balance+=amount;
    }
    public void withdraw(double amount){
        if(amount<=balance){
            balance-=amount;
        }else{
            System.out.println("Insufficient balance");
        }
    }

    // method to display account details
    void displayInfo() {
        System.out.println("Bank: " + BankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("Sanju", 1001, 5000);
        BankAccount acc2 = new BankAccount("Shubham", 1002, 3000);

        // check instance of objects
        System.out.println("acc1 is instance of BankAccount: " + (acc1 instanceof BankAccount));
        System.out.println("acc2 is instance of BankAccount: " + (acc2 instanceof BankAccount));

        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());

        // display account details
        acc1.displayInfo();
        acc2.displayInfo();

        // perform some transactions
        acc1.deposit(2000);
        acc2.withdraw(1000);

        // display updated account details
        System.out.println("After transactions:");
        acc1.displayInfo();
        acc2.displayInfo();
    }

}
