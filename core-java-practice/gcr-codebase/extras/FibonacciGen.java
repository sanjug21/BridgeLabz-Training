import java.util.*;

public class FibonacciGen {
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of terms: ");
        int n=sc.nextInt();
        printFibo(n);
        sc.close();
    }
    // Function to calculate and print Fibonacci series
    static void printFibo(int n){
        int a=0,b=1;
        if(n>=1) System.out.print(a);
        if(n>=2) System.out.print(" "+b);
        for(int i=2;i<n;i++){
            int c=a+b;
            System.out.print(" "+c);
            a=b;
            b=c;
        }
        System.out.println();
    }
}