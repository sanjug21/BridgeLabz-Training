import java.util.*;

class Task {
    String taskId;
    String taskName;
    int priority;
    String dueDate;

    Task(String taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }

}

public class TaskScheduler {
    static Scanner sc = new Scanner(System.in);
    Node<Task> head;
    Node<Task> tail;
    Node<Task> current; // To track current task view

    public void addTask(Task task, int position) {
        Node<Task> nn = new Node<>(task);
        if (head == null) {
            head = nn;
            tail = nn;
            nn.next = head; // Circular
            current = head;
            return;
        }

        // Add at beginning
        if (position == 1) {
            nn.next = head;
            head = nn;
            tail.next = head; // Update circular link
            return;
        }

        // Traverse
        Node<Task> temp = head;
        int count = 1;
        // Loop until end of list (tail) or position reached
        while (temp != tail && count < position - 1) {
            temp = temp.next;
            count++;
        }

        // Add at end or specific position
        if (temp == tail) {
            // Add at end
            tail.next = nn;
            tail = nn;
            tail.next = head; // Maintain circle
        } else {
            // Add in between
            nn.next = temp.next;
            temp.next = nn;
        }
    }

    public void removeTask(String taskId) {
        if (head == null) {
            System.out.println("No tasks to remove.");
            return;
        }

        Node<Task> temp = head;
        Node<Task> prev = tail; // Start prev at tail for circular traversal logic

        do {
            if (temp.data.taskId.equals(taskId)) {
                if (temp == head && temp == tail) { // Only one node
                    head = null;
                    tail = null;
                    current = null;
                } else if (temp == head) { // Remove head
                    head = head.next;
                    tail.next = head;
                    if (current == temp)
                        current = head;
                } else if (temp == tail) { // Remove tail
                    tail = prev;
                    tail.next = head;
                    if (current == temp)
                        current = head;
                } else { // Remove middle
                    prev.next = temp.next;
                    if (current == temp)
                        current = temp.next;
                }
                System.out.println("Task " + taskId + " removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task with ID " + taskId + " not found.");
    }

    public void viewCurrentAndMove() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task:");
        System.out.printf("ID: %s | Name: %s | Priority: %d | Due: %s%n",
                current.data.taskId, current.data.taskName, current.data.priority, current.data.dueDate);

        current = current.next;
        System.out.println("Moved to next task.");
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        Node<Task> temp = head;
        System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Name", "Priority", "Due Date");
        System.out.println("-----------------------------------------------------------");
        do {
            System.out.printf("%-10s %-20s %-10d %-15s%n",
                    temp.data.taskId, temp.data.taskName, temp.data.priority, temp.data.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        Node<Task> temp = head;
        boolean found = false;
        System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Name", "Priority", "Due Date");
        do {
            if (temp.data.priority == priority) {
                System.out.printf("%-10s %-20s %-10d %-15s%n",
                        temp.data.taskId, temp.data.taskName, temp.data.priority, temp.data.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No tasks found with priority " + priority);
    }

    public void addTaskInput() {
        try {
            System.out.println("Enter Task Details:");
            System.out.print("Task ID: ");
            String id = sc.nextLine();
            if (id.trim().isEmpty())
                throw new InvalidInputException("ID cannot be empty.");

            System.out.print("Task Name: ");
            String name = sc.nextLine();
            if (name.trim().isEmpty())
                throw new InvalidInputException("Name cannot be empty.");

            System.out.print("Priority (1-10): ");
            int priority = sc.nextInt();
            sc.nextLine(); // consume newline
            if (priority < 1 || priority > 10)
                throw new InvalidInputException("Invalid priority.");

            System.out.print("Due Date: ");
            String date = sc.nextLine();

            System.out.println("Where to add? (1. Start, 2. End, 3. Specific Position)");
            int choice = sc.nextInt();
            int pos = -1;
            if (choice == 1)
                pos = 1;
            else if (choice == 2)
                pos = Integer.MAX_VALUE;
            else if (choice == 3) {
                System.out.print("Enter position: ");
                pos = sc.nextInt();
            } else
                throw new InvalidInputException("Invalid position choice.");
            sc.nextLine();

            addTask(new Task(id, name, priority, date), pos);
            System.out.println("Task added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        System.out.println("==== Task Scheduler (Circular List) ====");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Current Task & Move Next");
            System.out.println("4. Display All Tasks");
            System.out.println("5. Search by Priority");
            System.out.println("6. Exit");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
            }
            sc.nextLine();

            switch (choice) {
                case 1:
                    scheduler.addTaskInput();
                    break;
                case 2:
                    System.out.print("Enter Task ID to remove: ");
                    scheduler.removeTask(sc.nextLine());
                    break;
                case 3:
                    scheduler.viewCurrentAndMove();
                    break;
                case 4:
                    scheduler.displayAllTasks();
                    break;
                case 5:
                    System.out.print("Enter Priority to search: ");
                    try {
                        int p = sc.nextInt();
                        scheduler.searchTaskByPriority(p);
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                        sc.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
