package abstract_example;

abstract class Account {
    private String AccountNo;
    private String AccountHolderName;
    private long balance;

    Account(String AccountNo,String AccountHolderName,long balance){
        this.AccountNo=AccountNo;
        this.AccountHolderName=AccountHolderName;
        this.balance=balance;
    }
    public String getAccountNo() {
        return AccountNo;
    }
    public String getAccountHolderName(){
        return AccountHolderName;
    }
    public long getBalance(){
        return balance;
    }

    abstract void calculateIntrest();

}
