package engine.metrics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Tracks execution metrics for the engine.
 */
public class ExecutionMetrics {

    private final AtomicLong totalTasksSubmitted = new AtomicLong(0);
    private final AtomicLong totalTasksCompleted = new AtomicLong(0);
    private final AtomicLong totalTasksFailed = new AtomicLong(0);
    private final AtomicInteger activeWorkerThreads = new AtomicInteger(0);

    public void incrementSubmitted() {
        totalTasksSubmitted.incrementAndGet();
    }

    public void incrementCompleted() {
        totalTasksCompleted.incrementAndGet();
    }

    public void incrementFailed() {
        totalTasksFailed.incrementAndGet();
    }

    public void incrementActiveThreads() {
        activeWorkerThreads.incrementAndGet();
    }

    public void decrementActiveThreads() {
        activeWorkerThreads.decrementAndGet();
    }

    public long getTotalTasksSubmitted() {
        return totalTasksSubmitted.get();
    }

    public long getTotalTasksCompleted() {
        return totalTasksCompleted.get();
    }

    public long getTotalTasksFailed() {
        return totalTasksFailed.get();
    }

    public int getActiveWorkerThreads() {
        return activeWorkerThreads.get();
    }

    @Override
    public String toString() {
        return String.format(
            "Metrics [Submitted: %d, Completed: %d, Failed: %d, Active Threads: %d]",
            getTotalTasksSubmitted(),
            getTotalTasksCompleted(),
            getTotalTasksFailed(),
            getActiveWorkerThreads()
        );
    }
}
