package multithreading.work2.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {

    private final Lock lock = new ReentrantLock();
    private long counter = 0;

    @Override
    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void incrementBy(long delta) {
        lock.lock();
        try {
            counter += delta;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getCount() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }
}
