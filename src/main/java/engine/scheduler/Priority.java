package engine.scheduler;

/**
 * Defines the priority levels for tasks.
 * Higher values (numerically smaller) have higher priority if using natural ordering,
 * but here we define HIGH as the most important.
 */
public enum Priority {
    LOW(3),
    MEDIUM(2),
    HIGH(1);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
