import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class StackUsingQueues {

    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Elements to Push
        System.out.print("Enter number of elements to push onto stack: ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            push(val);
            System.out.println("Pushed: " + val);
        }

        // 2. Pop Elements
        System.out.println("\nPopping elements (LIFO order):");
        while (!isEmpty()) {
            System.out.println("Popped: " + pop());
        }

        sc.close();
    }

    // Push operation
    public static void push(int x) {
        // Add new element to q2
        q2.add(x);

        // Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }

        // Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Pop operation 
    public static int pop() {
        if (q1.isEmpty()) return -1;
        return q1.poll();
    }

    public static boolean isEmpty() {
        return q1.isEmpty();
    }
}