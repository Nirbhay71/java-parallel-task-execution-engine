package engine.demo;

import engine.core.ExecutionEngine;
import engine.core.Task;
import engine.core.TaskResult;
import engine.scheduler.Priority;

/**
 * Demonstrates the upgraded Parallel Task Execution Engine.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ExecutionEngine engine = new ExecutionEngine(3);

        System.out.println("--- Submitting Tasks with Priorities ---");

        // 1. High Priority Task
        TaskResult<String> highTask = engine.submitTask(new Task<String>() {
            @Override
            public String execute() throws Exception {
                Thread.sleep(1000);
                return "High Priority Success";
            }
            @Override
            public Priority getPriority() { return Priority.HIGH; }
        });

        // 2. Low Priority Task
        TaskResult<String> lowTask = engine.submitTask(new Task<String>() {
            @Override
            public String execute() throws Exception {
                Thread.sleep(1000);
                return "Low Priority Success";
            }
            @Override
            public Priority getPriority() { return Priority.LOW; }
        });

        // 3. Task with Retries
        TaskResult<String> retryTask = engine.submitTask(new Task<String>() {
            private int count = 0;
            @Override
            public String execute() throws Exception {
                count++;
                if (count < 3) {
                    throw new RuntimeException("Temporary failure");
                }
                return "Success after retries";
            }
            @Override
            public int getMaxRetries() { return 3; }
        });

        // 4. Task with Timeout (Simple demo - user should handle it in Task)
        TaskResult<String> timeoutTask = engine.submitTask(new Task<String>() {
            @Override
            public String execute() throws Exception {
                Thread.sleep(5000);
                return "Late success";
            }
            @Override
            public long getTimeoutMillis() { return 2000; }
        });

        System.out.println("High Priority Result: " + highTask.get());
        System.out.println("Low Priority Result: " + lowTask.get());
        System.out.println("Retry Task Result: " + retryTask.get());

        try {
            System.out.println("Waiting for Timeout Task (with 1s timeout on get)...");
            System.out.println("Timeout Task Result: " + timeoutTask.get(1000));
        } catch (TaskResult.TimeoutException e) {
            System.err.println("Timeout Task effectively timed out: " + e.getMessage());
        }

        Thread.sleep(2000);
        System.out.println("\nFinal Engine Metrics: " + engine.getMetrics());
        System.out.println("Current Queue Size: " + engine.getQueueSize());

        engine.shutdown();
    }
}
