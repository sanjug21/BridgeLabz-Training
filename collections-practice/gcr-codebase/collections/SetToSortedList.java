import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SetToSortedList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Set
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            set.add(sc.nextInt());
        }

        System.out.println("Original Set: " + set);

        // 2. Convert and Sort
        List<Integer> sortedList = convertToSortedList(set);
        System.out.println("Sorted List: " + sortedList);

        sc.close();
    }

    public static List<Integer> convertToSortedList(Set<Integer> set) {
        if (set == null) return new ArrayList<>();
        
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }
}