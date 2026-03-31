import java.util.*;
public class ImplementQueueUsingStacks {
    // 232. Implement Queue using Stacks
    // https://leetcode.com/problems/implement-queue-using-stacks/

    public static void main(String[] args) {
        MyQueue obj=new MyQueue();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.peek());
        
        
    }
   
    static class MyQueue {

        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;

        public MyQueue() {
            inputStack=new Stack<>();
            outputStack=new Stack<>();

        }

        public void push(int x) {
            inputStack.push(x);

        }

        public int pop() {
            if(outputStack.isEmpty()){
                while(!inputStack.isEmpty()){
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.pop();

        }

        public int peek() {
            if(outputStack.isEmpty()){
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }

        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();

        }
    }

   




}