import java.util.*;

public class PrimeChecker {
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number to check weather it is Prime or not: ");
        int n=sc.nextInt();
        if(isPrime(n)) System.out.println(n+" is Prime");
        else System.out.println(n+" is not Prime");
        sc.close();
    }

    static boolean isPrime(int n){
        if(n<=1) return false;
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return true;
    }
}