
import java.util.*;

public class SlidingWindowMax{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input handling
        System.out.println("Enter the size of the array:");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter the size of the window:");
        int k = sc.nextInt();
        sc.close();

        // Use Deque to store indices
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that are out of the current window range
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.removeFirst();
            }

            // 2. Remove elements smaller than the current element from the back
            // (They will never be the maximum again)
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
                dq.removeLast();
            }

            // 3. Add current element's index
            dq.addLast(i);

            // 4. The front of the Deque is the largest element for the current window
            if (i >= k - 1) {
                System.out.print(arr[dq.peekFirst()] + " ");
            }
        }
    }
}