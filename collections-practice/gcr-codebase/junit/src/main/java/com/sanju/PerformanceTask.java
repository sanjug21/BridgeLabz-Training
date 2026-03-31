package com.sanju;

public class PerformanceTask {

    public String longRunningTask() throws InterruptedException {
        Thread.sleep(3000); // Sleep for 3 seconds
        return "Task completed";
    }

    public String quickTask() throws InterruptedException {
        Thread.sleep(1000); // Sleep for 1 second
        return "Quick task completed";
    }
}
