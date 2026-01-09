import java.util.*;

class Process {
    String processId;
    int burstTime;
    int remainingTime;
    int priority;
    int completionTime;
    int waitingTime;
    int turnAroundTime;

    Process(String processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}



public class RoundRobinScheduling {
    static Scanner sc = new Scanner(System.in);
    Node<Process> head = null;
    Node<Process> tail = null;

    // Add a process at the end of the circular list
    public void addProcess(Process process) {
        Node<Process> nn = new Node<Process>(process);
        if (head == null) {
            head = nn;
            tail = nn;
            nn.next = head;
        } else {
            tail.next = nn;
            tail = nn;
            tail.next = head;
        }
        System.out.println("Process " + process.processId + " added successfully.");
    }

    // Remove a process by ID (Manual removal)
    public void removeProcess(String processId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node<Process> current = head;
        Node<Process> prev = tail;

        do {
            if (current.data.processId.equals(processId)) {
                if (current == head && current == tail) { // Only one node
                    head = null;
                    tail = null;
                } else {
                    if (current == head) {
                        head = head.next;
                        tail.next = head;
                    } else if (current == tail) {
                        tail = prev;
                        tail.next = head;
                    } else {
                        prev.next = current.next;
                    }
                }
                System.out.println("Process " + processId + " removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Process " + processId + " not found.");
    }

    // Helper to remove the current head node (used during simulation)
    private void removeHead() {
        if (head == null) return;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in queue.");
            return;
        }
        Node<Process> temp = head;
        System.out.printf("%-10s %-10s %-10s%n", "ID", "Burst", "Remaining");
        System.out.println("--------------------------------");
        do {
            System.out.printf("%-10s %-10d %-10d%n",
                    temp.data.processId, temp.data.burstTime, temp.data.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void simulateRoundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        System.out.println("\nStarting Round Robin Simulation (Quantum: " + timeQuantum + ")");
        int currentTime = 0;
        List<Process> completedProcesses = new ArrayList<>();
        int round = 1;

        while (head != null) {
            System.out.println("\n--- Round " + round + " ---");
            // We need to iterate through the current size of the list
            // Since the list changes (nodes removed), we count nodes first or be careful with pointers.
            // Strategy: Process the head. If finished, remove it. If not, rotate head.
            // We do this for the number of processes that were present at the start of the round.
            
            int count = 0;
            Node<Process> temp = head;
            if (temp != null) {
                do {
                    count++;
                    temp = temp.next;
                } while (temp != head);
            }

            for (int i = 0; i < count; i++) {
                if (head == null) break;

                Process p = head.data;
                int timeSpent = Math.min(timeQuantum, p.remainingTime);
                p.remainingTime -= timeSpent;
                currentTime += timeSpent;

                if (p.remainingTime == 0) {
                    p.completionTime = currentTime;
                    p.turnAroundTime = p.completionTime; // Assuming Arrival Time = 0
                    p.waitingTime = p.turnAroundTime - p.burstTime;
                    completedProcesses.add(p);
                    System.out.println("Process " + p.processId + " completed execution.");
                    removeHead(); // Removes head and updates head to next
                } else {
                    // Rotate head to next process
                    head = head.next;
                    tail = tail.next; // Tail must also move to keep circular structure correct relative to head
                }
            }
            
            displayProcesses();
            round++;
        }

        System.out.println("\nSimulation Completed.");
        calculateAndDisplayStats(completedProcesses);
    }

    private void calculateAndDisplayStats(List<Process> processes) {
        double totalWt = 0;
        double totalTat = 0;
        System.out.println("\nFinal Statistics:");
        System.out.printf("%-10s %-10s %-10s %-10s%n", "ID", "Burst", "Wait", "TAT");
        System.out.println("-------------------------------------------");
        for (Process p : processes) {
            totalWt += p.waitingTime;
            totalTat += p.turnAroundTime;
            System.out.printf("%-10s %-10d %-10d %-10d%n", p.processId, p.burstTime, p.waitingTime, p.turnAroundTime);
        }
        System.out.println("-------------------------------------------");
        System.out.printf("Average Waiting Time: %.2f%n", (processes.isEmpty() ? 0 : totalWt / processes.size()));
        System.out.printf("Average Turn-Around Time: %.2f%n", (processes.isEmpty() ? 0 : totalTat / processes.size()));
    }

    public static void main(String[] args) {
        RoundRobinScheduling rrs = new RoundRobinScheduling();
        System.out.println("==== Round Robin Scheduling (Circular Linked List) ====");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Process");
            System.out.println("2. Remove Process");
            System.out.println("3. Simulate Round Robin");
            System.out.println("4. Display Processes");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Process ID: ");
                    String id = sc.nextLine();
                    System.out.println("Enter Burst Time: ");
                    int burst = sc.nextInt();
                    System.out.println("Enter Priority: ");
                    int prio = sc.nextInt();
                    sc.nextLine();
                    rrs.addProcess(new Process(id, burst, prio));
                    break;
                case 2:
                    System.out.println("Enter Process ID to remove: ");
                    String rid = sc.nextLine();
                    rrs.removeProcess(rid);
                    break;
                case 3:
                    System.out.println("Enter Time Quantum: ");
                    int tq = sc.nextInt();
                    sc.nextLine();
                    rrs.simulateRoundRobin(tq);
                    break;
                case 4:
                    rrs.displayProcesses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}