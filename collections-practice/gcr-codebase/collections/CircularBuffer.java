import java.util.Scanner;

public class CircularBuffer {

    private int[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Initialize Buffer
        System.out.print("Enter buffer capacity: ");
        int capacity = sc.nextInt();
        CircularBuffer cb = new CircularBuffer(capacity);

        // 2. Perform Operations
        System.out.print("Enter number of elements to add: ");
        int ops = sc.nextInt();
        System.out.println("Enter " + ops + " integers:");

        for (int i = 0; i < ops; i++) {
            int val = sc.nextInt();
            cb.add(val);
            cb.printBuffer();
        }

        sc.close();
    }

    public void add(int element) {
        if (size == capacity) {
            // Buffer full, overwrite head (oldest element)
            head = (head + 1) % capacity;
            size--; 
        }
        buffer[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    public void printBuffer() {
        System.out.print("Buffer State: [");
        if (size > 0) {
            int count = 0;
            int current = head;
            while (count < size) {
                System.out.print(buffer[current]);
                if (count < size - 1) System.out.print(", ");
                current = (current + 1) % capacity;
                count++;
            }
        }
        System.out.println("]");
    }
}