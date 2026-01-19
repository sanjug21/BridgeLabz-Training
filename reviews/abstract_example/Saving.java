package abstract_example;

public class Saving extends Account {
    final double rate;
    Saving(String AccountNo,String AccountHolderName,long balance,double rate){
        super(AccountNo, AccountHolderName, balance);
        this.rate=rate;
    }
    @Override
    void calculateIntrest(){
        double intrest=(getBalance()*rate)/100;
        System.out.println("The iintrest is:"+intrest);
    }

}
