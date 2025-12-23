import java.util.*;

public class DelhiMetro{
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // Initial smart card balance for travel 
        double balance=500.0; 

        System.out.println("Welcome to Delhi Metro Smart Card System");
        System.out.println("Initial Balance: Rs. "+balance);

        // Loop until balance is 0 or user quits
        while (balance > 0) {
            System.out.print("\nEnter distance in km (or -1 to quit): ");
            double distance=sc.nextDouble();

            if (distance==-1) {
                System.out.println("Exiting system.");
                break;
            }
            // Calculates fare using ternary operator
            // Distance < 5km = Rs 10, 5-15km = Rs 20, > 15km = Rs 40
            double fare=(distance<5)?10:(distance<=15)?20:40;

            System.out.println("Calculated Fare: Rs. "+fare);
            if (balance>=fare) {
                balance-=fare;
                System.out.println("Ride successful. Remaining Balance: Rs. "+balance);
            } else {
                // Handle insufficient balance when balance is less than fare
                System.out.println("Insufficient balance. Please recharge.");
                break; 
            }
        }
        if (balance<=0) {
            System.out.println("Card balance exhausted.");
        }
        sc.close();
    }
}