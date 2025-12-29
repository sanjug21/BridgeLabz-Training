import java.util.*;

public class MovieTicketBooking {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Movie Ticket Booking App");

        // Loop for multiple customers until they choose to exit
        while(true){

            // Display movie options
            System.out.println("\nSelect Movie Type (1,2,3 or 0 to exit):");
            System.out.println("1. Premium (Rs. 150)");
            System.out.println("2. 3D (Rs. 200)");
            System.out.println("3. IMAX (Rs. 300)");
            System.out.print("Enter choice: ");
            int movieChoice=sc.nextInt();

            // handle exit choice
            if(movieChoice==0){
                System.out.println("Exiting app.");
                break;
            }

            // Display seat options
            System.out.println("Select Seat Type: ");
            System.out.println("1. Regular (Rs. 0)");
            System.out.println("2. Gold (Rs. 50 extra)");
            System.out.print("Enter choice: ");
            int seatChoice=sc.nextInt();

            // Display snack options
            System.out.println("Choose Snack Combo (1,2,3):");
            System.out.println("1. Popcorn (Rs. 100)");
            System.out.println("2. Burger (Rs. 150)");
            System.out.println("3. No Combo (Rs. 0)");
            System.out.print("Enter choice: ");
            int snackChoice=sc.nextInt();

            String movieType="";
            int basePrice=0;
            // switch to assign movie type and base price
            switch (movieChoice) {
                case 1:
                    movieType="Premium";
                    basePrice=150;
                    break;
                case 2:
                    movieType="3D";
                    basePrice=200;
                    break;
                case 3:
                    movieType="IMAX";
                    basePrice=300;
                    break;
                default:
                    break;
            }

            String seatType = "";
            int seatPrice = 0;
            // switch to assign seat type and price
            switch (seatChoice) {
                case 1:
                    seatType="Regular";
                    seatPrice=0;
                    break;
                case 2:
                    seatType="Gold";
                    seatPrice=50;
                    break;
                default:
                    break;
            }
            
            String snackName = "";
            int snackChoicePrice = 0;
            // switch to assign snack combo and price
            switch (snackChoice) {
                case 1:
                    snackName="Popcorn";
                    snackChoicePrice=100;
                    break;
                case 2:
                    snackName="Burger";
                    snackChoicePrice=150;
                    break;
                case 3:
                    snackName="No Combo";
                    snackChoicePrice=0;
                    break;
            
                default:
                    break;
            }
            int totalPrice=basePrice+seatPrice+snackChoicePrice;

            System.out.println("\n Booking Summary");
            System.out.println("Movie Type: "+movieType);
            System.out.println("Seat Type:  "+seatType);
            System.out.println("Snack Combo:"+snackName);
            System.out.println("Base Price: Rs.  "+basePrice);
            System.out.println("Seat Price: Rs.  "+seatPrice);
            System.out.println("Snack Price: Rs. "+snackChoicePrice);
            System.out.println("Total Price: Rs. "+totalPrice);
        }
        sc.close();
    }
}
