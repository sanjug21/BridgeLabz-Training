package digital_wallet_system;

public class DigitalWalletSystem implements WalletServices{
    @Override
    public void transferMoney(Wallet sender, Wallet recipient, double amount)  {
        sender.withdraw(amount);
        recipient.deposit(amount);
        sender.addTransaction("Transferred " + amount + " to " + recipient.getWalletId());
        recipient.addTransaction("Received " + amount + " from " + sender.getWalletId());
    }

    public static void main(String[] args) {
        System.out.println("=== Digital Wallet System Demo ===");
        DigitalWalletSystem walletSystem = new DigitalWalletSystem();
        Wallet w1=new Wallet("W001");
        Wallet w2=new Wallet("W002");
        w1.deposit(1000);
        w1.addTransaction("Deposited 1000 to wallet of W1");
        w2.deposit(500);
        
        w2.addTransaction("Deposited 500 to wallet of W2");

        // will throw exception about insufficient balance
        w1.withdraw(2500);

        walletSystem.transferMoney(w1, w2, 250);

        w1.getTransactions().forEach(System.out::println);
        w2.getTransactions().forEach(System.out::println);


        // for(String transaction:w1.getTransactions()){
        //     System.out.println(transaction);
        // }
        // for(String transaction:w2.getTransactions()){
        //     System.out.println(transaction);
        // }
    }
    
}
