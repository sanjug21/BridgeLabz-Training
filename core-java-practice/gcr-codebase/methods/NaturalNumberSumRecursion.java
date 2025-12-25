import java.util.*;

public class NaturalNumberSumRecursion {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n=sc.nextInt();
        sc.close();
        // Checks weather n is a natural number
        if(n<0){
            System.out.println("Not a natural number");
            return;
        }
        
        NaturalNumberSumRecursion calculator=new NaturalNumberSumRecursion();
        long sumRec=calculator.sumRecursion(n);
        long sumForm=calculator.sumFormula(n);
        
        System.out.println("Sum using recursion: "+sumRec);
        System.out.println("Sum using formula: "+sumForm);
        
        if(sumRec==sumForm)System.out.println("Both sums are equal.");
        else System.out.println("Both sums are not equal.");
    }
    //Recursive method
    public long sumRecursion(int n){
        if(n==0) return 0;
        return n+sumRecursion(n-1);
    }
    //Formula method
    public long sumFormula(int n){
        return (long)n*(n+1)/2;
    }
}