import java.util.*;

public class QuotientRemainder {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number: ");
        int number=sc.nextInt();
        System.out.print("Enter divisor: ");
        int divisor=sc.nextInt();
        
        QuotientRemainder quotientRemainder=new QuotientRemainder();
        // calls method to find quotient and remainder
        int[] result=quotientRemainder.findRemainderAndQuotient(number,divisor);
        
        System.out.println("Quotient: "+result[0]);
        System.out.println("Remainder: "+result[1]);
        sc.close();
    }

    public int[] findRemainderAndQuotient(int number,int divisor){
        return new int[]{number/divisor,number%divisor};
    }
}