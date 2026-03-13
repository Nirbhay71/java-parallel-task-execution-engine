package engine.scheduler;

import engine.core.Task;
import engine.core.TaskResult;

/**
 * Internal wrapper to handle task execution metadata and priority comparison.
 */
public class TaskWrapper<T> implements Comparable<TaskWrapper<?>> {

    private final Task<T> task;
    private final TaskResult<T> result;
    private final long submissionTime;
    private int attemptCount = 0;

    public TaskWrapper(Task<T> task, TaskResult<T> result) {
        this.task = task;
        this.result = result;
        this.submissionTime = System.nanoTime();
    }

    public Task<T> getTask() {
        return task;
    }

    public TaskResult<T> getResult() {
        return result;
    }

    public int incrementAndGetAttempt() {
        return ++attemptCount;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    @Override
    public int compareTo(TaskWrapper<?> other) {
        // Higher Priority enum level means higher priority (HIGH is 1, LOW is 3)
        // So we compare by level ascending.
        int priorityCompare = Integer.compare(this.task.getPriority().getLevel(), other.task.getPriority().getLevel());
        if (priorityCompare != 0) {
            return priorityCompare;
        }
        // If priorities are same, use FIFO based on submission time
        return Long.compare(this.submissionTime, other.submissionTime);
    }
}
