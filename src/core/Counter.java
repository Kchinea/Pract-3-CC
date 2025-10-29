package pract3.core;

/**
 * Simple mutable counter used instead of AtomicInteger when concurrency is not
 * required. Provides increment and get operations.
 */
public class Counter {
    private int value;
    public Counter() { this.value = 0; }
    public Counter(int start) { this.value = start; }
    /** Increment and return the new value. */
    public int incrementAndGet() { return ++value; }
    /** Return the current value. */
    public int get() { return value; }
    @Override
    public String toString() { return Integer.toString(value); }
}
