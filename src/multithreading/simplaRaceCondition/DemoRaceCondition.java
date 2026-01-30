package multithreading.simplaRaceCondition;

import java.util.ArrayList;
import java.util.List;

public class DemoRaceCondition {
    public static int counter = 0;

    public static void main(String[] args) {
        int threadsNum = 8;
        int incrementValue = 10000;

        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < threadsNum; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < incrementValue; j++) {
                    counter++;
                }
            });
            threads.add(t);
        }

        threads.forEach(Thread::start);
        threads.forEach(DemoRaceCondition::join);
        System.out.printf("Expected: %s, actual: %s\n", threadsNum * incrementValue, counter);
    }

    public static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
