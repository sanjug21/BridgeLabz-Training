import java.util.InputMismatchException;
import java.util.Scanner;

public class uncheckedException {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try{
            int a=5;
            int b=0;
            int c=a/b;
            System.out.println(c);

        }catch(ArithmeticException e){
            System.out.println("You cannot divide by zero");
        }
        try {
            System.out.println("Enter a number: ");
            int num=sc.nextInt();
            System.out.println("You entered: "+num);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number");
        }
        sc.close();
    }
}
