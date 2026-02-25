package multithreading.work2.counter;

public interface Counter {
    void increment();

    void incrementBy(long delta);

    long getCount();
}
