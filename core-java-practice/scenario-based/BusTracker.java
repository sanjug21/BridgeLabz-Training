import java.util.*;

public class BusTracker {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double totalDistance=0.0;

        System.out.println("Bus Route Distance Tracker ");

        // Loop until passenger decides to exit the bus
        while(true){
            System.out.print("\nEnter distance to next stop (in km): ");
            
            if (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }
            double distance=sc.nextDouble();
            totalDistance+=distance;
            System.out.println("Total distance covered: "+totalDistance+" km");

            System.out.print("Do you want to get off at this stop? (yes/no): ");
            String choice=sc.next();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("You got off the bus. Final Distance: "+totalDistance+" km");
                break;
            }
        }
        sc.close();
    }
}