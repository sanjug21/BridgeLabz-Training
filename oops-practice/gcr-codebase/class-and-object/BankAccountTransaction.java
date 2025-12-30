public class BankAccountTransaction {
    
    String accountHolder;
    String accountNumber;
    double balance;
     

    public BankAccountTransaction(String accountHolder, String accountNumber, double balance){
        this.accountHolder=accountHolder;
        this.accountNumber=accountNumber;
        this.balance=balance;
    }

    public void deposit(double amount){
        balance+=amount;
        System.out.println("Deposited: "+amount);
        displayBalance();
    }

    public void withdraw(double amount){
        System.out.println("Withdraw amount is : "+amount);
        if(balance>=amount){
            balance-=amount;
            System.out.println("Withdrawn: "+amount);
            displayBalance();
        }else{
            System.out.println("Insufficient balance");
        }
    }

    public void displayBalance(){
        System.out.println("Current balance: "+balance);
    }

    public static void main(String[] args){
        System.out.println("State Bank of India\n");
        BankAccountTransaction account=new BankAccountTransaction("User", "12345", 700.0);
        account.displayBalance();
        account.deposit(200.0);
        account.withdraw(100.0);
        account.withdraw(1000.0); 
    }
}