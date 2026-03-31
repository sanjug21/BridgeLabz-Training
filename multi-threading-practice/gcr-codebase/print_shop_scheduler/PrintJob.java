package print_shop_scheduler;
class PrintJob implements Runnable {
    private String jobName;
    private int pages;
    private int priority;

    public PrintJob(String jobName, int pages, int priority) {
        this.jobName = jobName;
        this.pages = pages;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public void run() {
        String priorityLevel = getPriorityLevel();
        
        try {
            for (int page = 1; page <= pages; page++) {
                System.out.println("[" + priorityLevel + "] Printing " + jobName + " - Page " + page + " of " + pages);
                Thread.sleep(100);
            }
            System.out.println(jobName + " completed");
        } catch (InterruptedException e) {
            System.out.println(jobName + " interrupted");
        }
    }

    private String getPriorityLevel() {
        if (priority >= 7) {
            return "High Priority";
        } else if (priority >= 5) {
            return "Medium Priority";
        } else {
            return "Low Priority";
        }
    }
}
