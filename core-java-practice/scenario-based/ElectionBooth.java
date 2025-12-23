import java.util.*;

public class ElectionBooth {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int candidate1=0, candidate2=0, candidate3=0;

        System.out.println("Welcome to the Election Booth!");
        System.out.println("Candidates: 1. Alice | 2. Bob | 3. Charlie");

        while (true) {
            System.out.print("\nEnter Age (or type -1 to exit): ");
            // handels invalid input for age
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
                continue;
            }

            int age = sc.nextInt();

            // -1 as input to exit the booth
            if (age == -1) {
                break;
            }

            // Check eligibility of the voter based on age
            if (age>=18 ) {
                System.out.print("Enter vote (1, 2, or 3): ");
                int vote=sc.nextInt();

                if (vote==1) {
                    candidate1++;
                    System.out.println("you voted for Alice.");
                } else if (vote==2) {
                    candidate2++;
                    System.out.println("you voted for Bob.");
                } else if (vote==3) {
                    candidate3++;
                    System.out.println("you voted for Charlie.");
                } else {
                    System.out.println("Invalid choice vote got invalid.");
                }
            } else {
                System.out.println("Not eligible to vote (Age < 18).");
            }
        }
        System.out.println("Polling closed. Final counts -> Alice: "+candidate1+", Bob: "+candidate2+", Charlie: "+candidate3);
        sc.close();
    }
}