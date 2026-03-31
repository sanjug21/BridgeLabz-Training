import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SymmetricDifference {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Set 1
        System.out.print("Enter the number of elements in Set 1: ");
        int n1 = sc.nextInt();
        Set<Integer> set1 = new HashSet<>();
        System.out.println("Enter " + n1 + " integers for Set 1:");
        for (int i = 0; i < n1; i++) {
            set1.add(sc.nextInt());
        }

        // 2. Input Set 2
        System.out.print("Enter the number of elements in Set 2: ");
        int n2 = sc.nextInt();
        Set<Integer> set2 = new HashSet<>();
        System.out.println("Enter " + n2 + " integers for Set 2:");
        for (int i = 0; i < n2; i++) {
            set2.add(sc.nextInt());
        }

        // 3. Find Symmetric Difference
        Set<Integer> diff = findSymmetricDifference(set1, set2);
        System.out.println("Symmetric Difference: " + diff);

        sc.close();
    }

    public static Set<Integer> findSymmetricDifference(Set<Integer> set1, Set<Integer> set2) {
        // Elements in Set1 but not Set2
        Set<Integer> diff1 = new HashSet<>(set1);
        diff1.removeAll(set2);

        // Elements in Set2 but not Set1
        Set<Integer> diff2 = new HashSet<>(set2);
        diff2.removeAll(set1);

        diff1.addAll(diff2);
        return diff1;
    }
}