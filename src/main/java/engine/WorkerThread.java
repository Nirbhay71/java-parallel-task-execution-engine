package engine;

public class WorkerThread extends Thread {

    private TaskQueue taskQueue;

    public WorkerThread(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {

        while (true) {
            try {
                Task task = taskQueue.getTask();
                task.execute();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}