package engine;

public class ExecutionEngine {

    private TaskQueue queue = new TaskQueue();
    private ThreadPoolManager pool;

    public ExecutionEngine(int threads) {
        pool = new ThreadPoolManager(threads, queue);
    }

    public void submitTask(Task task) {
        queue.addTask(task);
    }
}