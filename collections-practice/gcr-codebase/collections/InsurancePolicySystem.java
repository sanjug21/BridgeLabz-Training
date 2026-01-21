import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Policy implements Comparable<Policy> {
    private String policyNumber;
    private String policyholderName;
    private LocalDate expiryDate;
    private String coverageType;
    private double premiumAmount;

    public Policy(String policyNumber, String policyholderName, LocalDate expiryDate, String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() { return policyNumber; }
    public String getPolicyholderName() { return policyholderName; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getCoverageType() { return coverageType; }
    public double getPremiumAmount() { return premiumAmount; }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Policy other = (Policy) obj;
        return Objects.equals(policyNumber, other.policyNumber);
    }

    @Override
    public int compareTo(Policy other) {
        // Primary sort: Expiry Date (for TreeSet requirement)
        int dateComparison = this.expiryDate.compareTo(other.expiryDate);
        if (dateComparison != 0) return dateComparison;
        // Secondary sort: Policy Number (to ensure consistency with equals)
        return this.policyNumber.compareTo(other.policyNumber);
    }

    @Override
    public String toString() {
        return String.format("Policy[ID=%s, Name=%s, Expiry=%s, Type=%s, Premium=%.2f]", 
            policyNumber, policyholderName, expiryDate, coverageType, premiumAmount);
    }
}

public class InsurancePolicySystem {
    // 1. Store Unique Policies using different Sets
    private static Set<Policy> hashSet = new HashSet<>();       // Quick lookups
    private static Set<Policy> linkedHashSet = new LinkedHashSet<>(); // Maintain insertion order
    private static Set<Policy> treeSet = new TreeSet<>();       // Sorted by expiry date
    
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Insurance Policy Management System ---");
            System.out.println("1. Add Policy");
            System.out.println("2. View All Policies (HashSet - No Order)");
            System.out.println("3. View All Policies (LinkedHashSet - Insertion Order)");
            System.out.println("4. View All Policies (TreeSet - Sorted by Expiry)");
            System.out.println("5. View Policies Expiring Soon (Next 30 Days)");
            System.out.println("6. View Policies by Coverage Type");
            System.out.println("7. Search Policy by Number");
            System.out.println("8. Search Policies by Policyholder Name");
            System.out.println("9. Remove Expired Policies");
            System.out.println("10. Run Performance Comparison");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: addPolicy(); break;
                case 2: printPolicies(hashSet); break;
                case 3: printPolicies(linkedHashSet); break;
                case 4: printPolicies(treeSet); break;
                case 5: viewExpiringSoon(); break;
                case 6: viewByCoverageType(); break;
                case 7: searchByPolicyNumber(); break;
                case 8: searchByPolicyholder(); break;
                case 9: removeExpiredPolicies(); break;
                case 10: runPerformanceComparison(); break;
                case 11: 
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void addPolicy() {
        System.out.print("Enter Policy Number: ");
        String id = sc.nextLine();
        System.out.print("Enter Policyholder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
        System.out.print("Enter Coverage Type (Health, Auto, Home): ");
        String type = sc.nextLine();
        System.out.print("Enter Premium Amount: ");
        double premium = sc.nextDouble();

        Policy policy = new Policy(id, name, date, type, premium);

        // Add to all sets to demonstrate capabilities
        boolean added = hashSet.add(policy);
        linkedHashSet.add(policy);
        treeSet.add(policy);

        if (added) {
            System.out.println("Policy added successfully.");
        } else {
            System.out.println("Duplicate Policy ID found. Policy not added (Sets enforce uniqueness).");
        }
    }

    private static void printPolicies(Set<Policy> policies) {
        if (policies.isEmpty()) {
            System.out.println("No policies found.");
        } else {
            for (Policy p : policies) {
                System.out.println(p);
            }
        }
    }

    private static void viewExpiringSoon() {
        System.out.println("Policies expiring within next 30 days:");
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        boolean found = false;

        // Using TreeSet is efficient as it is already sorted by date
        for (Policy p : treeSet) {
            if (p.getExpiryDate().isAfter(thirtyDaysLater)) break; // Optimization: Stop if date exceeds range
            if (!p.getExpiryDate().isBefore(today)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No policies expiring soon.");
    }

    private static void viewByCoverageType() {
        System.out.print("Enter Coverage Type to filter: ");
        String type = sc.nextLine();
        boolean found = false;
        for (Policy p : hashSet) {
            if (p.getCoverageType().equalsIgnoreCase(type)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No policies found for type: " + type);
    }

    private static void searchByPolicyNumber() {
        System.out.print("Enter Policy Number: ");
        String id = sc.nextLine();
        boolean found = false;
        for (Policy p : hashSet) {
            if (p.getPolicyNumber().equals(id)) {
                System.out.println(p);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Policy not found.");
    }

    private static void searchByPolicyholder() {
        System.out.print("Enter Policyholder Name: ");
        String name = sc.nextLine();
        boolean found = false;
        for (Policy p : hashSet) {
            if (p.getPolicyholderName().equalsIgnoreCase(name)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No policies found for: " + name);
    }

    private static void removeExpiredPolicies() {
        LocalDate today = LocalDate.now();
        hashSet.removeIf(p -> p.getExpiryDate().isBefore(today));
        linkedHashSet.removeIf(p -> p.getExpiryDate().isBefore(today));
        treeSet.removeIf(p -> p.getExpiryDate().isBefore(today));
        System.out.println("Expired policies removed (if any).");
    }

    private static void runPerformanceComparison() {
        int N = 100000;
        System.out.println("Comparing performance with " + N + " elements...");
        List<Policy> dummyPolicies = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            dummyPolicies.add(new Policy("ID" + i, "Name" + i, LocalDate.now().plusDays(i % 365), "Type" + (i % 3), 100.0 + i));
        }

        measurePerformance("HashSet", new HashSet<>(), dummyPolicies);
        measurePerformance("LinkedHashSet", new LinkedHashSet<>(), dummyPolicies);
        measurePerformance("TreeSet", new TreeSet<>(), dummyPolicies);
    }

    private static void measurePerformance(String setName, Set<Policy> set, List<Policy> data) {
        long start = System.nanoTime();
        for (Policy p : data) set.add(p);
        long end = System.nanoTime();
        System.out.printf("%-15s Add: %8.2f ms | ", setName, (end - start) / 1_000_000.0);

        start = System.nanoTime();
        for (int i = 0; i < data.size(); i += 10) set.contains(data.get(i)); // Search 10%
        end = System.nanoTime();
        System.out.printf("Search (10%%): %8.2f ms\n", (end - start) / 1_000_000.0);
    }
}