package engine.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Priority-aware task queue.
 */
public class TaskQueue {

    private final BlockingQueue<TaskWrapper<?>> queue = new PriorityBlockingQueue<>();

    public void addTask(TaskWrapper<?> taskWrapper) {
        queue.offer(taskWrapper);
    }

    public TaskWrapper<?> getTask() throws InterruptedException {
        return queue.take();
    }

    public int size() {
        return queue.size();
    }
}
