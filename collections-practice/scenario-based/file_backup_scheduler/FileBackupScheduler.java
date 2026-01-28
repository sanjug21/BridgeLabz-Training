import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FileBackupScheduler {

    private PriorityQueue<BackupTask> backupQueue;
    private int taskCounter;

    public FileBackupScheduler() {
        this.backupQueue = new PriorityQueue<>();
        this.taskCounter = 1000;
    }

    // Add a backup task
    public void addBackupTask(String folderPath, int priority, 
                              LocalDateTime scheduledTime, String description) 
                              throws InvalidBackupPathException {
        
        // Validate folder path
        if (folderPath == null || folderPath.trim().isEmpty()) {
            throw new InvalidBackupPathException("Folder path cannot be null or empty");
        }

        // Check if path exists (optional validation)
        if (!Files.exists(Paths.get(folderPath))) {
            throw new InvalidBackupPathException("Invalid path: " + folderPath + " does not exist");
        }

        // Validate priority (1-5)
        if (priority < 1 || priority > 5) {
            throw new InvalidBackupPathException("Priority must be between 1 and 5");
        }

        // Create and add backup task
        BackupTask task = new BackupTask(
                ++taskCounter,
                folderPath,
                priority,
                scheduledTime,
                description
        );

        backupQueue.offer(task);
        System.out.println("Backup task added: " + task.getTaskId() + 
                           " for " + folderPath + " (Priority: " + priority + ")");
    }

    // Execute next backup task (highest priority)
    public void executeNextBackup() {
        if (backupQueue.isEmpty()) {
            System.out.println("No backup tasks in queue");
            return;
        }

        BackupTask task = backupQueue.poll();
        System.out.println("\n=== Executing Backup ===");
        System.out.println("Task ID: " + task.getTaskId());
        System.out.println("Folder: " + task.getFolderPath());
        System.out.println("Priority: " + task.getPriority());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Scheduled Time: " + task.getScheduledTime());
        System.out.println("Status: Backup completed successfully!");
    }

    // Execute all backup tasks in priority order
    public void executeAllBackups() {
        if (backupQueue.isEmpty()) {
            System.out.println("No backup tasks in queue");
            return;
        }

        System.out.println("\n=== Executing All Backups in Priority Order ===");
        int count = 0;
        while (!backupQueue.isEmpty()) {
            BackupTask task = backupQueue.poll();
            count++;
            System.out.println("\n" + count + ". Executing: " + task.getFolderPath() + 
                               " (Priority: " + task.getPriority() + ")");
            System.out.println("   " + task.getDescription());
        }
        System.out.println("\nAll " + count + " backup tasks completed!");
    }

    // Show all pending backups
    public void showPendingBackups() {
        if (backupQueue.isEmpty()) {
            System.out.println("\nNo pending backup tasks");
            return;
        }

        System.out.println("\n=== Pending Backup Tasks (in priority order) ===");
        PriorityQueue<BackupTask> tempQueue = new PriorityQueue<>(backupQueue);
        int count = 0;
        while (!tempQueue.isEmpty()) {
            BackupTask task = tempQueue.poll();
            count++;
            System.out.println(count + ". " + task);
        }
    }

    // Get queue size
    public int getQueueSize() {
        return backupQueue.size();
    }

    // Main method with user input
    public static void main(String[] args) {
        FileBackupScheduler scheduler = new FileBackupScheduler();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== File Backup Scheduler ===\n");

        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Backup Task");
            System.out.println("2. Execute Next Backup (Highest Priority)");
            System.out.println("3. Execute All Backups");
            System.out.println("4. Show Pending Backups");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Backup Task
                    System.out.print("Enter folder path: ");
                    String folderPath = scanner.nextLine();
                    System.out.print("Enter priority (1-5, 5 is highest): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    try {
                        scheduler.addBackupTask(
                                folderPath,
                                priority,
                                LocalDateTime.now(),
                                description
                        );
                    } catch (InvalidBackupPathException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Execute Next Backup
                    scheduler.executeNextBackup();
                    break;

                case 3:
                    // Execute All Backups
                    scheduler.executeAllBackups();
                    break;

                case 4:
                    // Show Pending Backups
                    scheduler.showPendingBackups();
                    break;

                case 5:
                    // Exit
                    System.out.println("Thank you for using File Backup Scheduler!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
