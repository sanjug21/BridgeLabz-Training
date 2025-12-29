import java.util.*;

public class SandeepFitnessChallenge {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dailyPushups = new int[7];

        System.out.println("Sandeep's Fitness Challenge Tracker");
        System.out.println("Enter number of push-ups for 7 days:");

        // Store counts for a week
        for (int i=0;i<7;i++) {
            System.out.print("Day "+(i+1)+" push-ups: ");
            dailyPushups[i]=sc.nextInt();
        }

        int totalPushups=0;
        int daysWithPushups=7;

        // Use for-each to calculate total
        for (int pushups:dailyPushups) {
            // Use continue to skip rest days
            if(pushups==0){
                daysWithPushups--;
                continue;
            }
            totalPushups+=pushups;
        }

        double average=totalPushups/7.0;

        System.out.println("Total push-ups for the week: "+totalPushups);
        System.out.println("Average push-ups per day: "+average);
        System.out.println("Days with push-ups: "+daysWithPushups);
        System.out.println("Rest days: "+(7-daysWithPushups));
        sc.close();
    }
}