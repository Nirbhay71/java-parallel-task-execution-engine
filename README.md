# Java Parallel Task Execution Engine 🚀

A robust, multi-threaded parallel task execution engine built in pure Java. This project demonstrates advanced concurrency patterns, thread pool management, and efficient task scheduling without relying on external frameworks.

## 🌟 Key Features

- **Custom Thread Pool Management**: Efficiently manages a pool of worker threads to execute tasks concurrently.
- **Thread-safe Task Queue**: Utilizes blocking queues for safe, concurrent producers and consumers.
- **Graceful Shutdown**: Ensures all running and queued tasks are completed before the engine shuts down.
- **Task Interface**: Extensible `Task` interface allowing easy integration of custom execution logic.
- **Zero Dependencies**: Built entirely using Java's standard `java.util.concurrent` capabilities.

## 🏗️ Architecture Design

The engine is structured around several core components:

*   **`ExecutionEngine`**: The main orchestrator that clients interact with to submit tasks and manage the engine lifecycle.
*   **`ThreadPoolManager`**: Handles the creation, allocation, and monitoring of `WorkerThread` instances.
*   **`TaskQueue`**: A thread-safe queue (typically backed by `BlockingQueue`) that stores pending tasks waiting for an available thread.
*   **`WorkerThread`**: Continuously polls the `TaskQueue` and executes the retrieved `Task` instances.
*   **`Task`**: The interface representing a unit of work to be executed asynchronously.

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher.

### Installation

Clone the repository:
```bash
git clone https://github.com/Nirbhay71/java-parallel-task-execution-engine.git
cd java-parallel-task-execution-engine
```

### Usage Example

```java
import engine.ExecutionEngine;
import engine.Task;

public class Main {
    public static void main(String[] args) {
        // Initialize engine with a pool of 5 worker threads
        ExecutionEngine engine = new ExecutionEngine(5);

        // Submit tasks for parallel execution
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            engine.submitTask(new Task() {
                @Override
                public void execute() {
                    System.out.println("Processing task " + taskId + " on " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000); // Simulate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        // Gracefully shut down the engine
        engine.shutdown();
    }
}
```

## 🛠️ Lessons Learned & Technical Growth

Building this project reinforced several core software engineering principles:
- **Concurrency Control**: Managing race conditions and thread synchronization out-of-the-box.
- **Producer-Consumer Pattern**: Implementing robust inter-thread communication logic.
- **Resource Management**: Properly allocating and cleaning up system threads to prevent memory leaks and thread starvation.

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## 📝 License

This project is licensed under the MIT License.
