package multithreading.work1;

import java.util.ArrayList;
import java.util.List;

public class MutexCounterDemo {

    public static void main(String[] args) {
        runRace(8, 100000, new UnsafeCounter());
        runRace(8, 100000, new SynchronizedCounter());
    }

    static void runRace(int threads, int itersPerThread, Counter counter) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < itersPerThread; j++) {
                    counter.inc();
                }
            }));
        }

        list.forEach(Thread::start);
        list.forEach(MutexCounterDemo::join);

        System.out.printf("Expected: %s, Actual: %s%n", threads * itersPerThread, counter.value());
    }

    static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

interface Counter {
    void inc();

    long value();
}

class UnsafeCounter implements Counter {

    private int counter = 0;

    @Override
    public void inc() {
        counter++;
    }

    @Override
    public long value() {
        return counter;
    }
}

class SynchronizedCounter implements Counter {

    private int counter = 0;

    @Override
    public synchronized void inc() {
        counter++;
    }

    @Override
    public long value() {
        return counter;
    }
}
