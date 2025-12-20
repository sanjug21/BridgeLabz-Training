import java.util.Scanner;

public class FactorialFor {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();

        if(number>0){
            long factorial=1;
            for(int i=1;i<=number;i++){
                factorial*=i;
            }
            System.out.println("The factorial of "+number+" is "+factorial);
        } else {
            System.out.println("The number "+number+" is not a positive integer");
        }
        sc.close();
    }
}
