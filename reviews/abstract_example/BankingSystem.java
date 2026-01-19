package abstract_example;

public class BankingSystem {
    
    public static void main(String[] args) {
        Account acc1=new Saving("SA001", "Pushpendra Singh", 1000, 2.5);
        Account acc2=new Current("CA001", "Yeshvendra Singh", 5000);
        acc1.calculateIntrest();
        acc2.calculateIntrest();
    }
}
