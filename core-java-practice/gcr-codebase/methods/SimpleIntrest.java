import java.util.*;

public class SimpleIntrest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Principal: ");
        double principal=sc.nextDouble();
        System.out.print("Enter Rate: ");
        double rate=sc.nextDouble();
        System.out.print("Enter Time: ");
        double time=sc.nextDouble();
        
        SimpleIntrest simpleIntrest=new SimpleIntrest();
        // Calculate simple interest p*r*t/100
        double interest=simpleIntrest.calculateSimpleInterest(principal,rate,time);
        
        System.out.println("The Simple Interest is "+interest+" for Principal "+principal+", Rate of Interest "+rate+" and Time "+time);
        sc.close();
    }

    public double calculateSimpleInterest(double principal,double rate,double time){
        return (principal*rate*time)/100;
    }
}
