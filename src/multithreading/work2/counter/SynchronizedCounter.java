package multithreading.work2.counter;

public class SynchronizedCounter implements Counter{

    private long counter;

    @Override
    public synchronized void increment() {
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
