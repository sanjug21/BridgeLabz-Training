import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class NthElementFinder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input LinkedList
        System.out.print("Enter the number of elements: ");
        int size = sc.nextInt();

        LinkedList<String> linkedList = new LinkedList<>();
        System.out.println("Enter " + size + " strings:");
        for (int i = 0; i < size; i++) {
            linkedList.add(sc.next());
        }

        // 2. Input N
        System.out.print("Enter N (position from end): ");
        int n = sc.nextInt();

        // 3. Find Element
        try {
            String result = getNthFromEnd(linkedList, n);
            System.out.println("Element at " + n + " from end: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    
    public static String getNthFromEnd(LinkedList<String> list, int n) {
        if (list == null || n <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        Iterator<String> fast = list.iterator();
        Iterator<String> slow = list.iterator();

        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast.hasNext()) {
                fast.next();
            } else {
                throw new IllegalArgumentException("List is smaller than N");
            }
        }

        // Move both pointers until fast reaches the end
        while (fast.hasNext()) {
            fast.next();
            slow.next();
        }

        return slow.next();
    }
}