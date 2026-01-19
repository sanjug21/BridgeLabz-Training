package abstract_example;

public class Current extends Account {

    Current(String AccountNo,String AccountHolderName,long balance){
        super(AccountNo, AccountHolderName, balance);
    }
    void calculateIntrest(){
        System.out.println("Thier is no intrest on Current Account");
    }


}
