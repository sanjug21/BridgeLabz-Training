package thread_state_monitoring;
public class ThreadStateMonitoring {

    public static void main(String[] args) {
        TaskRunner task1 = new TaskRunner("Task-1");
        TaskRunner task2 = new TaskRunner("Task-2");

        Thread[] tasks = {task1, task2};
        StateMonitor monitor = new StateMonitor(tasks);

        System.out.println("Initial States:");
        for (Thread task : tasks) {
            System.out.println("[Monitor] " + task.getName() + " is in " + task.getState() + " state");
        }

        System.out.println("\nStarting monitoring...\n");
        
        monitor.start();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task1.start();
        task2.start();

        try {
            monitor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
