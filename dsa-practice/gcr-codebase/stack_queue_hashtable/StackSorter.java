


import java.util.Scanner;
import java.util.Stack;

public class StackSorter {

    // Recursive method to sort the stack
    public static void sortStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            // Hold the top element in the call stack
            int temp = stack.pop();
            
            // Sort the rest of the stack
            sortStack(stack);
            
            // Insert the held element back in the correct sorted position
            sortedInsert(stack, temp);
        }
    }

    // Helper method to insert an element into a sorted stack
    private static void sortedInsert(Stack<Integer> stack, int element) {
        // Base case: stack is empty or element is greater than top (for ascending order)
        if (stack.isEmpty() || element > stack.peek()) {
            stack.push(element);
            return;
        }

        // If element is smaller, pop top and recurse
        int temp = stack.pop();
        sortedInsert(stack, element);

        // Push the popped element back
        stack.push(temp);
    }
    

    public static void main(String[] args) {
        System.out.println("=== Sort Stack Using Recursion ===");
        Stack<Integer> stack = new Stack<>();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the elements of the stack you want to sort:");
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.println("Enter element "+(i+1)+":");
            stack.push(sc.nextInt());
        }

        System.out.println("Original Stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted Stack:   " + stack);
        sc.close();
    }
}