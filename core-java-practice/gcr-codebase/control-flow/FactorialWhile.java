import java.util.Scanner;

public class FactorialWhile {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();

        if(number>0){
            long factorial=1;
            int i=1;
            while(i<=number){
                factorial*=i;
                i++;
            }
            System.out.println("The factorial of "+number+" is "+factorial);
        } else {
            System.out.println("The number "+number+" is not a positive integer");
        }
        sc.close();
    }
}
