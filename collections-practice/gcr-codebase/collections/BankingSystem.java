import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class BankingSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // HashMap to store customer accounts (AccountNumber -> Balance)
        Map<String, Double> accounts = new HashMap<>();
        accounts.put("123456", 1000.00);
        accounts.put("789012", 500.00);
        accounts.put("345678", 2000.00);

        // TreeMap to sort customers by balance (Balance -> AccountNumber)
        TreeMap<Double, String> sortedAccounts = new TreeMap<>();
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            sortedAccounts.put(entry.getValue(), entry.getKey());
        }

        // Queue to process withdrawal requests (AccountNumber)
        Queue<String> withdrawalRequests = new LinkedList<>();

        while (true) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Request Withdrawal");
            System.out.println("2. Process Withdrawals");
            System.out.println("3. Display Accounts (Sorted by Balance)");
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
                    System.out.print("Enter Account Number: ");
                    String accountNumber = sc.nextLine().trim();
                    if (accounts.containsKey(accountNumber)) {
                        withdrawalRequests.add(accountNumber);
                        System.out.println("Withdrawal request added for account: " + accountNumber);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 2:
                    while (!withdrawalRequests.isEmpty()) {
                        String accNumber = withdrawalRequests.poll();
                        System.out.println("Processing withdrawal for account: " + accNumber);
                        // Simulate withdrawal processing (in real system, database interaction happens here)
                        System.out.println("Withdrawal processed for account: " + accNumber);
                    }
                    System.out.println("All withdrawal requests processed.");
                    break;
                case 3:
                    System.out.println("--- Accounts (Sorted by Balance) ---");
                    for (Map.Entry<Double, String> entry : sortedAccounts.entrySet()) {
                        System.out.println("Account: " + entry.getValue() + ", Balance: $" + entry.getKey());
                    }
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
