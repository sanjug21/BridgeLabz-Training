import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DuplicateRemover {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input List
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        System.out.println("Enter " + n + " integers (including duplicates):");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        System.out.println("Original List: " + list);

        // 2. Remove Duplicates
        List<Integer> uniqueList = removeDuplicates(list);
        System.out.println("Unique List: " + uniqueList);

        sc.close();
    }

    
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        if (list == null) return result;

        Set<Integer> seen = new HashSet<>();
        for (Integer item : list) {
            if (seen.add(item)) {
                result.add(item);
            }
        }
        return result;
    }
}