package background_job_execution;

import java.util.ArrayList;
import java.util.List;

public class BackgroundJobExecution {

    public static void executeJob(Job job, Runnable task) {
        System.out.println("Starting Job: " + job.getJobName());
        task.run();
        System.out.println("Completed Job: " + job.getJobName() + "\n");
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("Database Backup", 5000));
        jobs.add(new Job("Email Notification", 2000));
        jobs.add(new Job("Report Generation", 3000));
        jobs.add(new Job("Cache Cleanup", 1000));
        jobs.add(new Job("Log Analysis", 4000));

        System.out.println("Background Job Execution System");
        System.out.println("================================\n");

        for (Job job : jobs) {
            Runnable task = () -> {
                System.out.println("Executing: " + job.getJobName());
                try {
                    Thread.sleep(job.getDuration());
                    System.out.println("Duration: " + job.getDuration() + "ms");
                } catch (InterruptedException e) {
                    System.out.println("Job interrupted: " + job.getJobName());
                }
            };
            executeJob(job, task);
        }

        System.out.println("Running Jobs Asynchronously:");
        System.out.println("============================\n");

        List<Thread> threads = new ArrayList<>();
        for (Job job : jobs) {
            Runnable asyncTask = () -> {
                System.out.println("ASYNC - Starting: " + job.getJobName());
                try {
                    Thread.sleep(job.getDuration());
                    System.out.println("ASYNC - Completed: " + job.getJobName() + " (" + job.getDuration() + "ms)");
                } catch (InterruptedException e) {
                    System.out.println("ASYNC - Interrupted: " + job.getJobName());
                }
            };
            
            Thread thread = new Thread(asyncTask);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                // join to wait for all threads to finish before printing the final message
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nAll background jobs completed!");
    }
}
