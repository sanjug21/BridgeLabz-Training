import java.util.*;

public class RohansLibrary {
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Rohan's Library Reminder App");

        // Repeat for 5 books 
        for(int i=1;i<=5;i++){
            System.out.println("\nBook "+i+":");
            System.out.print("Enter due date (day of month): ");
            int dueDate=sc.nextInt();
            System.out.print("Enter return date (day of month): ");
            int returnDate=sc.nextInt();

            // Calculate fine if returned late
            if(returnDate>dueDate){
                int daysLate=returnDate-dueDate;
                int fine=daysLate*5;
                System.out.println("Returned late by "+daysLate+" days. Fine for late return: Rs. "+fine);
            }else{
                System.out.println("Returned on time. No fine.");
            }
        }
        sc.close();
    }
}
