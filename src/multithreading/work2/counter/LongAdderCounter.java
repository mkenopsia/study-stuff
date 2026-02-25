package multithreading.work2.counter;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderCounter implements Counter {

    private final LongAdder counter = new LongAdder();

    @Override
    public void increment() {
        counter.increment();
    }

    @Override
    public void incrementBy(long delta) {
        counter.add(delta);
    }

    @Override
    public long getCount() {
        return counter.longValue();
    }
}
