package multithreading.work2.counter;

import multithreading.ThreadUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CounterLoad {

    static class Result {
        String name;
        long result;

        public Result(String name, long result) {
            this.name = name;
            this.result = result;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        final int threadsCount = 32;
        final int iterationsPerThread = 1_250_000;

        long reentrantRes = runLoad(new ReentrantLockCounter(), threadsCount, iterationsPerThread);
        long longAdderRes = runLoad(new LongAdderCounter(), threadsCount, iterationsPerThread);
        long synchronizedRes = runLoad(new SynchronizedCounter(), threadsCount, iterationsPerThread);
        long atomicRes = runLoad(new AtomicCounter(), threadsCount, iterationsPerThread);
        long unsafeRes = runLoad(new UnsafeCounter(), threadsCount, iterationsPerThread);

        var results = new ArrayList<>(List.of(
                new Result("UNSAFE", unsafeRes),
                new Result("ATOMIC", atomicRes),
                new Result("SYNCHRONIZED", synchronizedRes),
                new Result("REENTRANT", reentrantRes),
                new Result("LONG_ADDER", longAdderRes)
        ));

        results.sort(Comparator.comparingLong((a) -> a.result));
        results.forEach(System.out::println);
    }

    public static long runLoad(final Counter counter,
                               final int threadsCount,
                               final int iterationsPerThread) {
        final Thread[] threads = new Thread[threadsCount];
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for(int j = 0; j < iterationsPerThread; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        final long start = System.nanoTime();
        countDownLatch.countDown();
        ThreadUtil.safeJoin(threads);
        final long benchmark = System.nanoTime() - start;

        System.out.printf("<<%s>>: expected: %s, actual %s, time %s%n",
                counter.getClass().getSimpleName(),
                threadsCount * iterationsPerThread,
                counter.getCount(),
                benchmark);
        return benchmark;
    }
}
