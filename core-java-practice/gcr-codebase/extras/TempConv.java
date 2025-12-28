import java.util.*;

public class TempConv {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("1. Fahrenheit to Celsius\n2. Celsius to Fahrenheit");
        int ch=sc.nextInt();
        System.out.print("Enter temperature: ");
        double t=sc.nextDouble();
        
        if(ch==1)System.out.println("Celsius: "+toC(t));
        else System.out.println("Fahrenheit: "+toF(t));
        sc.close();
    }
    // function to convert Fahrenheit to Celsius
    static double toC(double f){
        return (f-32)*5/9;
    }
    // function to convert Celsius to Fahrenheit
    static double toF(double c){
        return (c*9/5)+32;
    }
}