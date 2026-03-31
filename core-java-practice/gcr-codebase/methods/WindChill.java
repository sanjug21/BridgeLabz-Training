import java.util.*;

public class WindChill {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter temperature: ");
        double temperature=sc.nextDouble();
        System.out.print("Enter wind speed: ");
        double windSpeed=sc.nextDouble();
        
        WindChill windChill=new WindChill();
        // Calculate wind chill using given formula
        double windChillTemp=windChill.calculateWindChill(temperature,windSpeed);
        
        System.out.println("Wind chill temperature: "+windChillTemp);
        sc.close();
    }

    public double calculateWindChill(double temperature,double windSpeed){
        return 35.74+0.6215*temperature+(0.4275*temperature-35.75)*Math.pow(windSpeed,0.16);
    }
}