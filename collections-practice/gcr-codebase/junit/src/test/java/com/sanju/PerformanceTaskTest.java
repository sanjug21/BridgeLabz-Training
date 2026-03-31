package com.sanju;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PerformanceTaskTest {

    private PerformanceTask performanceTask;

    @Before
    public void setUp() {
        performanceTask = new PerformanceTask();
    }

    @Test(timeout = 2000) // Fails if test takes more than 2 seconds
    public void testLongRunningTaskTimeout() throws InterruptedException {
        performanceTask.longRunningTask(); // This will fail because it takes 3 seconds
    }

    @Test(timeout = 2000) // Passes because test completes within 2 seconds
    public void testQuickTask() throws InterruptedException {
        String result = performanceTask.quickTask();
        assertEquals("Quick task completed", result);
    }
}
