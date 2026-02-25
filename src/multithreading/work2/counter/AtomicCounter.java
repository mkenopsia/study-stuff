package multithreading.work2.counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {

    private final AtomicLong counter = new AtomicLong();

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public void incrementBy(long delta) {
        counter.addAndGet(delta);
    }

    @Override
    public long getCount() {
        return counter.get();
    }
}
