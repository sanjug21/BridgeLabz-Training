import java.time.LocalDateTime;

public class BackupTask implements Comparable<BackupTask> {

    private int taskId;
    private String folderPath;
    private int priority; // Higher number = higher priority (1-5)
    private LocalDateTime scheduledTime;
    private String description;

    public BackupTask(int taskId, String folderPath, int priority, 
                      LocalDateTime scheduledTime, String description) {
        this.taskId = taskId;
        this.folderPath = folderPath;
        this.priority = priority;
        this.scheduledTime = scheduledTime;
        this.description = description;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(BackupTask other) {
        // First compare by priority (higher priority first)
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority); // Descending order
        }
        // If priority is same, compare by scheduled time (earlier first)
        return this.scheduledTime.compareTo(other.scheduledTime);
    }

    @Override
    public String toString() {
        return "BackupTask{" +
                "taskId=" + taskId +
                ", folderPath='" + folderPath + '\'' +
                ", priority=" + priority +
                ", scheduledTime=" + scheduledTime +
                ", description='" + description + '\'' +
                '}';
    }
}
