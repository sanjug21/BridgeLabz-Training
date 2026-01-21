import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SetOperations {

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

        // 3. Perform Operations
        System.out.println("Union: " + findUnion(set1, set2));
        System.out.println("Intersection: " + findIntersection(set1, set2));

        sc.close();
    }

    public static Set<Integer> findUnion(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> union = new HashSet<>(set1);
        if (set2 != null) {
            union.addAll(set2);
        }
        return union;
    }

    public static Set<Integer> findIntersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> intersection = new HashSet<>(set1);
        if (set2 != null) {
            intersection.retainAll(set2);
        }
        return intersection;
    }
}