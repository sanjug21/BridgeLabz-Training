import java.util.*;

public class GcdLcmCalc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        int a=sc.nextInt();
        int b=sc.nextInt();
        int gcd=gcd(a,b);
        int lcm=lcm(a,b,gcd);
        System.out.println("GCD: "+gcd);
        System.out.println("LCM: "+lcm);
        sc.close();
    }
    // Function to calculate GCD using Euclidean algorithm
    static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    // Function to calculate LCM using GCD
    static int lcm(int a,int b,int gcd){
        return (a*b)/gcd;
    }
}