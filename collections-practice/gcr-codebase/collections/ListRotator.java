import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListRotator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input List
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        // 2. Input Rotation Count
        System.out.print("Enter number of positions to rotate left: ");
        int positions = sc.nextInt();

        System.out.println("Original List: " + list);

        // 3. Perform Rotation
        rotateList(list, positions);
        System.out.println("Rotated List: " + list);

        sc.close();
    }

    
    // rotate list by k postions
    public static void rotateList(List<Integer> list, int k) {
        if (list == null || list.size() <= 1 || k < 0) {
            return;
        }

        int n = list.size();
        k = k % n; // Handle cases where k > n
        if (k == 0) return;

        // Reversal Algorithm
        reverseSubList(list, 0, k - 1);
        reverseSubList(list, k, n - 1);
        reverseSubList(list, 0, n - 1);
    }

    private static void reverseSubList(List<Integer> list, int start, int end) {
        while (start < end) {
            Integer temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }
}