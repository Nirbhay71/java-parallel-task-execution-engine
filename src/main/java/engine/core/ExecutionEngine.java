package engine.core;

import engine.metrics.ExecutionMetrics;
import engine.scheduler.TaskQueue;
import engine.scheduler.TaskWrapper;

/**
 * Entry point for submitting tasks to the parallel execution engine.
 */
public class ExecutionEngine {

    private final TaskQueue queue = new TaskQueue();
    private final ThreadPoolManager pool;
    private final ExecutionMetrics metrics = new ExecutionMetrics();

    public ExecutionEngine(int threads) {
        this.pool = new ThreadPoolManager(threads, queue, metrics);
    }

    public <T> TaskResult<T> submitTask(Task<T> task) {
        metrics.incrementSubmitted();
        TaskResult<T> result = new TaskResult<>();
        TaskWrapper<T> wrapper = new TaskWrapper<>(task, result);
        
        queue.addTask(wrapper);
        return result;
    }

    public ExecutionMetrics getMetrics() {
        return metrics;
    }

    public int getQueueSize() {
        return queue.size();
    }

    public void shutdown() {
        pool.shutdown();
    }
}
