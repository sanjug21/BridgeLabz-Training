import java.util.*;

public class NaturalNumberSum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n=sc.nextInt();
        
        NaturalNumberSum naturalNumberSum=new NaturalNumberSum();
        // Calculate sum of all the natural numbers from 1 to n
        int sum=naturalNumberSum.calculateSum(n);
        
        System.out.println("Sum of "+n+" natural numbers: "+sum);
        sc.close();
    }

    public int calculateSum(int n){
        int sum=0;
        for(int i=1;i<=n;i++) sum+=i;
        return sum;
    }
}