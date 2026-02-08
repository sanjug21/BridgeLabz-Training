package thread_state_monitoring;
class TaskRunner extends Thread {
    
    public TaskRunner(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            System.out.println("[" + getName() + "] Starting task");
            Thread.sleep(2000);
            
            for (int i = 0; i < 1000000; i++) {
                Math.sqrt(i);
            }
            
            System.out.println("[" + getName() + "] Task completed");
        } catch (InterruptedException e) {
            System.out.println("[" + getName() + "] Task interrupted");
        }
    }
}
