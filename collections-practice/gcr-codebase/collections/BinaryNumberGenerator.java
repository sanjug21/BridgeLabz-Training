import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryNumberGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input N
        System.out.print("Enter N (number of binary numbers to generate): ");
        int n = sc.nextInt();

        // 2. Generate and Print
        System.out.println("Binary numbers from 1 to " + n + ":");
        generateBinaryNumbers(n);

        sc.close();
    }

    public static void generateBinaryNumbers(int n) {
        if (n <= 0) return;
        
        Queue<String> queue = new LinkedList<>();
        queue.add("1");

        for (int i = 0; i < n; i++) {
            String current = queue.poll();
            System.out.print(current + " ");
            queue.add(current + "0");
            queue.add(current + "1");
        }
        System.out.println();
    }
}