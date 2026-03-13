package engine.core;

/**
 * Represents the result of an asynchronous task execution.
 */
public class TaskResult<T> {

    private T result;
    private Throwable exception;
    private volatile State state = State.PENDING;

    public enum State {
        PENDING,
        RUNNING,
        COMPLETED,
        FAILED,
        CANCELLED
    }

    public synchronized void setRunning() {
        if (state == State.PENDING) {
            state = State.RUNNING;
        }
    }

    public synchronized void setResult(T result) {
        if (state == State.CANCELLED) return;
        this.result = result;
        this.state = State.COMPLETED;
        notifyAll();
    }

    public synchronized void setException(Throwable exception) {
        if (state == State.CANCELLED) return;
        this.exception = exception;
        this.state = State.FAILED;
        notifyAll();
    }

    public synchronized void cancel() {
        if (state == State.PENDING || state == State.RUNNING) {
            this.state = State.CANCELLED;
            notifyAll();
        }
    }

    public synchronized T get() throws InterruptedException, ExecutionException {
        while (state == State.PENDING || state == State.RUNNING) {
            wait();
        }
        if (state == State.CANCELLED) {
            throw new InterruptedException("Task was cancelled");
        }
        if (state == State.FAILED) {
            throw new ExecutionException(exception);
        }
        return result;
    }

    public synchronized T get(long timeoutMillis) throws InterruptedException, ExecutionException, TimeoutException {
        long startTime = System.currentTimeMillis();
        long remaining = timeoutMillis;

        while ((state == State.PENDING || state == State.RUNNING) && remaining > 0) {
            wait(remaining);
            remaining = timeoutMillis - (System.currentTimeMillis() - startTime);
        }

        if (state == State.PENDING || state == State.RUNNING) {
            throw new TimeoutException("Task timed out");
        }
        return get();
    }

    public State getState() {
        return state;
    }

    public boolean isDone() {
        return state == State.COMPLETED || state == State.FAILED || state == State.CANCELLED;
    }

    public static class ExecutionException extends Exception {
        public ExecutionException(Throwable cause) {
            super(cause);
        }
    }

    public static class TimeoutException extends Exception {
        public TimeoutException(String message) {
            super(message);
        }
    }
}
