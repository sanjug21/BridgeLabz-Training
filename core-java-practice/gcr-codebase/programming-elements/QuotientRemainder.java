import java.util.Scanner;

public class QuotientRemainder {
        public static void main(String[] args) {
                Scanner sc=new Scanner(System.in);
                int dividend=sc.nextInt();
                int divisor=sc.nextInt();
                int q=dividend/divisor;
                int rem=dividend%divisor;
                System.out.println("The Quotient is " + q + " and Reminder is " + rem + " of two number " + dividend + " and " + divisor);
        }
}
