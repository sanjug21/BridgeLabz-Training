package traffic_manager;

import java.util.LinkedList;
import java.util.Queue;

// Queue implementation for vehicles waiting to enter
public class WaitingQueue {
    private Queue<Vehicle> queue;
    private final int capacity;

    public WaitingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public void add(String id) {
        if (queue.size() >= capacity) {
            System.out.println("Queue Overflow! Cannot add Vehicle-" + id);
            return;
        }
        Vehicle v = new Vehicle(id);
        queue.add(v);
        System.out.println(v + " joined the waiting queue.");
    }

    public Vehicle remove() {
        if (queue.isEmpty()) {
            System.out.println("Queue Underflow! No vehicles waiting.");
            return null;
        }
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void display() {
        System.out.print("Waiting Queue State: ");
        if (queue.isEmpty()) {
            System.out.println("[Empty]");
        } else {
            System.out.println(queue);
        }
    }
}