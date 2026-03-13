package engine.core;

import engine.scheduler.Priority;

/**
 * Functional interface for a task that returns a result.
 */
@FunctionalInterface
public interface Task<T> {
    T execute() throws Exception;

    default Priority getPriority() {
        return Priority.MEDIUM;
    }

    default long getTimeoutMillis() {
        return 0; // 0 means no timeout
    }

    default int getMaxRetries() {
        return 0; // 0 means no retries
    }
}
