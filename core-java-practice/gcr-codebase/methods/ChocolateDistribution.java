import java.util.*;

public class ChocolateDistribution {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of chocolates: ");
        int numberOfChocolates=sc.nextInt();
        System.out.print("Enter number of children: ");
        int numberOfChildren=sc.nextInt();
        
        ChocolateDistribution chocolateDistribution=new ChocolateDistribution();
        // Finds chocolates for each child and remaining chocolates
        int[] result=chocolateDistribution.findRemainderAndQuotient(numberOfChocolates,numberOfChildren);
        
        System.out.println("Chocolates per child: "+result[0]);
        System.out.println("Remaining chocolates: "+result[1]);
        sc.close();
    }

    public int[] findRemainderAndQuotient(int number,int divisor){
        return new int[]{number/divisor,number%divisor};
    }
}