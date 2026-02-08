package thread_state_monitoring;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class StateMonitor extends Thread {
    private Thread[] tasksToMonitor;
    private Map<String, Integer> stateChangeCount;
    private DateTimeFormatter timeFormatter;

    public StateMonitor(Thread[] tasksToMonitor) {
        this.tasksToMonitor = tasksToMonitor;
        this.stateChangeCount = new HashMap<>();
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        for (Thread task : tasksToMonitor) {
            stateChangeCount.put(task.getName(), 0);
        }
    }

    @Override
    public void run() {
        boolean allTerminated = false;

        while (!allTerminated) {
            allTerminated = true;

            for (Thread task : tasksToMonitor) {
                Thread.State state = task.getState();
                String timestamp = LocalTime.now().format(timeFormatter);
                
                System.out.println("[Monitor] " + task.getName() + " is in " + state + " state at " + timestamp);
                
                stateChangeCount.put(task.getName(), stateChangeCount.get(task.getName()) + 1);

                if (state != Thread.State.TERMINATED) {
                    allTerminated = false;
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Monitor interrupted");
            }
        }

        System.out.println("\n=== Summary ===");
        for (Thread task : tasksToMonitor) {
            System.out.println("Summary: " + task.getName() + " went through " + stateChangeCount.get(task.getName()) + " state checks");
        }
    }
}
