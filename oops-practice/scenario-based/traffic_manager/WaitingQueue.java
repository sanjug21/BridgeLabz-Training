package traffic_manager;

// Queue Implementation for vehicles waiting to enter
public class WaitingQueue {
    private Vehicle[] queue;
    private int front, rear, size, capacity;

    public WaitingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Vehicle[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Add vehicle to queue (Overflow handling)
    public void enqueue(Vehicle vehicle) {
        if (size == capacity) {
            System.out.println("Queue Overflow: Waiting line is full! Cannot add " + vehicle.getLicensePlate());
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = vehicle;
        size++;
        System.out.println(vehicle.getLicensePlate() + " joined the waiting queue.");
    }

    // Remove vehicle from queue (Underflow handling)
    public Vehicle dequeue() {
        if (size == 0) {
            System.out.println("Queue Underflow: No vehicles waiting.");
            return null;
        }
        Vehicle v = queue[front];
        front = (front + 1) % capacity;
        size--;
        return v;
    }

    public boolean isEmpty() { return size == 0; }
    
    public void displayQueue() {
        System.out.print("Waiting Queue: ");
        if (size == 0) {
            System.out.println("Empty");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % capacity].getLicensePlate() + " ");
        }
        System.out.println();
    }
}