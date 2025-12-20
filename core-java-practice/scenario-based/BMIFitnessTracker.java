import java.util.Scanner;

public class BMIFitnessTracker {
     
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Maya's BMI Fitness Tracker");

        // Takes Weight in kilograms
        System.out.print("Enter weight in kg: ");
        double weightInKg=sc.nextDouble();

        // Takes Height in meters
        System.out.print("Enter height in meters: ");
        double heightInMeters=sc.nextDouble();
        // Calculated BMI using the below formula
        double bmi=weightInKg/(heightInMeters*heightInMeters);

        System.out.printf("Your BMI is: %.2f\n", bmi);

        // Category user belongs to based on BMI
        if (bmi<18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi>=18.5 && bmi<25) {
            System.out.println("Category: Normal");
        } else {
            System.out.println("Category: Overweight");
        }
        sc.close();
    }
}