package multithreading.work2.counter;

public class UnsafeCounter implements Counter {

    private long counter;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public void incrementBy(long delta) {
        counter += delta;
    }

    @Override
    public long getCount() {
        return counter;
    }
}
