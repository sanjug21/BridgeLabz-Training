import java.util.Scanner;

public class CoffeeCounter {
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to the Coffee Counter Chronicles!");

        // Loop to allow multiple orders till user decides to exit
        while (true) {
            System.out.println("\nSelect Coffee Type:");
            System.out.println("1. Espresso (Rs. 100)");
            System.out.println("2. Latte (Rs. 150)");
            System.out.println("3. Cappuccino (Rs. 180)");
            System.out.print("Enter choice (1-3) or type 'exit' to quit: ");

            // Check if the input is an integer (for menu choice) to avoid exceptions 
            if (sc.hasNextInt()) {
                int choice=sc.nextInt();
                double price=0;
                String coffeeType="";

                // used switch-case for updating the coffee type and price based on user choice
                switch (choice) {
                    case 1:
                        price=150;
                        coffeeType="Espresso";
                        break;
                    case 2:
                        price=200;
                        coffeeType="Latte";
                        break;
                    case 3:
                        price=220;
                        coffeeType="Cappuccino";
                        break;
                    // handeles invalid menu choice other than 1-3
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                        continue;
                }
                //To enter the quantity of coffee
                System.out.print("Enter quantity: ");
                int quantity=sc.nextInt();

                // Calculated Bill based on type and quantity of coffee including 18% GST
                double basePrice=price*quantity;
                double gst=basePrice*0.18; 
                double totalBill=basePrice+gst;

                System.out.println("Bill Details:");
                System.out.println("Coffee Type:"+coffeeType+" and Quantity: "+quantity);
                System.out.println("Total (incl. 18% GST): Rs. "+totalBill);

            } else {
                // Handle non-integer input and exit the loop if user types 'exit'
                String input=sc.next();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Thanks for visiting.");
                    break; 
                }
            }
        }
        sc.close();
    }
}