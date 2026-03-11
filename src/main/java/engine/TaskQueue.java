package engine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {

    private BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    public void addTask(Task task) {
        queue.offer(task);
    }

    public Task getTask() throws InterruptedException {
        return queue.take();
    }
}