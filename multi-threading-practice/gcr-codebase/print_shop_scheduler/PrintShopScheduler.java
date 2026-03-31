package print_shop_scheduler;

public class PrintShopScheduler {

    public static void main(String[] args) {
        System.out.println("Starting print jobs...\n");

        PrintJob job1 = new PrintJob("Job1", 10, 5);
        PrintJob job2 = new PrintJob("Job2", 5, 8);
        PrintJob job3 = new PrintJob("Job3", 15, 3);
        PrintJob job4 = new PrintJob("Job4", 8, 6);
        PrintJob job5 = new PrintJob("Job5", 12, 7);

        Thread t1 = new Thread(job1, "Job1");
        Thread t2 = new Thread(job2, "Job2");
        Thread t3 = new Thread(job3, "Job3");
        Thread t4 = new Thread(job4, "Job4");
        Thread t5 = new Thread(job5, "Job5");

        t1.setPriority(mapPriorityToThread(job1.getPriority()));
        t2.setPriority(mapPriorityToThread(job2.getPriority()));
        t3.setPriority(mapPriorityToThread(job3.getPriority()));
        t4.setPriority(mapPriorityToThread(job4.getPriority()));
        t5.setPriority(mapPriorityToThread(job5.getPriority()));

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("\nAll jobs completed in " + totalTime + "ms");
    }

    private static int mapPriorityToThread(int jobPriority) {
        if (jobPriority >= 7) {
            return Thread.MAX_PRIORITY;
        } else if (jobPriority >= 5) {
            return Thread.NORM_PRIORITY;
        } else {
            return Thread.MIN_PRIORITY;
        }
    }
}
