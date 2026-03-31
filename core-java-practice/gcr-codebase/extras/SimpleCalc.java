import java.util.*;

public class SimpleCalc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        double a=sc.nextDouble();
        double b=sc.nextDouble();
        System.out.print("Choose operation (+ - * /): ");
        char op=sc.next().charAt(0);

        if(op=='+') add(a,b);
        else if(op=='-')sub(a,b);
        else if(op=='*')mul(a,b);
        else if(op=='/')div(a,b);
        sc.close();
    }

    static void add(double a,double b){ 
        System.out.println("Result: "+(a+b)); 
    }
    static void sub(double a,double b){ 
        System.out.println("Result: "+(a-b)); 
    }
    static void mul(double a,double b){ 
        System.out.println("Result: "+(a*b)); 
    }
    static void div(double a,double b){ 
        System.out.println("Result: "+(a/b)); 
    }
}