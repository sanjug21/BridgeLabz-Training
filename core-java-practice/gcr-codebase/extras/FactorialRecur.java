import java.util.*;

public class FactorialRecur {
    
    public static void main(String[] args) {
        int n=getInput();
        long f=calculate(n);
        output(f);
    }

    static int getInput(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number: ");
        int number=sc.nextInt();
        sc.close();
        return number;
    }

    static long calculate(int n){
        if(n==0||n==1) return 1;
        return n*calculate(n-1);
    }

    static void output(long res){
        System.out.println("Factorial: "+res);
    }
}