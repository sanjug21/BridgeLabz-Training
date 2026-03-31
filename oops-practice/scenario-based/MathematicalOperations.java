import java.util.Scanner;

public class MathematicalOperations {
    public int factorial(int number){
        if(number<0){
            return -1;
        }
        if(number==0){
            return 1;
        }
        int res=1;
        for(int i=1;i<=number;i++){
            res*=i;
        }
        return res;
    }
    public boolean isPrime(int number){

        if(number<=1){
            return false;
        }
        for(int i=2;i*i<=number;i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
    public void fibnacci(int number){
        if(number<=0){
            return;
        }
        if(number==1){
            System.out.println(0);
            return;
        }

        int a=0;
        int b=1;
        System.out.println("Fibonacci series");
        System.out.print(a+" "+b+" ");
        for(int i=2;i<number;i++){
            int c=a+b;
            System.out.print(c+" ");
            a=b;
            b=c;
        }
        System.out.println();
    }
    public int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        MathematicalOperations mo=new MathematicalOperations();
        System.out.println("Enter a number to find factorial:");
        int factNumber=sc.nextInt();
        System.out.println("Factorial of "+factNumber+" is: "+mo.factorial(factNumber));

        System.out.println("\n");

        System.out.println("Enter a number to check if it is prime:");
        int primeNumber=sc.nextInt();
        System.out.println("Is number "+primeNumber+" is a prime: "+mo.isPrime(primeNumber));

        System.out.println("\n");

        System.out.println("Enter a number to generate fibonacci series:");
        int fibNumber=sc.nextInt();
        mo.fibnacci(fibNumber);

        System.out.println("\n");
        System.out.println("Enter two numbers to find GCD:");
        int a=sc.nextInt();
        int b=sc.nextInt();
        System.out.println("GCD of "+a+" and "+b+" is: "+mo.gcd(a,b));
        sc.close();
    }

}
