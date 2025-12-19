import java.util.Scanner;
public class TemperatureConvert {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter temperature in Celsius:");
        double celcius=sc.nextDouble();
        double fahrenheit=(celcius*9/5)+32;
        System.out.println("Temperature in Fahrenheit: "+fahrenheit);
        sc.close();
    }
}
