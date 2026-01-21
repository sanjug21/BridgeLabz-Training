import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SubsetChecker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Set 1 (Potential Subset)
        System.out.print("Enter the number of elements in Set 1 (Potential Subset): ");
        int n1 = sc.nextInt();
        Set<Integer> set1 = new HashSet<>();
        System.out.println("Enter " + n1 + " integers for Set 1:");
        for (int i = 0; i < n1; i++) {
            set1.add(sc.nextInt());
        }

        // 2. Input Set 2 (Super Set)
        System.out.print("Enter the number of elements in Set 2: ");
        int n2 = sc.nextInt();
        Set<Integer> set2 = new HashSet<>();
        System.out.println("Enter " + n2 + " integers for Set 2:");
        for (int i = 0; i < n2; i++) {
            set2.add(sc.nextInt());
        }

        // 3. Check Subset
        boolean isSubset = checkSubset(set1, set2);
        System.out.println("Is Set 1 a subset of Set 2? " + isSubset);

        sc.close();
    }

    public static boolean checkSubset(Set<Integer> subSet, Set<Integer> superSet) {
        if (subSet == null || superSet == null) {
            return false;
        }
        return superSet.containsAll(subSet);
    }
}