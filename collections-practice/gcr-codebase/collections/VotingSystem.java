import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VotingSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Map<String, Integer> voteMap = new HashMap<>();
        
        Map<String, Integer> insertionOrderMap = new LinkedHashMap<>();

        while (true) {
            System.out.println("\n--- Voting System ---");
            System.out.println("1. Cast Vote");
            System.out.println("2. Display Results (Sorted by Candidate - TreeMap)");
            System.out.println("3. Display Results (Insertion Order - LinkedHashMap)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Candidate Name: ");
                    String candidate = sc.nextLine().trim();
                    if (!candidate.isEmpty()) {
                        voteMap.put(candidate, voteMap.getOrDefault(candidate, 0) + 1);
                        insertionOrderMap.put(candidate, insertionOrderMap.getOrDefault(candidate, 0) + 1);
                        
                        System.out.println("Vote cast for: " + candidate);
                    } else {
                        System.out.println("Invalid name.");
                    }
                    break;
                case 2:
                    Map<String, Integer> sortedMap = new TreeMap<>(voteMap);
                    System.out.println("Results (Sorted): " + sortedMap);
                    break;
                case 3:
                    System.out.println("Results (Insertion Order): " + insertionOrderMap);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}