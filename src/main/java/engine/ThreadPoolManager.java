package engine;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolManager {

    private List<WorkerThread> workers = new ArrayList<>();

    public ThreadPoolManager(int numThreads, TaskQueue queue) {

        for(int i=0;i<numThreads;i++) {
            WorkerThread worker = new WorkerThread(queue);
            workers.add(worker);
            worker.start();
        }
    }
}