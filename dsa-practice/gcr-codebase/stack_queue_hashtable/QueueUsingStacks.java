


import java.util.Scanner;
import java.util.Stack;

public class QueueUsingStacks {
    private Stack<Integer> inputStack;
    private Stack<Integer> outputStack;

    public QueueUsingStacks() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    // Enqueue: Always push to input stack
    public void enqueue(int data) {
        inputStack.push(data);
        System.out.println("Enqueued: " + data);
    }

    // Dequeue: Pop from output stack. If empty, move everything from input stack first.
    public int dequeue() {
        if (outputStack.isEmpty()) {
            if (inputStack.isEmpty()) {
                System.out.println("Queue Underflow");
                return -1;
            }
            // Transfer elements
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }

    public boolean isEmpty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("=== Queue Using Two Stacks ===");
        QueueUsingStacks q = new QueueUsingStacks();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("Enter your choice:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Exit");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    q.enqueue(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Dequeued: " + q.dequeue());
                    break;
                case 3:
                    System.exit(0);
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();

        }
        
    }
}