import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueReverser {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Queue Size
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }

        System.out.println("Original Queue: " + queue);

        // 2. Reverse Queue
        reverseQueue(queue);
        System.out.println("Reversed Queue: " + queue);

        sc.close();
    }

    // Reverses the queue using recursion (implicitly using stack memory)
    public static void reverseQueue(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }
        int temp = queue.poll();
        reverseQueue(queue);
        queue.add(temp);
    }
}